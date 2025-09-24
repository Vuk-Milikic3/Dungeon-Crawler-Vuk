package org.example;

import java.util.*;


public class Room {
    private final String name;
    private final String beschreibung;
    private final Map<Direction, Room> exits = new LinkedHashMap<>();
    private final Map<String, Item> lootByName = new LinkedHashMap<>();

    public Room(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public void connect(Direction direction, Room target) {
        exits.put(direction, target);
    }

    public Room getNextRoom(Direction direction) {
        return exits.get(direction);
    }

    public void addPotion(Potion potion) {
        if (potion == null) return;
        addItem(potion);
    }


    public void addWeapon(Weapon weapon) {
        if (weapon == null) return;
        addItem(weapon);
    }

    public void addItem(Item item) {
        if (item == null) return;
        String key = item.getName() == null ? "" : item.getName().toLowerCase();
        lootByName.put(key, item);
    }


    public Optional<Potion> removePotionByName(String name) {
        if (name == null) {
            return Optional.empty();
        }

        Item it = lootByName.get(name.toLowerCase());
        if (it == null) {
            return Optional.empty();
        }
        if (it instanceof Potion) {
            lootByName.remove(name.toLowerCase());
            return Optional.of((Potion) it);
        }
        return Optional.empty();
    }


    public Optional<Weapon> removeWeaponByName(String name) {
        if (name == null) {
            return Optional.empty();
        }
        Item it = lootByName.get(name.toLowerCase());
        if (it == null) {
            return Optional.empty();
        }
        if (it instanceof Weapon) {
            lootByName.remove(name.toLowerCase());
            return Optional.of((Weapon) it);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Raum: ").append(name).append("\n");
        sb.append(beschreibung).append("\n");
        if (exits.isEmpty()) {
            sb.append("Ausgänge: keine");
        } else {
            List<String> directionLabels = new ArrayList<>();
            for (Direction d : exits.keySet()) {
                directionLabels.add(d.label());
            }
            sb.append("Ausgänge: ").append(String.join(", ", directionLabels));
        }
        List<String> potionNames = new ArrayList<>();
        List<String> weaponNames = new ArrayList<>();
        for (Item it : lootByName.values()) {
            String displayName = it.getName();
            if (it instanceof Potion) potionNames.add(displayName);
            else if (it instanceof Weapon) weaponNames.add(displayName);
        }
        if (!potionNames.isEmpty()) {
            sb.append("\n");
            sb.append("Tränke hier: ").append(String.join(", ", potionNames));
        }
        if (!weaponNames.isEmpty()) {
            sb.append("\n");
            sb.append("Waffen hier: ").append(String.join(", ", weaponNames));
        }
        return sb.toString();
    }
}


