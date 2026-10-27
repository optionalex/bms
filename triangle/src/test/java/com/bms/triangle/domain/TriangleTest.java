package com.bms.triangle.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TriangleTest {

    @Test
    void rejectsInvalidSideLengths() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-1, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(Double.NaN, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 2, 3));
    }
}