package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    private Utilities utilities;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        utilities = new Utilities();
    }

    public static Stream<Arguments> testConditions_everyNthChar() {
        return Stream.of(
                Arguments.of(new char[]{'e', 'l'}, (new char[]{'h', 'e', 'l', 'l', 'o'}), 2),
                Arguments.of(null, null, 0),
                Arguments.of(new char[]{'h', 'e', 'l', 'l', 'o'}, (new char[]{'h', 'e', 'l', 'l', 'o'}), 8)
        );
    }


    @ParameterizedTest(name = "Run {index}: expected={0}, actualCharArray={1}, actualN={2}")
    @MethodSource("testConditions_everyNthChar")
    void everyNthChar(char[] expected, char[] actualCharArray, int actualN) {
        assertArrayEquals(expected, utilities.everyNthChar(actualCharArray, actualN), () -> "Arrays are not the same " +
                "length");
    }


    public static Stream<Arguments> testConditions_removePairs() {
        return Stream.of(
                Arguments.of("ABBCDEEF", "ABCDEF"),
                Arguments.of("ABCBDEEF", "ABCBDEF"),
                Arguments.of("AAAAAAA", "A"),
                Arguments.of("11111111", "1"),
                Arguments.of("ABCDEF", "ABCDEF"),
                Arguments.of("AA", "A"),
                Arguments.of("A", "A"),
                Arguments.of("", ""),
                Arguments.of(null, null),
                Arguments.of("ABCDEFF", "ABCDEF"),
                Arguments.of("AB88EFFG", "AB8EFG"),
                Arguments.of("112233445566", "123456"),
                Arguments.of("ZYZQQB", "ZYZQB")
        );
    }

    @ParameterizedTest(name = "Run {index}: string={0}, expected={1}")
    @MethodSource("testConditions_removePairs")
    public void removePairsTest_multipleChars(String string, String expected) {
        assertEquals(expected, utilities.removePairs(string));
    }

    public static Stream<Arguments> testConditions_converter() {
        return Stream.of(
                Arguments.of(300, 10, 5)
        );
    }

    @ParameterizedTest(name = "run {index}: expected={0}, actualA={1}, actualB={2}")
    @MethodSource("testConditions_converter")
    void converter(int expected, int actualA, int actualB) throws ArithmeticException {
        assertEquals(expected, utilities.converter(actualA, actualB));
    }

    public static Stream<Arguments> testConditions_converterArithmeticException() {
        return Stream.of(
                Arguments.of(0, 300, 0),
                Arguments.of(0, 0, 0)
        );
    }

    @ParameterizedTest(name = "run {index}: expected={0}, actualA={1}, actualB={2}")
    @MethodSource("testConditions_converterArithmeticException")
    void converter_ArithmeticException(int expected, int actualA, int actualB) throws ArithmeticException {
        Assertions.assertThrows(ArithmeticException.class, () -> assertEquals(expected, utilities.converter(actualA,
                actualB)), "ArithmeticException (divide by 0)");
    }

    public static Stream<Arguments> testConditions_nullIfOddLength() {
        return Stream.of(
                Arguments.of("AB", "AB"),
                Arguments.of(null, "A"),
                Arguments.of("", ""),
                Arguments.of("Test", "Test")
        );
    }

    @ParameterizedTest(name = "Run {index}: expected={0}, actualString={1}")
    @MethodSource("testConditions_nullIfOddLength")
    void nullIfOddLength(String expected, String actualString) {
        assertEquals(expected, utilities.nullIfOddLength(actualString));
    }
}