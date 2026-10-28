package com.bms.triangle.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/** Tests the side-equality rules used by {@link TriangleClassifier}. */
class TriangleClassifierTest {

    /** Verifies classification across triangle types and side orderings. */
    @ParameterizedTest
    @CsvSource({
        "3, 3, 3, EQUILATERAL",
        "3, 3, 2, ISOSCELES",
        "3, 2, 3, ISOSCELES",
        "2, 3, 3, ISOSCELES",
        "3, 4, 5, SCALENE",
        "5, 3, 4, SCALENE",
        "0.1, 0.10, 0.100, EQUILATERAL"
    })
    void classifiesTriangleTypes(
            final BigDecimal firstSide,
            final BigDecimal secondSide,
            final BigDecimal thirdSide,
            final TriangleType expectedType) {
        assertEquals(
                expectedType,
                TriangleClassifier.classify(new Triangle(firstSide, secondSide, thirdSide)));
    }
}