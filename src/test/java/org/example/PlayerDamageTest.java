package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerDamageTest {

    @Test
    void base_attack_without_weapon_is_baseDamage() {
        PlayerDamage dmg = new PlayerDamage(10, 5);
        assertEquals(10, dmg.getAttack());
    }

    @Test
    void attack_with_weapon_adds_weapon_damage() {
        PlayerDamage dmg = new PlayerDamage(10, 5);
        Weapon w = new Sword("Katana", 16);
        dmg.equip(w);
        assertEquals(26, dmg.getAttack());
    }

    @Test
    void chakra_increases_unbounded() {
        PlayerDamage dmg = new PlayerDamage(10, 10);
        dmg.restoreChakra(50);
        assertEquals(60, dmg.getChakra());
        dmg.restoreChakra(50);
        assertEquals(110, dmg.getChakra());
    }
}


