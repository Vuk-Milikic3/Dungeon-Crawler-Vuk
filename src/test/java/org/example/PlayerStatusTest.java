package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerStatusTest {

    @Test
    void toString_formatsAsExpected() {
        PlayerStatus status = new PlayerStatus(100, 100, 10, 0, 8);
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 0/8", status.toString());
    }
}


