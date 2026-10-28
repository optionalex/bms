package com.bms.triangle.cli;

import com.bms.triangle.domain.Triangle;
import com.bms.triangle.domain.TriangleClassifier;
import java.math.BigDecimal;

/** Parses triangle side lengths, classifies the triangle, and writes the result to the console. */
public final class TriangleCli {

    /** Prevents instantiation of this command-line utility class. */
    private TriangleCli() {
    }

    /** Runs the command-line application with the supplied arguments.
     *
    * <p>Exactly three positive decimal side lengths are expected. Invalid input is reported on
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
            final var triangle = new Triangle(
                    parseSide(args[0]),
                    parseSide(args[1]),
                    parseSide(args[2]));
                
            System.out.println(TriangleClassifier.classify(triangle).label());
            return;
        } catch (final IllegalArgumentException exception) {
            System.err.println("Error: " + exception.getMessage());
            System.exit(2);
        }
    }

    /** Parses a command-line value as a decimal number.
     *
     * @param argument text supplied for a side length
     * @return the parsed side length
     * @throws IllegalArgumentException if the argument is not a valid decimal number
     */
    private static BigDecimal parseSide(final String argument) {
        try {
            return new BigDecimal(argument);
        } catch (final NumberFormatException exception) {
            throw new IllegalArgumentException("Side lengths must be valid decimal numbers.", exception);
        }
    }
}