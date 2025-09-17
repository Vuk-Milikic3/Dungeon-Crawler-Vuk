package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    void move_returnsNewRoomDescription_onValidExit() {
        Room start = new Room("Start", "");
        Room next = new Room("Ziel", "");
        start.connect(Direction.NORTH, next);
        Player player = new Player(start);

        String out = player.move(Direction.NORTH);

        assertTrue(out.contains("Raum: Ziel"));
    }

    @Test
    void move_returnsWallMessage_onMissingExit() {
        Room start = new Room("Start", "");
        Player player = new Player(start);

        String out = player.move(Direction.WEST);

        assertEquals("Dort ist eine Wand.", out);
    }

    @Test
    void move_fromNullRoom_returnsEmptyString() {
        Player player = new Player(null);

        String out = player.move(Direction.NORTH);

        assertEquals("", out);
    }
}


