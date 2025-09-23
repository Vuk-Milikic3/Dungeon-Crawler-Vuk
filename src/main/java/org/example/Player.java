package org.example;

public class Player {
    private final PlayerStatus status;
    private Room currentRoom;
    private final Inventory inventory;

    public Player(Room startRoom) {
        this.status = new PlayerStatus(100, 100, 10, 0, 8);
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
        Potions p = currentRoom.removePotionByName(name);
        if (p != null) {
            inventory.add(p);
            status.setInventoryUsed(inventory.used());
            return "Du nimmst: " + p.getName();
        }
        Weapons w = currentRoom.removeWeaponByName(name);
        if (w != null) {
            inventory.add(w);
            status.setInventoryUsed(inventory.used());
            return "Du nimmst: " + w.getName();
        }
        return "Das gibt es hier nicht.";
    }

    public String dropItem(String name) {
        if (currentRoom == null) return "";
        Object found = null;
        for (Object o : inventory.list()) {
            String n = (o instanceof Potions) ? ((Potions) o).getName() : (o instanceof Weapons) ? ((Weapons) o).getName() : null;
            if (n != null && n.equalsIgnoreCase(name)) {
                found = o;
                break;
            }
        }
        if (found == null) {
            return "Das hast du nicht im Inventar.";
        }
        inventory.remove(found);
        status.setInventoryUsed(inventory.used());
        if (found instanceof Potions) {
            currentRoom.addPotion((Potions) found);
        } else if (found instanceof Weapons) {
            currentRoom.addWeapon((Weapons) found);
        }
        return "Du legst ab: " + name;
    }

    public String showInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventar (" ).append(inventory.used()).append("/").append(inventory.capacity()).append(")\n");
        if (inventory.list().isEmpty()) {
            sb.append("Leer");
        } else {
            for (Object o : inventory.list()) {
                if (o instanceof Potions) {
                    sb.append("- Trank: ").append(((Potions) o).getName()).append("\n");
                } else if (o instanceof Weapons) {
                    sb.append("- Waffe: ").append(((Weapons) o).getName()).append("\n");
                }
            }
        }
        return sb.toString();
    }
}

