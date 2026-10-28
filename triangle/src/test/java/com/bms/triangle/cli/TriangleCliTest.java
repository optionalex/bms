package com.bms.triangle.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/** Tests the command-line adapter's observable output. */
class TriangleCliTest {

    /** Verifies that valid side lengths print the triangle classification. */
    @Test
    void printsClassificationFromCli() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ByteArrayOutputStream error = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        final PrintStream originalErr = System.err;

        try (final PrintStream capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8);
                final PrintStream capturedErr = new PrintStream(error, true, StandardCharsets.UTF_8)) {
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
}