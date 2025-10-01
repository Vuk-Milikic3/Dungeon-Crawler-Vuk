package org.example.Room;

import org.example.Enemy.BasicEnemy;
import org.example.Enemy.Enemy;
import org.example.Item.Potion.Potion;
import org.example.Item.Potion.Heal.SmallHealingPotion;
import org.example.Item.Weapon.Sword;
import org.example.Item.Weapon.Weapon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

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

        Weapon weapon = new Sword("Katana", 16);
        r.addWeapon(weapon);
        var outWeapon = r.removeWeaponByName(weapon.getName());
        assertTrue(outWeapon.isPresent());
        assertTrue(r.removeWeaponByName(weapon.getName()).isEmpty());
    }

    @Test
    void addEnemy_should_add_enemy_to_room() {
        Room room = new Room("Test", "");
        Enemy enemy = new BasicEnemy("Ninja", 80, 12);
        room.addEnemy(enemy);
        assertTrue(room.hasEnemies());
    }

    @Test
    void getEnemyByName_should_return_enemy_when_exists() {
        Room room = new Room("Test", "");
        Enemy enemy = new BasicEnemy("Ninja", 80, 12);
        room.addEnemy(enemy);
        var result = room.getEnemyByName("Ninja");
        assertTrue(result.isPresent());
        assertEquals("Ninja", result.get().getName());
    }

    @Test
    void getEnemyByName_should_return_empty_when_not_exists() {
        Room room = new Room("Test", "");
        var result = room.getEnemyByName("Ninja");
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllEnemies_should_return_all_enemies() {
        Room room = new Room("Test", "");
        Enemy enemy1 = new BasicEnemy("Ninja1", 80, 12);
        Enemy enemy2 = new BasicEnemy("Ninja2", 90, 15);
        room.addEnemy(enemy1);
        room.addEnemy(enemy2);
        var enemies = room.getAllEnemies();
        assertEquals(2, enemies.size());
    }

    @Test
    void toString_should_show_defeated_enemies() {
        Room room = new Room("Test", "");
        Enemy enemy = new BasicEnemy("Ninja", 50, 10);
        room.addEnemy(enemy);
        enemy.takeDamage(50);
        String result = room.toString();
        assertTrue(result.contains("Ninja (besiegt)"));
    }
}


