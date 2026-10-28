package com.bms.triangle.cli;

import com.bms.triangle.domain.Triangle;
import com.bms.triangle.domain.TriangleClassifier;
import java.io.PrintStream;

/** Parses triangle side lengths, classifies the triangle, and writes the result to the console. */
public final class TriangleCli {

    /** Prevents instantiation of this command-line utility class. */
    private TriangleCli() {
    }

    /** Starts the command-line application.
     *
     * @param args command-line arguments passed to {@link #run(String[], PrintStream, PrintStream)}
     */
    public static void main(final String[] args) {
        final var exitCode = run(args, System.out, System.err);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    /** Parses, validates, and classifies command-line arguments using the supplied streams.
     *
     * @param args command-line arguments containing the three side lengths
     * @param output stream for successful output
     * @param error stream for usage and validation errors
     * @return {@code 0} on success or {@code 2} for invalid input
     */
    static int run(final String[] args, final PrintStream output, final PrintStream error) {
        if (args.length != 3) {
            error.println("Usage: triangle <side-a> <side-b> <side-c>");
            return 2;
        }
        try {
            final var triangle = new Triangle(
                    parseSide(args[0]),
                    parseSide(args[1]),
                    parseSide(args[2]));

            output.println(TriangleClassifier.classify(triangle).label());
            return 0;
        } catch (final IllegalArgumentException exception) {
            error.println("Error: " + exception.getMessage());
            return 2;
        }
    }

    /** Parses a side length using {@link Double#parseDouble(String)}.
     *
     * @param argument text supplied for a side length
     * @return the parsed side length
     * @throws IllegalArgumentException if the argument is not accepted by {@link Double#parseDouble(String)}
     */
    private static double parseSide(final String argument) {
        try {
            return Double.parseDouble(argument);
        } catch (final NumberFormatException exception) {
            throw new IllegalArgumentException("Side lengths must be valid numbers.", exception);
        }
    }
}