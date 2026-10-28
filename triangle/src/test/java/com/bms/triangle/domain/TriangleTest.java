package com.bms.triangle.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/** Tests the invariants enforced when constructing a {@link Triangle}. */
class TriangleTest {

    /** Verifies that null, nonpositive, and geometrically invalid sides are rejected. */
    @Test
    void rejectsInvalidSideLengths() {
        final var two = new BigDecimal("2");
        assertThrows(IllegalArgumentException.class, () -> new Triangle(BigDecimal.ZERO, two, two));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(new BigDecimal("-1"), two, two));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(null, two, two));
        }

        /** Verifies that a flat triangle whose two sides sum to the third is rejected. */
        @Test
        void rejectsDegenerateTriangle() {
        assertThrows(
                IllegalArgumentException.class,
            () -> new Triangle(BigDecimal.ONE, new BigDecimal("2"), new BigDecimal("3")));
    }

    /** Verifies exact validation beyond the precision range of {@code double}. */
    @Test
    void acceptsValidSidesNearFloatingPointRoundingBoundary() {
        assertDoesNotThrow(() -> new Triangle(
                BigDecimal.ONE,
                new BigDecimal("9007199254740992"),
                new BigDecimal("9007199254740992")));
    }
}