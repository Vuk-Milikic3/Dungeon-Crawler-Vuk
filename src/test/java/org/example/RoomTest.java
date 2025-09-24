package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {

    @ParameterizedTest
    @EnumSource(Direction.class)
    void connect_should_use_direction_label_in_output(Direction direction) {
        Room a = new Room("A", "");
        Room b = new Room("B", "");
        a.connect(direction, b);

        String out = a.toString().toLowerCase();
        assertTrue(out.contains("ausgänge:"));
        assertTrue(out.contains(direction.label().toLowerCase()));
    }

    @Test
    void room_without_connections_should_show_no_exits() {
        Room a = new Room("A", "");
        String out = a.toString().toLowerCase();
        assertTrue(out.contains("ausgänge:"));
        assertTrue(out.contains("ausgänge: keine"));
    }

    @Test
    void room_with_north_connection_should_show_north_exit() {
        Room a = new Room("A", "");
        Room b = new Room("B", "");
        a.connect(Direction.NORTH, b);
        String out = a.toString().toLowerCase();
        assertTrue(out.contains("ausgänge:"));
        assertTrue(out.contains("norden"));
    }

    @Test
    void remove_item_by_name_should_remove_and_return_then_be_empty() {
        Room r = new Room("R", "");
        Potion p = new SmallHealingPotion();
        r.addPotion(p);
        var out = r.removePotionByName(p.getName());
        assertTrue(out.isPresent());
        assertTrue(r.removePotionByName(p.getName()).isEmpty());

        Weapon w = new Sword("Katana", 16);
        r.addWeapon(w);
        var outW = r.removeWeaponByName(w.getName());
        assertTrue(outW.isPresent());
        assertTrue(r.removeWeaponByName(w.getName()).isEmpty());
    }
}


