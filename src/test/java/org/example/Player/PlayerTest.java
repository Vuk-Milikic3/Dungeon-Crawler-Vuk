package org.example.Player;

import org.example.Enemy.BasicEnemy;
import org.example.Enemy.Enemy;
import org.example.Item.Potion.Chakra.LargeChakraElixir;
import org.example.Item.Potion.Heal.LargeHealingPotion;
import org.example.Item.Potion.Potion;
import org.example.Item.Potion.Heal.SmallHealingPotion;
import org.example.Item.Weapon.Sword;
import org.example.Item.Weapon.Weapon;
import org.example.Room.Direction;
import org.example.Room.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("HP: 100/100 | Chakra: 10 | Inventar: 1/8 | Waffe: keine | Schaden: 12", player.getStatusString());
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
        assertTrue(status.contains("Schaden: 28"));
    }

    @Test
    void chooseEnemy_should_set_current_enemy() {
        Room room = new Room("R", "");
        Enemy enemy = new BasicEnemy("Ninja", 80, 12);
        room.addEnemy(enemy);
        Player player = new Player(room);
        
        player.chooseEnemy("Ninja");
        player.startBattle();
        assertTrue(player.isInBattle());
    }

    @Test
    void attackEnemy_should_damage_enemy_when_in_battle() {
        Room room = new Room("R", "");
        Enemy enemy = new BasicEnemy("Ninja", 50, 12);
        room.addEnemy(enemy);
        Player player = new Player(room);
        player.chooseEnemy("Ninja");
        player.startBattle();
        
        String result = player.attackEnemy();
        assertTrue(result.contains("Du greifst"));
        assertTrue(result.contains("Ninja"));
        assertEquals(38, enemy.getCurrentHealth());
    }

    @Test
    void attackEnemy_should_end_battle_when_enemy_defeated() {
        Room room = new Room("R", "");
        Enemy enemy = new BasicEnemy("Ninja", 10, 5);
        room.addEnemy(enemy);
        Player player = new Player(room);
        player.chooseEnemy("Ninja");
        player.startBattle();
        
        String result = player.attackEnemy();
        assertTrue(result.contains("besiegen"));
        assertFalse(player.isInBattle());
        assertFalse(enemy.isAlive());
    }

    @Test
    void flee_should_end_battle_and_return_to_previous_room() {
        Room room1 = new Room("Room1", "");
        Room room2 = new Room("Room2", "");
        room1.connect(Direction.NORTH, room2);
        Enemy enemy = new BasicEnemy("Ninja", 80, 12);
        room2.addEnemy(enemy);
        Player player = new Player(room1);
        
        player.move(Direction.NORTH);
        player.chooseEnemy("Ninja");
        player.startBattle();
        
        String result = player.flee();
        assertTrue(result.contains("fliehst"));
        assertFalse(player.isInBattle());
        assertEquals("Room1", player.getCurrentRoom().toString().split("\n")[0].replace("Raum: ", ""));
    }

    @Test
    void currentRoomHasEnemies_should_return_false_when_all_defeated() {
        Room room = new Room("R", "");
        Enemy enemy = new BasicEnemy("Ninja", 10, 5);
        room.addEnemy(enemy);
        Player player = new Player(room);
        
        enemy.takeDamage(10);
        assertFalse(player.currentRoomHasEnemies());
    }
}


