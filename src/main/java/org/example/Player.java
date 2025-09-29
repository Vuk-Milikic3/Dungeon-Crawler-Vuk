package org.example;

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

    public String getStatusString() {
        Weapon w = damage.getEquippedWeapon();
        String base = health.toString() + " | Chakra: " + damage.getChakra();
        String weaponName = (w == null) ? "keine" : w.getName();
        return base
                + " | Inventar: " + inventory.spaceUsed() + "/" + inventory.capacity()
                + " | Waffe: " + weaponName
                + " | Schaden: " + damage.getAttack();
    }

    public String usePotion(String name) {
        Optional<Item> found = inventory.findByName(name);
        if (found.isEmpty() || !(found.get() instanceof Potion)) {
            return "Du hast keinen passenden Trank im Inventar.";
        }
        Item item = found.get();
        if (item instanceof HealingPotion && !(item instanceof ChakraPotion)) {
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
        Optional<Item> found = inventory.findByName(name);
        if (found.isEmpty() || !(found.get() instanceof Weapon)) {
            return "Diese Waffe hast du nicht im Inventar.";
        }
        Weapon w = (Weapon) found.get();
        damage.equip(w);
        return "Du rüstest aus: " + w.getName();
    }
}

