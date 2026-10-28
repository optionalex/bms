package com.bms.triangle.domain;

/** The three side-equality classifications for a valid triangle. */
public enum TriangleType {
    /** A triangle with three equal sides. */
    EQUILATERAL("Equilateral"),
    /** A triangle with exactly two equal sides. */
    ISOSCELES("Isosceles"),
    /** A triangle with no equal sides. */
    SCALENE("Scalene");

    /** Human-readable name used by the command-line interface. */
    private final String label;

    /** Creates a classification with its display label.
     *
     * @param label human-readable classification name
     */
    TriangleType(final String label) {
        this.label = label;
    }

    /** Returns the human-readable classification name.
     *
     * @return display label for this triangle type
     */
    public String label() {
        return label;
    }
}