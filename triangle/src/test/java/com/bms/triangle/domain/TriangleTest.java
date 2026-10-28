package com.bms.triangle.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/** Tests the invariants enforced when constructing a {@link Triangle}. */
class TriangleTest {

    /** Verifies that nonpositive, nonfinite, and geometrically invalid sides are rejected. */
    @Test
    void rejectsInvalidSideLengths() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-1, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(Double.NaN, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(Double.parseDouble("1e309"), 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 2, 3));
    }
}