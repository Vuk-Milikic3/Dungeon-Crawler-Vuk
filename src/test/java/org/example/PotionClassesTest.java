package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PotionClassesTest {

    @Test
    void smallHealingPotion_properties() {
        HealingPotion p = new SmallHealingPotion();
        assertEquals("Kleiner Kyubi-Heiltrank", p.getName());
        assertEquals(25, p.heal());
        assertTrue(p.toString().contains(p.getName()));
    }

    @Test
    void largeHealingPotion_properties() {
        HealingPotion p = new LargeHealingPotion();
        assertEquals("Grosser Weisen-Heiltrank", p.getName());
        assertEquals(60, p.heal());
    }

    @Test
    void smallChakraElixir_properties() {
        ChakraPotion p = new SmallChakraElixir();
        assertEquals("Kleines Chakra-Elixier", p.getName());
        assertEquals(20, p.chakraPowerUp());
    }

    @Test
    void largeChakraElixir_properties() {
        ChakraPotion p = new LargeChakraElixir();
        assertEquals("Grosses Weisen-Chakra-Elixier", p.getName());
        assertEquals(50, p.chakraPowerUp());
    }
}


