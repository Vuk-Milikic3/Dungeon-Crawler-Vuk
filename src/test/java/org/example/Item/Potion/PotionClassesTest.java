package org.example.Item.Potion;

import org.example.Item.Potion.Chakra.LargeChakraElixir;
import org.example.Item.Potion.Heal.LargeHealingPotion;
import org.example.Item.Potion.Chakra.SmallChakraElixir;
import org.example.Item.Potion.Chakra.ChakraPotion;
import org.example.Item.Potion.Heal.HealingPotion;
import org.example.Item.Potion.Heal.SmallHealingPotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PotionClassesTest {

    @Test
    void smallHealingPotion_properties() {
        HealingPotion p = new SmallHealingPotion();
        assertEquals("Kleiner-Kyubi-Heiltrank", p.getName());
        assertEquals(25, p.heal());
        assertTrue(p.toString().contains(p.getName()));
    }

    @Test
    void largeHealingPotion_properties() {
        HealingPotion p = new LargeHealingPotion();
        assertEquals("Grosser-Weisen-Heiltrank", p.getName());
        assertEquals(60, p.heal());
    }

    @Test
    void smallChakraElixir_properties() {
        ChakraPotion p = new SmallChakraElixir();
        assertEquals("Kleines-Chakra-Elixier", p.getName());
        assertEquals(20, p.chakraPowerUp());
    }

    @Test
    void largeChakraElixir_properties() {
        ChakraPotion p = new LargeChakraElixir();
        assertEquals("Grosses-Weisen-Chakra-Elixier", p.getName());
        assertEquals(50, p.chakraPowerUp());
    }
}


