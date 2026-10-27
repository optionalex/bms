package com.bms.triangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bms.triangle.cli.TriangleCli;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void printsClassificationFromCli() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ByteArrayOutputStream error = new ByteArrayOutputStream();
        final int exitCode = TriangleCli.run(
                new String[] {"3", "4", "5"},
                new PrintStream(output),
                new PrintStream(error));

        assertEquals(0, exitCode);
        assertEquals("Scalene" + System.lineSeparator(), output.toString(StandardCharsets.UTF_8));
        assertTrue(error.toString(StandardCharsets.UTF_8).isEmpty());
    }

    @Test
    void reportsInvalidCliInput() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ByteArrayOutputStream error = new ByteArrayOutputStream();
        final int exitCode = TriangleCli.run(
                new String[] {"not-a-number", "2", "2"},
                new PrintStream(output),
                new PrintStream(error));

        assertEquals(2, exitCode);
        assertTrue(output.toString(StandardCharsets.UTF_8).isEmpty());
        assertTrue(error.toString(StandardCharsets.UTF_8).contains("Error:"));
    }
}
