package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    void move_should_return_new_room_description_on_valid_exit() {
        Room start = new Room("Start", "");
        Room next = new Room("Ziel", "");
        start.connect(Direction.NORTH, next);
        Player player = new Player(start);

        String out = player.move(Direction.NORTH);

        assertTrue(out.contains("Raum: Ziel"));
    }

    @Test
    void move_should_return_wall_message_when_missing_exit() {
        Room start = new Room("Start", "");
        Player player = new Player(start);

        String out = player.move(Direction.WEST);

        assertEquals("Dort ist eine Wand.", out);
    }

    @Test
    void move_from_null_room_should_return_empty_string() {
        Player player = new Player(null);

        String out = player.move(Direction.NORTH);

        assertEquals("", out);
    }

    @Test
    void inventory_nimm_and_lege_flow_should_update_status_and_room() {
        Room room = new Room("R", "");
        Potion potion = new SmallHealingPotion();
        room.addPotion(potion);
        Player player = new Player(room);

        String out = player.takeItem(potion.getName());
        assertTrue(out.toLowerCase().contains("du nimmst"));
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 1/8 | Waffe: keine | Schaden: 10", player.getStatusString());
        assertEquals("Das gibt es hier nicht.", player.takeItem(potion.getName()));

        Weapon weapon = new Sword("Katana", 16);
        room.addWeapon(weapon);
        player.takeItem(weapon.getName());
        String drop = player.dropItem(weapon.getName());
        assertTrue(drop.toLowerCase().contains("du legst ab"));
        assertEquals("Das hast du nicht im Inventar.", player.dropItem(weapon.getName()));
    }

    @Test
    void usePotion_healing_should_not_exceed_maxHp_and_remove_item() {
        Room room = new Room("R", "");
        Potion potion = new LargeHealingPotion();
        room.addPotion(potion);
        Player player = new Player(room);
        player.takeItem(potion.getName());

        String resp = player.usePotion(potion.getName());
        assertEquals("Du bist nicht verletzt.", resp);
        assertTrue(room.removePotionByName(potion.getName()).isEmpty());
    }

    @Test
    void usePotion_when_full_hp_should_fail_with_not_injured_message() {
        Room room = new Room("R", "");
        Potion potion = new SmallHealingPotion();
        room.addPotion(potion);
        Player player = new Player(room);
        player.takeItem(potion.getName());

        String resp = player.usePotion(potion.getName());
        assertEquals("Du bist nicht verletzt.", resp);
    }

    @Test
    void usePotion_chakra_should_increase_unlimited_and_remove_item() {
        Room room = new Room("R", "");
        Potion firstChakraPotion = new LargeChakraElixir();
        room.addPotion(firstChakraPotion);
        Player player = new Player(room);
        player.takeItem(firstChakraPotion.getName());

        String resp1 = player.usePotion(firstChakraPotion.getName());
        assertTrue(resp1.contains("Chakra: 60"));
        
    }

    @Test
    void equipWeapon_should_set_weapon_and_reflect_in_inventory_and_status() {
        Room room = new Room("R", "");
        Weapon weapon = new Sword("Katana", 16);
        room.addWeapon(weapon);
        Player player = new Player(room);
        player.takeItem(weapon.getName());

        String resp = player.equipWeapon(weapon.getName());
        assertTrue(resp.contains("Du rüstest aus:"));
        String status = player.getStatusString();
        assertTrue(status.contains("Waffe: Katana"));
        assertTrue(status.contains("Schaden: 26"));
    }
}


