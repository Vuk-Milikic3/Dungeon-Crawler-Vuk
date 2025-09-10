package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    void start_keepsStartState_andReturnsWithoutError() {
        Game game = new Game();
        game.start();
        assertTrue(game.getState());
    }

    @Test
    void processCommand_status_returnsFormattedStats() {
        Game game = new Game();
        String result = game.processCommand("status");
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 0/8", result);
    }
}


