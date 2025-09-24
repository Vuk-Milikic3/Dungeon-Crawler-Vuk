package org.example;

public class Player {
    private final PlayerStatus status;
    private Room currentRoom;
    private final Inventory inventory;

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
        java.util.Optional<Potion> p = currentRoom.removePotionByName(name);
        if (p.isPresent()) {
            Potion potion = p.get();
            inventory.add(potion);
            return "Du nimmst: " + potion.getName();
        }
        java.util.Optional<Weapon> w = currentRoom.removeWeaponByName(name);
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
        return inventory.toString();
    }

    public String getStatusString() {
        return status.toString() + " | Inventar: " + inventory.spaceUsed() + "/" + inventory.capacity();
    }
}

