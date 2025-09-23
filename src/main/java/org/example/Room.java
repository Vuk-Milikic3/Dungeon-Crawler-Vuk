package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Room {
    private final String name;
    private final String beschreibung;
    private final Map<Direction, Room> exits = new LinkedHashMap<>();
    private final List<Potions> potions = new ArrayList<>();
    private final List<Weapons> weapons = new ArrayList<>();

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

    public void addPotion(Potions potion) {
        if (potion == null) return;
        potions.add(potion);
    }


    public void addWeapon(Weapons weapon) {
        if (weapon == null) return;
        weapons.add(weapon);
    }

    public Potions removePotionByName(String name) {
        if (name == null) return null;
        for (int i = 0; i < potions.size(); i++) {
            Potions p = potions.get(i);
            if (p.getName().equalsIgnoreCase(name)) {
                potions.remove(i);
                return p;
            }
        }
        return null;
    }

    public Weapons removeWeaponByName(String name) {
        if (name == null) return null;
        for (int i = 0; i < weapons.size(); i++) {
            Weapons w = weapons.get(i);
            if (w.getName().equalsIgnoreCase(name)) {
                weapons.remove(i);
                return w;
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
        if (!potions.isEmpty()) {
            sb.append("\n");
            List<String> potionNames = new ArrayList<>();
            for (Potions p : potions) {
                potionNames.add(p.getName());
            }
            sb.append("Tränke hier: ").append(String.join(", ", potionNames));
        }
        if (!weapons.isEmpty()) {
            sb.append("\n");
            List<String> weaponNames = new ArrayList<>();
            for (Weapons w : weapons) {
                weaponNames.add(w.getName());
            }
            sb.append("Waffen hier: ").append(String.join(", ", weaponNames));
        }
        return sb.toString();
    }
}


