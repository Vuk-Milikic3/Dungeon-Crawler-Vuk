package org.example.Room;

import org.example.Enemy.Enemy;
import org.example.Item.Item;
import org.example.Item.Potion.Potion;
import org.example.Item.Weapon.Weapon;

import java.util.*;


public class Room {
    private final String name;
    private final String beschreibung;
    private final Map<Direction, Room> exits = new LinkedHashMap<>();
    private final Map<String, Item> lootByName = new LinkedHashMap<>();
    private final Map<String, Enemy> enemiesByName = new LinkedHashMap<>();

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

    public void addEnemy(Enemy enemy) {
        if (enemy == null) return;
        String key = enemy.getName() == null ? "" : enemy.getName().toLowerCase();
        enemiesByName.put(key, enemy);
    }


    public Optional<Potion> removePotionByName(String name) {
        if (name == null) {
            return Optional.empty();
        }

        Item item = lootByName.get(name.toLowerCase());
        return Optional.ofNullable(item)
                .filter(Potion.class::isInstance)
                .map(item2 -> {
                    lootByName.remove(name.toLowerCase());
                    return (Potion) item2;
                });
    }


    public Optional<Weapon> removeWeaponByName(String name) {
        if (name == null) {
            return Optional.empty();
        }
        Item item = lootByName.get(name.toLowerCase());
        return Optional.ofNullable(item)
                .filter(Weapon.class::isInstance)
                .map(item2 -> {
                    lootByName.remove(name.toLowerCase());
                    return (Weapon) item2;
                });
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
        for (Item item : lootByName.values()) {
            String displayName = item.getName();
            if (item instanceof Potion) potionNames.add(displayName);
            else if (item instanceof Weapon) weaponNames.add(displayName);
        }
        if (!potionNames.isEmpty()) {
            sb.append("\n");
            sb.append("Tränke hier: ").append(String.join(", ", potionNames));
        }
        if (!weaponNames.isEmpty()) {
            sb.append("\n");
            sb.append("Waffen hier: ").append(String.join(", ", weaponNames));
        }
        List<String> enemyNames = new ArrayList<>();
        for (Enemy enemy : enemiesByName.values()) {
            enemyNames.add(enemy.getName());
        }
        if (!enemyNames.isEmpty()) {
            sb.append("\n");
            sb.append("Gegner hier: ").append(String.join(", ", enemyNames));
        }
        return sb.toString();
    }
}


