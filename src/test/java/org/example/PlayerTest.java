package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void defaults_areInitializedCorrectly() {
        Player player = new Player();
        assertEquals(100, player.getMaxHp());
        assertEquals(100, player.getCurrentHp());
        assertEquals(10, player.getChakra());
        assertEquals(0, player.getInventoryUsed());
        assertEquals(8, player.getInventoryCapacity());
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 0/8", player.getStatusString());
    }

    @Test
    void setCurrentHp_isClampedBetweenZeroAndMax() {
        Player player = new Player();
        player.setCurrentHp(150);
        assertEquals(100, player.getCurrentHp());
        player.setCurrentHp(-10);
        assertEquals(0, player.getCurrentHp());
        player.setCurrentHp(50);
        assertEquals(50, player.getCurrentHp());
        assertEquals("HP: 50/100 | Chakra: 10 | Inventar: 0/8", player.getStatusString());
    }

    @Test
    void setMaxHp_adjustsCurrentHpIfAboveNewMax() {
        Player player = new Player();
        player.setCurrentHp(90);
        player.setMaxHp(80);
        assertEquals(80, player.getMaxHp());
        assertEquals(80, player.getCurrentHp());
        assertEquals("HP: 80/80 | Chakra: 10 | Inventar: 0/8", player.getStatusString());
        player.setMaxHp(1);
        assertEquals(1, player.getMaxHp());
        assertEquals(1, player.getCurrentHp());
        assertEquals("HP: 1/1 | Chakra: 10 | Inventar: 0/8", player.getStatusString());
    }

    @Test
    void setChakra_cannotBeNegative() {
        Player player = new Player();
        player.setChakra(-5);
        assertEquals(0, player.getChakra());
        player.setChakra(25);
        assertEquals(25, player.getChakra());
    }

    @Test
    void setInventoryUsed_isClampedBetweenZeroAndCapacity() {
        Player player = new Player();
        player.setInventoryUsed(-3);
        assertEquals(0, player.getInventoryUsed());
        player.setInventoryUsed(7);
        assertEquals(7, player.getInventoryUsed());
        player.setInventoryUsed(99);
        assertEquals(player.getInventoryCapacity(), player.getInventoryUsed());
    }
}
