package com.bms.triangle.domain;

/** An immutable triangle whose side lengths are positive, finite, and satisfy the triangle inequality.
 *
 * @param firstSide length of the first side
 * @param secondSide length of the second side
 * @param thirdSide length of the third side
 */
public record Triangle(double firstSide, double secondSide, double thirdSide) {

    /** Creates a triangle after validating its side lengths.
     *
     * @throws IllegalArgumentException if any side is not positive and finite, or the sides do not
     *     satisfy the triangle inequality
     */
    public Triangle {
        if (!isPositiveFinite(firstSide) || !isPositiveFinite(secondSide) || !isPositiveFinite(thirdSide)) {
            throw new IllegalArgumentException("Side lengths must be positive finite numbers.");
        }

        if (firstSide + secondSide <= thirdSide
            || firstSide + thirdSide <= secondSide
            || secondSide + thirdSide <= firstSide) {
            throw new IllegalArgumentException("Side lengths must satisfy the triangle inequality.");
        }
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