package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponClassesTest {

    @Test
    void sword_properties() {
        Weapon w = new Sword("Katana", 16);
        assertEquals("Katana", w.getName());
        assertEquals(16, w.damage());
        assertEquals(1, w.stackSize());
        assertTrue(w.toString().contains("Katana"));
    }

    @Test
    void throwingKnife_properties() {
        Weapon w = new ThrowingKnife("Shuriken (6)", 7);
        assertEquals("Shuriken (6)", w.getName());
        assertEquals(7, w.damage());
        assertEquals(6, w.stackSize());
    }
}


