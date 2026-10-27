package com.bms.triangle.cli;

import com.bms.triangle.domain.Triangle;
import com.bms.triangle.domain.TriangleClassifier;
import java.io.PrintStream;

public final class TriangleCli {

    private TriangleCli() {
    }

    public static void main(final String[] args) {
        final int exitCode = run(args, System.out, System.err);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    public static int run(final String[] args, final PrintStream output, final PrintStream error) {
        if (args.length != 3) {
            error.println("Usage: triangle <side-a> <side-b> <side-c>");
            return 2;
        }

        try {
            final Triangle triangle = new Triangle(
                    Double.parseDouble(args[0]),
                    Double.parseDouble(args[1]),
                    Double.parseDouble(args[2]));
            output.println(TriangleClassifier.classify(triangle).label());
            return 0;
        } catch (IllegalArgumentException exception) {
            error.println("Error: " + exception.getMessage());
            return 2;
        }
    }
}