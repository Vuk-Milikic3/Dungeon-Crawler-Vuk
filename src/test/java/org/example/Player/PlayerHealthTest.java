package org.example.Player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerHealthTest {

    @Test
    void heal_should__not_go_over_max__when_amount_is_too_big() {
        PlayerHealth playerHealth = new PlayerHealth(90, 100);
        playerHealth.heal(50);
        assertEquals(100, playerHealth.getCurrentHp());
    }

    @Test
    void heal_should__do_nothing__when_amount_is_non_positive() {
        PlayerHealth playerHealth = new PlayerHealth(90, 100);
        playerHealth.heal(0);
        assertEquals(90, playerHealth.getCurrentHp());
    }

    @Test
    void heal_should__increase_health__when_amount_is_positive() {
        PlayerHealth playerHealth = new PlayerHealth(60, 100);
        playerHealth.heal(25);
        assertEquals(85, playerHealth.getCurrentHp());
    }
}

