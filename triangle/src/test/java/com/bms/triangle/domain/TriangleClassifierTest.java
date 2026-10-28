package com.bms.triangle.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Tests the side-equality rules used by {@link TriangleClassifier}. */
class TriangleClassifierTest {

    /** Verifies classification of equilateral, isosceles, and scalene triangles. */
    @Test
    void classifiesTriangleTypes() {
        assertEquals(TriangleType.EQUILATERAL, TriangleClassifier.classify(new Triangle(3, 3, 3)));
        assertEquals(TriangleType.ISOSCELES, TriangleClassifier.classify(new Triangle(3, 3, 2)));
        assertEquals(TriangleType.SCALENE, TriangleClassifier.classify(new Triangle(3, 4, 5)));
    }
}