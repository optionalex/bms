package com.bms.triangle.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TriangleClassifierTest {

    @Test
    void classifiesTriangleTypes() {
        assertEquals(TriangleType.EQUILATERAL, TriangleClassifier.classify(new Triangle(3, 3, 3)));
        assertEquals(TriangleType.ISOSCELES, TriangleClassifier.classify(new Triangle(3, 3, 2)));
        assertEquals(TriangleType.SCALENE, TriangleClassifier.classify(new Triangle(3, 4, 5)));
    }
}