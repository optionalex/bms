package com.bms.triangle.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

/** Tests the command-line adapter's observable output. */
class TriangleCliTest {

    /** Verifies that valid side lengths print the triangle classification. */
    @Test
    void printsClassificationFromCli() {
        final var output = new ByteArrayOutputStream();
        final var error = new ByteArrayOutputStream();
        final var originalOut = System.out;
        final var originalErr = System.err;

        try (final var capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8);
            final var capturedErr = new PrintStream(error, true, StandardCharsets.UTF_8)) {
            System.setOut(capturedOut);
            System.setErr(capturedErr);
            TriangleCli.main(new String[] {"3", "4", "5"});
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }

        assertEquals("Scalene" + System.lineSeparator(), output.toString(StandardCharsets.UTF_8));
        assertTrue(error.toString(StandardCharsets.UTF_8).isEmpty());
    }

    /** Verifies that an incorrect number of arguments prints usage and exits with status 2. */
    @Test
    void reportsUsageForIncorrectArgumentCount() throws Exception {
        final var result = runCli("3", "4");

        assertEquals(2, result.exitCode());
        assertTrue(result.stdout().isEmpty());
        assertEquals("Usage: triangle <side-a> <side-b> <side-c>" + System.lineSeparator(), result.stderr());
    }

    /** Verifies that a nonnumeric side is reported on standard error. */
    @Test
    void reportsErrorForNonnumericSide() throws Exception {
        final var result = runCli("not-a-number", "2", "2");

        assertEquals(2, result.exitCode());
        assertTrue(result.stdout().isEmpty());
        assertEquals(
                "Error: Side lengths must be valid numbers." + System.lineSeparator(),
            result.stderr());
    }

    /** Verifies that geometrically invalid sides are rejected. */
    @Test
    void reportsErrorForInvalidTriangle() throws Exception {
        final var result = runCli("1", "2", "3");

        assertEquals(2, result.exitCode());
        assertTrue(result.stdout().isEmpty());
        assertEquals(
                "Error: Side lengths must satisfy the triangle inequality." + System.lineSeparator(),
                result.stderr());
    }

    /** Verifies that decimal values outside the finite {@code double} range are rejected. */
    @ParameterizedTest
    @ValueSource(strings = {"1e309", "1e-4000"})
    void rejectsSidesOutsideDoubleRange(final String side) throws Exception {
        final var result = runCli(side, side, side);

        assertEquals(2, result.exitCode());
        assertTrue(result.stdout().isEmpty());
        assertEquals(
                "Error: Side lengths must be positive finite numbers." + System.lineSeparator(),
                result.stderr());
    }

    /** Runs the application in a child JVM so its exit call cannot terminate the test process.
     *
     * @param arguments command-line arguments passed to the application
     * @return the child's exit code and captured console streams
     */
    private static CliResult runCli(final String... arguments) throws IOException, InterruptedException,
            java.net.URISyntaxException {
        final var javaExecutable = System.getProperty("os.name").startsWith("Windows") ? "java.exe" : "java";
        final var javaPath = Path.of(System.getProperty("java.home"), "bin", javaExecutable).toString();
        final var classesPath = Path.of(
                TriangleCli.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toString();
        final var command = Stream.concat(
            Stream.of(javaPath, "-cp", classesPath, "com.bms.triangle.App"),
            Arrays.stream(arguments)).toList();

        final var process = new ProcessBuilder(command).start();
        final var stdout = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        final var stderr = new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
        final var exitCode = process.waitFor();
        return new CliResult(exitCode, stdout, stderr);
    }

    /** Captured result of a command-line process. */
    private record CliResult(int exitCode, String stdout, String stderr) {
    }
}