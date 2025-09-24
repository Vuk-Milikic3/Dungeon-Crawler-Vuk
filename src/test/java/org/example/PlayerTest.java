package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    void move_returnsNewRoomDescription_onValidExit() {
        Room start = new Room("Start", "");
        Room next = new Room("Ziel", "");
        start.connect(Direction.NORTH, next);
        Player player = new Player(start);

        String out = player.move(Direction.NORTH);

        assertTrue(out.contains("Raum: Ziel"));
    }

    @Test
    void move_returnsWallMessage_onMissingExit() {
        Room start = new Room("Start", "");
        Player player = new Player(start);

        String out = player.move(Direction.WEST);

        assertEquals("Dort ist eine Wand.", out);
    }

    @Test
    void move_fromNullRoom_returnsEmptyString() {
        Player player = new Player(null);

        String out = player.move(Direction.NORTH);

        assertEquals("", out);
    }

    @Test
    void inventory_nimm_and_lege_flow() {
        Room r = new Room("R", "");
        Potion p = new SmallHealingPotion();
        r.addPotion(p);
        Player player = new Player(r);

        String out = player.takeItem(p.getName());
        assertTrue(out.toLowerCase().contains("du nimmst"));
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 1/8", player.getStatusString());
        assertEquals("Das gibt es hier nicht.", player.takeItem(p.getName()));

        Weapon w = new Sword("Katana", 16);
        r.addWeapon(w);
        player.takeItem(w.getName());
        String drop = player.dropItem(w.getName());
        assertTrue(drop.toLowerCase().contains("du legst ab"));
        assertEquals("Das hast du nicht im Inventar.", player.dropItem(w.getName()));
    }
}


