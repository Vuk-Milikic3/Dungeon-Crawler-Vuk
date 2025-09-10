package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    void start_keepsStartState_andReturnsWithoutError() {
        Game game = new Game();
        game.start();
        assertEquals(Game.State.GAME_START, game.getState());
    }
}


