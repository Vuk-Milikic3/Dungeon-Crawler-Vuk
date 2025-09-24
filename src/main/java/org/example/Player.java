package org.example;

import java.util.Optional;

public class Player {
    private final PlayerStatus status;
    private Room currentRoom;
    private final Inventory inventory;
    private Weapon equippedWeapon;

    public Player(Room startRoom) {
        this.status = new PlayerStatus(100, 100, 10);
        this.currentRoom = startRoom;
        this.inventory = new Inventory(8);
    }

    public PlayerStatus getPlayerStatus() {
        return status;
    }

    public String getCurrentRoomDescription() {
        return currentRoom == null ? "" : currentRoom.toString();
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
        Optional<Potion> p = currentRoom.removePotionByName(name);
        if (p.isPresent()) {
            Potion potion = p.get();
            inventory.add(potion);
            return "Du nimmst: " + potion.getName();
        }
        Optional<Weapon> w = currentRoom.removeWeaponByName(name);
        if (w.isPresent()) {
            Weapon weapon = w.get();
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

    public String showInventory() {
        String base = inventory.toString();
        String weaponLine = equippedWeapon == null ? "Ausgerüstete Waffe: keine | Angriff: 10" :
                ("Ausgerüstete Waffe: " + equippedWeapon.getName() + " | Angriff: " + (10 + equippedWeapon.damage()));
        return base + "\n" + weaponLine;
    }

    public String getStatusString() {
        String weaponInfo = equippedWeapon == null ? "keine" : equippedWeapon.getName();
        return status.toString() + " | Inventar: " + inventory.spaceUsed() + "/" + inventory.capacity() + " | Waffe: " + weaponInfo;
    }

    public String usePotion(String name) {
        java.util.Optional<Item> found = inventory.findByName(name);
        if (found.isEmpty() || !(found.get() instanceof Potion)) {
            return "Du hast keinen passenden Trank im Inventar.";
        }
        Item item = found.get();
        if (item instanceof HealingPotion && !(item instanceof ChakraPotion)) {
            if (status.getCurrentHp() >= status.getMaxHp()) {
                return "Du bist nicht verletzt.";
            }
        }
        if (item instanceof HealingPotion) {
            status.heal(((HealingPotion) item).heal());
        }
        if (item instanceof ChakraPotion) {
            status.restoreChakra(((ChakraPotion) item).chakraPowerUp());
        }
        inventory.removeByName(name);
        return "Du verwendest: " + name + " | " + status.toString();
    }

    public String equipWeapon(String name) {
        java.util.Optional<Item> found = inventory.findByName(name);
        if (found.isEmpty() || !(found.get() instanceof Weapon)) {
            return "Diese Waffe hast du nicht im Inventar.";
        }
        equippedWeapon = (Weapon) found.get();
        return "Du rüstest aus: " + equippedWeapon.getName();
    }
}

