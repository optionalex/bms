package com.bms.triangle.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

/** Tests the command-line adapter's observable output. */
class TriangleCliTest {

    /** Verifies that valid side lengths print the triangle classification. */
    @Test
    void printsClassificationFromCli() {
        final var result = runCli("3", "4", "5");

        assertEquals(0, result.exitCode());
        assertEquals("Scalene" + System.lineSeparator(), result.stdout());
        assertTrue(result.stderr().isEmpty());
    }

    /** Verifies that an incorrect number of arguments prints usage and exits with status 2. */
    @Test
    void reportsUsageForIncorrectArgumentCount() {
        final var result = runCli("3", "4");

        assertEquals(2, result.exitCode());
        assertTrue(result.stdout().isEmpty());
        assertEquals("Usage: triangle <side-a> <side-b> <side-c>" + System.lineSeparator(), result.stderr());
    }

    /** Verifies that a nonnumeric side is reported on standard error. */
    @Test
    void reportsErrorForNonnumericSide() {
        final var result = runCli("not-a-number", "2", "2");

        assertEquals(2, result.exitCode());
        assertTrue(result.stdout().isEmpty());
        assertEquals(
                "Error: Side lengths must be valid numbers." + System.lineSeparator(),
            result.stderr());
    }

    /** Verifies that geometrically invalid sides are rejected. */
    @Test
    void reportsErrorForInvalidTriangle() {
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
    void rejectsSidesOutsideDoubleRange(final String side) {
        final var result = runCli(side, side, side);

        assertEquals(2, result.exitCode());
        assertTrue(result.stdout().isEmpty());
        assertEquals(
                "Error: Side lengths must be positive finite numbers." + System.lineSeparator(),
                result.stderr());
    }

    /** Runs the CLI with captured output streams.
     *
     * @param arguments command-line arguments passed to the application
     * @return the exit code and captured console streams
     */
    private static CliResult runCli(final String... arguments) {
        final var output = new ByteArrayOutputStream();
        final var error = new ByteArrayOutputStream();

        try (final var outputStream = new PrintStream(output, true, StandardCharsets.UTF_8);
                final var errorStream = new PrintStream(error, true, StandardCharsets.UTF_8)) {
            final var exitCode = TriangleCli.run(arguments, outputStream, errorStream);
            return new CliResult(
                    exitCode,
                    output.toString(StandardCharsets.UTF_8),
                    error.toString(StandardCharsets.UTF_8));
        }
    }

    /** Captured result of a command-line process. */
    private record CliResult(int exitCode, String stdout, String stderr) {
    }
}