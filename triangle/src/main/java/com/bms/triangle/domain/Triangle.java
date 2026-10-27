package com.bms.triangle.domain;

public record Triangle(double sideA, double sideB, double sideC) {

    public Triangle {
        if (!isPositiveFinite(sideA) || !isPositiveFinite(sideB) || !isPositiveFinite(sideC)) {
            throw new IllegalArgumentException("Side lengths must be positive finite numbers.");
        }

        if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
            throw new IllegalArgumentException("Side lengths must satisfy the triangle inequality.");
        }
    }

    private static boolean isPositiveFinite(final double side) {
        return Double.isFinite(side) && side > 0;
    }
}