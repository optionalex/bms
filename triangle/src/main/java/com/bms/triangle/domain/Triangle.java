package com.bms.triangle.domain;

import java.math.BigDecimal;

/** An immutable triangle whose side lengths are positive and satisfy the strict triangle inequality.
 *
 * @param firstSide length of the first side
 * @param secondSide length of the second side
 * @param thirdSide length of the third side
 */
public record Triangle(BigDecimal firstSide, BigDecimal secondSide, BigDecimal thirdSide) {

    /** Creates a triangle after validating its side lengths.
     *
    * @throws IllegalArgumentException if any side is null or not positive, or the sides do not
    *     satisfy the strict triangle inequality
     */
    public Triangle {
        if (!isPositive(firstSide) || !isPositive(secondSide) || !isPositive(thirdSide)) {
            throw new IllegalArgumentException("Side lengths must be positive decimal numbers.");
        }

        if (firstSide.add(secondSide).compareTo(thirdSide) <= 0
                || firstSide.add(thirdSide).compareTo(secondSide) <= 0
                || secondSide.add(thirdSide).compareTo(firstSide) <= 0) {
            throw new IllegalArgumentException("Side lengths must satisfy the triangle inequality.");
        }
    }

    /** Checks whether a side length is non-null and greater than zero.
     *
     * @param side side length to validate
     * @return {@code true} when the side is positive
     */
    private static boolean isPositive(final BigDecimal side) {
        return side != null && side.signum() > 0;
    }
}