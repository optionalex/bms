package com.bms.triangle.domain;

import java.util.Arrays;

/** An immutable triangle whose side lengths are positive and satisfy the strict triangle inequality.
 *
 * @param firstSide length of the first side
 * @param secondSide length of the second side
 * @param thirdSide length of the third side
 */
public record Triangle(double firstSide, double secondSide, double thirdSide) {

    /** Creates a triangle after validating its side lengths.
     *
     * @throws IllegalArgumentException if any side is not positive and finite, or the sides do not
     *     satisfy the strict triangle inequality
     */
    public Triangle {
        if (!isPositiveFinite(firstSide) || !isPositiveFinite(secondSide) || !isPositiveFinite(thirdSide)) {
            throw new IllegalArgumentException("Side lengths must be positive finite numbers.");
        }

        if (!satisfiesTriangleInequality(firstSide, secondSide, thirdSide)) {
            throw new IllegalArgumentException("Side lengths must satisfy the triangle inequality.");
        }
    }

    /** Sorts the sides and checks the strict triangle inequality without summing large sides.
     *
     * @param firstSide length of the first side
     * @param secondSide length of the second side
     * @param thirdSide length of the third side
     * @return {@code true} when the sides form a nondegenerate triangle
     */
    private static boolean satisfiesTriangleInequality(
            final double firstSide,
            final double secondSide,
            final double thirdSide) {
        final var sides = new double[] {firstSide, secondSide, thirdSide};
        Arrays.sort(sides);
        return sides[0] > sides[2] - sides[1];
    }

    /** Checks whether a side length is finite and greater than zero.
     *
     * @param side side length to validate
     * @return {@code true} when the side is positive and finite
     */
    private static boolean isPositiveFinite(final double side) {
        return Double.isFinite(side) && side > 0;
    }
}