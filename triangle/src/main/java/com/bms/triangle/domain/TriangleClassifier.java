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
        if (triangle.firstSide() == triangle.secondSide()
            && triangle.secondSide() == triangle.thirdSide()) {
            return TriangleType.EQUILATERAL;
        }

        if (triangle.firstSide() == triangle.secondSide()
            || triangle.firstSide() == triangle.thirdSide()
            || triangle.secondSide() == triangle.thirdSide()) {
            return TriangleType.ISOSCELES;
        }

        return TriangleType.SCALENE;
    }
}