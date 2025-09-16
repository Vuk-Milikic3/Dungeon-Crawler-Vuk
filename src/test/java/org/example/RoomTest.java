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
                Arguments.of(RoomConnections.Direction.NORTH, "norden"),
                Arguments.of(RoomConnections.Direction.SOUTH, "süden"),
                Arguments.of(RoomConnections.Direction.EAST, "osten"),
                Arguments.of(RoomConnections.Direction.WEST, "westen")
        );
    }

    @ParameterizedTest
    @MethodSource("directions")
    void connect_usesDirectionLabelInOutput(RoomConnections.Direction direction, String expectedLabel) {
        Room a = new Room("A", "");
        Room b = new Room("B", "");
        a.connect(direction, b);

        String out = a.toString().toLowerCase();
        assertTrue(out.contains("ausgänge:"));
        assertTrue(out.contains(expectedLabel));
    }

    @Test
    void roomConnections_startRoomHasNorthExit() {
        RoomConnections world = new RoomConnections();
        String out = world.getStartRoom().toString().toLowerCase();
        assertTrue(out.contains("ausgänge:"));
        assertTrue(out.contains("norden"));
    }
}


