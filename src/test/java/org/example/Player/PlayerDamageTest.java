package org.example.Player;

import org.example.Item.Weapon.Sword;
import org.example.Item.Weapon.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerDamageTest {

    @Test
    void getDamage_should_return_baseDamage__when_no_weapon_equipped() {
        PlayerDamage dmg = new PlayerDamage(10, 5);
        assertEquals(10, dmg.getDamage());
    }

    @Test
    void getDamage_should_include_weaponDamage__when_weapon_equipped() {
        PlayerDamage playerDamage = new PlayerDamage(10, 5);
        Weapon weapon = new Sword("Katana", 16);
        playerDamage.equip(weapon);
        assertEquals(26, playerDamage.getDamage());
    }

    @Test
    void restoreChakra_should_accumulate_without_limit__when_multiple_elixirs_used() {
        PlayerDamage playerDamage = new PlayerDamage(10, 10);
        playerDamage.restoreChakra(50);
        assertEquals(60, playerDamage.getChakra());
        playerDamage.restoreChakra(50);
        assertEquals(110, playerDamage.getChakra());
    }
}


