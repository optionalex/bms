package com.bms.triangle.domain;

/** Classifies valid triangles by the equality of their side lengths. */
public final class TriangleClassifier {

    /** Prevents instantiation of this stateless classifier. */
    private TriangleClassifier() {
    }

    /** Determines the type of a triangle.
     *
     * @param triangle valid triangle to classify
     * @return the triangle's type
     */
    public static TriangleType classify(final Triangle triangle) {
        if (triangle.firstSide().compareTo(triangle.secondSide()) == 0
            && triangle.secondSide().compareTo(triangle.thirdSide()) == 0) {
            return TriangleType.EQUILATERAL;
        }

        if (triangle.firstSide().compareTo(triangle.secondSide()) == 0
            || triangle.firstSide().compareTo(triangle.thirdSide()) == 0
            || triangle.secondSide().compareTo(triangle.thirdSide()) == 0) {
            return TriangleType.ISOSCELES;
        }

        return TriangleType.SCALENE;
    }
}