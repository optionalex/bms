package com.bms.triangle.cli;

import com.bms.triangle.domain.Triangle;
import com.bms.triangle.domain.TriangleClassifier;
import java.io.PrintStream;

/** Parses triangle side lengths, classifies the triangle, and writes the result to the console. */
public final class TriangleCli {

    /** Prevents instantiation of this command-line utility class. */
    private TriangleCli() {
    }

    /** Runs the command-line application with the supplied arguments.
     *
     * <p>Exactly three positive, finite side lengths are expected. Invalid input is reported on
     * standard error and terminates the process with status code {@code 2}.
     *
     * @param args command-line arguments containing the three side lengths
     */
    public static void main(final String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: triangle <side-a> <side-b> <side-c>");
            System.exit(2);
        }
        try {
            final Triangle triangle = new Triangle(
                    Double.parseDouble(args[0]),
                    Double.parseDouble(args[1]),
                    Double.parseDouble(args[2]));
                
            System.out.println(TriangleClassifier.classify(triangle).label());
            return;
        } catch (final IllegalArgumentException exception) {
            System.err.println("Error: " + exception.getMessage());
            System.exit(2);
        }
    }
}