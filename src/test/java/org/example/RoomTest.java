package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {

    private static Stream<Arguments> directions() {
        return Stream.of(
                Arguments.of(Direction.NORTH, "norden"),
                Arguments.of(Direction.SOUTH, "süden"),
                Arguments.of(Direction.EAST, "osten"),
                Arguments.of(Direction.WEST, "westen")
        );
    }

    @ParameterizedTest
    @MethodSource("directions")
    void connect_usesDirectionLabelInOutput(Direction direction, String expectedLabel) {
        Room a = new Room("A", "");
        Room b = new Room("B", "");
        a.connect(direction, b);

        String out = a.toString().toLowerCase();
        assertTrue(out.contains("ausgänge:"));
        assertTrue(out.contains(expectedLabel));
    }

    @Test
    void room_connectNorth_updatesOutput() {
        Room a = new Room("A", "");

        String outBefore = a.toString().toLowerCase();
        assertTrue(outBefore.contains("ausgänge:"));
        assertTrue(outBefore.contains("ausgänge: keine"));

        Room b = new Room("B", "");
        a.connect(Direction.NORTH, b);

        String outAfter = a.toString().toLowerCase();
        assertTrue(outAfter.contains("ausgänge:"));
        assertTrue(outAfter.contains("norden"));
    }
}


