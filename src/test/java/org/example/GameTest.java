package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameTest {

    @Test
    void initial_isNotStarted() {
        Game game = new Game();
        assertFalse(game.isStarted());
    }

    @Test
    void processCommand_status_returnsFormattedStats() {
        Game game = new Game();
        String result = game.processCommand("status");
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 0/8", result);
    }

    @Test
    void processCommand_unknown_returnsErrorMessage() {
        Game game = new Game();
        String result = game.processCommand("bar");
        assertEquals("Unbekannter Befehl", result);
    }
}


