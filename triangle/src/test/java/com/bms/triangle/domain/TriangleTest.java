package com.bms.triangle.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/** Tests the invariants enforced when constructing a {@link Triangle}. */
class TriangleTest {

    /** Verifies that nonpositive, nonfinite, and geometrically invalid sides are rejected. */
    @Test
    void rejectsInvalidSideLengths() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-1, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(Double.NaN, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(Double.POSITIVE_INFINITY, 2, 2));
    }

    /** Verifies that a flat triangle whose two sides sum to the third is rejected. */
    @ParameterizedTest
    @CsvSource({"1, 2, 3", "1, 3, 2", "3, 1, 2"})
    void rejectsDegenerateTriangle(final double firstSide, final double secondSide, final double thirdSide) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(firstSide, secondSide, thirdSide));
    }

    /** Verifies valid boundary cases in each side position without losing small differences to rounding. */
    @ParameterizedTest
    @CsvSource({
        "1, 9007199254740992, 9007199254740992",
        "9007199254740996, 9007199254740996, 0.5",
        "9007199254740996, 0.5, 9007199254740996",
        "0.5, 9007199254740996, 9007199254740996"
    })
    void acceptsValidSidesNearFloatingPointRoundingBoundary(
            final double firstSide,
            final double secondSide,
            final double thirdSide) {
        assertDoesNotThrow(() -> new Triangle(firstSide, secondSide, thirdSide));
    }

    /** Verifies that a small side cannot satisfy an inequality with a larger gap. */
    @Test
    void rejectsInvalidSidesNearFloatingPointRoundingBoundary() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(0.5, 9007199254740996d, 9007199254740998d));
    }

    /** Verifies that triangle validation does not overflow when sides are large. */
    @Test
    void acceptsLargeFiniteEqualSides() {
        assertDoesNotThrow(() -> new Triangle(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));
    }
}