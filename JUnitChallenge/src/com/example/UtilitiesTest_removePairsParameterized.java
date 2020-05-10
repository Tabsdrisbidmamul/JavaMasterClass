package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UtilitiesTest_removePairsParameterized {

    private Utilities utilities;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        utilities = new Utilities();
    }

    public static Stream<Arguments> testConditions() {
        return Stream.of(
                Arguments.of("ABBCDEEF", "ABCDEF"),
                Arguments.of("ABCBDEEF", "ABCBDEF"),
                Arguments.of("AAAAAAA", "A"),
                Arguments.of("11111111", "1"),
                Arguments.of("ABCDEF", "ABCDEF"),
                Arguments.of("AA", "A"),
                Arguments.of("A", "A"),
                Arguments.of("", ""),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest(name = "Run {index}: string={0}, expected={1}")
    @MethodSource("testConditions")
    public void removePairsTest_multipleChars(String string, String expected) {
        assertEquals(expected, utilities.removePairs(string));
    }

}
