package org.example.Player;

import org.example.*;
import org.example.Item.Item;
import org.example.Item.Potion.Chakra.ChakraPotion;
import org.example.Item.Potion.Heal.HealingPotion;
import org.example.Item.Potion.Potion;
import org.example.Item.Weapon.Weapon;
import org.example.Room.Direction;
import org.example.Room.Room;

import java.util.Optional;

public class Player {
    private final PlayerHealth health;
    private Room currentRoom;
    private final Inventory inventory;
    private final PlayerDamage damage;

    public Player(Room startRoom) {
        this.health = new PlayerHealth(100, 100);
        this.currentRoom = startRoom;
        this.inventory = new Inventory(8);
        this.damage = new PlayerDamage(10, 10);
    }

    public PlayerHealth getPlayerHealth() {
        return health;
    }

    public String getCurrentRoomDescription() {
        return currentRoom == null ? "" : currentRoom.toString();
    }

    public String showInventory() {
        return inventory.toString();
    }

    public String move(Direction direction) {
        if (currentRoom == null) {
            return "";
        }
        Room nextRoom = currentRoom.getNextRoom(direction);
        if (nextRoom == null) {
            return "Dort ist eine Wand.";
        }
        currentRoom = nextRoom;
        return currentRoom.toString();
    }

    public String takeItem(String name) {
        if (currentRoom == null) return "";
        if (inventory.isFull()) {
            return "Dein Inventar ist voll.";
        }
        Optional<Potion> optionalPotion = currentRoom.removePotionByName(name);
        if (optionalPotion.isPresent()) {
            Potion potion = optionalPotion.get();
            inventory.add(potion);
            return "Du nimmst: " + potion.getName();
        }
        Optional<Weapon> optionalWeapon = currentRoom.removeWeaponByName(name);
        if (optionalWeapon.isPresent()) {
            Weapon weapon = optionalWeapon.get();
            inventory.add(weapon);
            return "Du nimmst: " + weapon.getName();
        }
        return "Das gibt es hier nicht.";
    }

    public String dropItem(String name) {
        if (currentRoom == null) return "";
        Item found = null;
        for (Item item : inventory.list()) {
            String n = item.getName();
            if (n != null && n.equalsIgnoreCase(name)) {
                found = item;
                break;
            }
        }
        if (found == null) {
            return "Das hast du nicht im Inventar.";
        }
        inventory.remove(found);
        
        currentRoom.addItem(found);
        return "Du legst ab: " + name;
    }

    public String getStatusString() {
        Weapon equippedWeapon = damage.getEquippedWeapon();
        String base = health.toString() + " | Chakra: " + damage.getChakra();
        String weaponName = (equippedWeapon == null) ? "keine" : equippedWeapon.getName();
        return base
                + " | Inventar: " + inventory.spaceUsed() + "/" + inventory.capacity()
                + " | Waffe: " + weaponName
                + " | Schaden: " + damage.getDamage();
    }

    public String usePotion(String name) {
        Optional<Item> optionalItem = inventory.findByName(name);
        if (optionalItem.isEmpty() || !(optionalItem.get() instanceof Potion)) {
            return "Du hast keinen passenden Trank im Inventar.";
        }
        Item item = optionalItem.get();
        if (item instanceof HealingPotion) {
            if (health.getCurrentHp() >= health.getMaxHp()) {
                return "Du bist nicht verletzt.";
            }
        }
        if (item instanceof HealingPotion) {
            health.heal(((HealingPotion) item).heal());
        }
        if (item instanceof ChakraPotion) {
            damage.restoreChakra(((ChakraPotion) item).chakraPowerUp());
        }
        inventory.removeByName(name);
        String statusNow = health.toString() + " | Chakra: " + damage.getChakra();
        return "Du verwendest: " + name + " | " + statusNow;
    }

    public String equipWeapon(String name) {
        Optional<Item> optionalItem = inventory.findByName(name);
        if (optionalItem.isEmpty() || !(optionalItem.get() instanceof Weapon)) {
            return "Diese Waffe hast du nicht im Inventar.";
        }
        Weapon weapon = (Weapon) optionalItem.get();
        damage.equip(weapon);
        return "Du rüstest aus: " + weapon.getName() + " | Schaden: " + damage.getDamage();
    }
}

