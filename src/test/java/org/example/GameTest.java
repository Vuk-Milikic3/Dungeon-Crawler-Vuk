package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void processCommand_schauen_showsRoomNameAndExits() {
        Game game = new Game();
        String out = game.processCommand("schauen");
        boolean hasName = out.contains("Raum: Kerker");
        String lower = out.toLowerCase();
        boolean hasExit = lower.contains("ausgänge:");
        boolean hasExitLabel =
                lower.contains("norden") || lower.contains("süden") ||
                lower.contains("osten") || lower.contains("westen");
        assertTrue(hasName && hasExit && hasExitLabel);
    }
}


