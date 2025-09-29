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
        Room r = new Room("R", "");
        Potion p = new SmallHealingPotion();
        r.addPotion(p);
        Player player = new Player(r);

        String out = player.takeItem(p.getName());
        assertTrue(out.toLowerCase().contains("du nimmst"));
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 1/8 | Waffe: keine | Schaden: 10", player.getStatusString());
        assertEquals("Das gibt es hier nicht.", player.takeItem(p.getName()));

        Weapon w = new Sword("Katana", 16);
        r.addWeapon(w);
        player.takeItem(w.getName());
        String drop = player.dropItem(w.getName());
        assertTrue(drop.toLowerCase().contains("du legst ab"));
        assertEquals("Das hast du nicht im Inventar.", player.dropItem(w.getName()));
    }

    @Test
    void usePotion_healing_should_not_exceed_maxHp_and_remove_item() {
        Room r = new Room("R", "");
        Potion p = new LargeHealingPotion();
        r.addPotion(p);
        Player player = new Player(r);
        player.takeItem(p.getName());

        String resp = player.usePotion(p.getName());
        assertEquals("Du bist nicht verletzt.", resp);
        assertTrue(r.removePotionByName(p.getName()).isEmpty());
    }

    @Test
    void usePotion_when_full_hp_should_fail_with_not_injured_message() {
        Room r = new Room("R", "");
        Potion p = new SmallHealingPotion();
        r.addPotion(p);
        Player player = new Player(r);
        player.takeItem(p.getName());

        String resp = player.usePotion(p.getName());
        assertEquals("Du bist nicht verletzt.", resp);
    }

    @Test
    void usePotion_chakra_should_increase_unlimited_and_remove_item() {
        Room r = new Room("R", "");
        Potion c1 = new LargeChakraElixir();
        r.addPotion(c1);
        Player player = new Player(r);
        player.takeItem(c1.getName());

        String resp1 = player.usePotion(c1.getName());
        assertTrue(resp1.contains("Chakra: 60"));
        
    }

    @Test
    void equipWeapon_should_set_weapon_and_reflect_in_inventory_and_status() {
        Room r = new Room("R", "");
        Weapon w = new Sword("Katana", 16);
        r.addWeapon(w);
        Player player = new Player(r);
        player.takeItem(w.getName());

        String resp = player.equipWeapon(w.getName());
        assertTrue(resp.contains("Du rüstest aus:"));
        String status = player.getStatusString();
        assertTrue(status.contains("Waffe: Katana"));
        assertTrue(status.contains("Schaden: 26"));
    }
}


