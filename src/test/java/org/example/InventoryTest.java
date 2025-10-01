package org.example;

import org.example.Item.Potion.Chakra.SmallChakraElixir;
import org.example.Item.Potion.Heal.SmallHealingPotion;
import org.example.Item.Weapon.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    void spaceUsed_should_increase_when_adding_items_until_full() {
        Inventory inv = new Inventory(2);
        assertEquals(0, inv.spaceUsed());
        assertFalse(inv.isFull());

        assertTrue(inv.add(new SmallHealingPotion()));
        assertEquals(1, inv.spaceUsed());
        assertFalse(inv.isFull());

        assertTrue(inv.add(new Sword("Katana", 16)));
        assertEquals(2, inv.spaceUsed());
        assertTrue(inv.isFull());

        assertFalse(inv.add(new SmallChakraElixir()));
        assertTrue(inv.remove(inv.list().get(0)));
        assertEquals(1, inv.spaceUsed());
        assertFalse(inv.isFull());
    }
}


