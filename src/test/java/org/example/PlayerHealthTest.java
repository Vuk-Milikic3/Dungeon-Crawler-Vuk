package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerHealthTest {

    @Test
    void heal_should_not_exceed_max_hp() {
        PlayerHealth hp = new PlayerHealth(90, 100);
        hp.heal(50);
        assertEquals(100, hp.getCurrentHp());
    }

    @Test
    void heal_with_non_positive_value_should_do_nothing() {
        PlayerHealth hp = new PlayerHealth(90, 100);
        hp.heal(0);
        assertEquals(90, hp.getCurrentHp());
    }
}

