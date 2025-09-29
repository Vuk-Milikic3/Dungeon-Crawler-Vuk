package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void initial_isNotStarted() {
        Game game = new Game();
        assertFalse(game.isStarted());
    }

    @Test
    void status_command_should_return_formatted_stats() {
        Game game = new Game();
        String result = game.processCommand("status");
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 0/8 | Waffe: keine | Schaden: 10" , result);
    }

    @Test
    void unknown_command_should_return_error_message() {
        Game game = new Game();
        String result = game.processCommand("bar");
        assertEquals("Unbekannter Befehl", result);
    }

    @Test
    void schauen_should_show_room_name_and_exits() {
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

    @Test
    void inventar_text_should_start_with_Inventar() {
        Game game = new Game();
        game.processCommand("w");
        String out = game.processCommand("inventar");
        assertTrue(out.startsWith("Inventar ("));
    }

    private static Stream<Arguments> movementCases() {
        return Stream.of(
                Arguments.of("", "w", "Raum: Waffenkammer"),
                Arguments.of("", "a", "Dort ist eine Wand."),
                Arguments.of("", "s", "Dort ist eine Wand."),
                Arguments.of("w", "d", "Raum: Bibliothek")
        );
    }

    @ParameterizedTest
    @MethodSource("movementCases")
    void wasd_moves_should_work(String pre, String cmd, String expectedContains) {
        Game game = new Game();
        for (char c : pre.toCharArray()) {
            game.processCommand(String.valueOf(c));
        }
        String out = game.processCommand(cmd);
        assertTrue(out.contains(expectedContains));
    }
}


