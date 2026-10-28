package com.bms.triangle;

import com.bms.triangle.cli.TriangleCli;

/** Provides the application-level entry point and delegates to the command-line adapter. */
public final class App {

    /** Prevents instantiation of this entry-point class. */
    private App() {
    }

    /** Starts the triangle classifier command-line application.
     *
     * @param args command-line arguments passed to {@link TriangleCli}
     */
    public static void main(final String[] args) {
        TriangleCli.main(args);
    }
}