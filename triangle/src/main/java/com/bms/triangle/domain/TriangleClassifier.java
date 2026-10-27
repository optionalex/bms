package com.bms.triangle.domain;

public final class TriangleClassifier {

    private TriangleClassifier() {
    }

    public static TriangleType classify(final Triangle triangle) {
        if (triangle.sideA() == triangle.sideB() && triangle.sideB() == triangle.sideC()) {
            return TriangleType.EQUILATERAL;
        }

        if (triangle.sideA() == triangle.sideB()
                || triangle.sideA() == triangle.sideC()
                || triangle.sideB() == triangle.sideC()) {
            return TriangleType.ISOSCELES;
        }

        return TriangleType.SCALENE;
    }
}