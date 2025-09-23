package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;


public class Room {
    private final String name;
    private final String beschreibung;
    private final Map<Direction, Room> exits = new LinkedHashMap<>();
    private final Map<String, List<Item>> lootByName = new LinkedHashMap<>();

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

    private void addItem(Item item) {
        String key = item.getName() == null ? "" : item.getName().toLowerCase();
        lootByName.computeIfAbsent(key, k -> new LinkedList<>()).add(item);
    }

    public Potion removePotionByName(String name) {
        if (name == null) return null;
        List<Item> bucket = lootByName.get(name.toLowerCase());
        if (bucket == null || bucket.isEmpty()) return null;
        for (int i = 0; i < bucket.size(); i++) {
            Item it = bucket.get(i);
            if (it instanceof Potion) {
                bucket.remove(i);
                if (bucket.isEmpty()) lootByName.remove(name.toLowerCase());
                return (Potion) it;
            }
        }
        return null;
    }

    public Weapon removeWeaponByName(String name) {
        if (name == null) return null;
        List<Item> bucket = lootByName.get(name.toLowerCase());
        if (bucket == null || bucket.isEmpty()) return null;
        for (int i = 0; i < bucket.size(); i++) {
            Item it = bucket.get(i);
            if (it instanceof Weapon) {
                bucket.remove(i);
                if (bucket.isEmpty()) lootByName.remove(name.toLowerCase());
                return (Weapon) it;
            }
        }
        return null;
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
        for (List<Item> bucket : lootByName.values()) {
            if (bucket.isEmpty()) continue;
            Item first = bucket.get(0);
            String displayName = first.getName();
            if (first instanceof Potion) potionNames.add(displayName);
            else if (first instanceof Weapon) weaponNames.add(displayName);
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


