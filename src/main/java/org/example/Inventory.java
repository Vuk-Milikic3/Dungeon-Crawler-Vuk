package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Inventory {
    private final int capacity;
    private final Map<String, Item> itemsByName = new LinkedHashMap<>();

    public Inventory(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return itemsByName.size() >= capacity;
    }

    public int spaceUsed() {
        return itemsByName.size();
    }

    public int capacity() {
        return capacity;
    }

    public boolean add(Item item) {
        if (item == null || isFull()) return false;
        String key = item.getName() == null ? "" : item.getName().toLowerCase();
        if (itemsByName.containsKey(key)) return false;
        itemsByName.put(key, item);
        return true;
    }

    public boolean remove(Item item) {
        if (item == null) return false;
        String key = item.getName() == null ? "" : item.getName().toLowerCase();
        return itemsByName.remove(key) != null;
    }

    public Optional<Item> removeByName(String name) {
        if (name == null) return Optional.empty();
        String key = name.toLowerCase();
        Item removed = itemsByName.remove(key);
        return Optional.ofNullable(removed);
    }

    public Optional<Item> findByName(String name) {
        if (name == null) return Optional.empty();
        return Optional.ofNullable(itemsByName.get(name.toLowerCase()));
    }

    public List<Item> list() {
        return Collections.unmodifiableList(new ArrayList<>(itemsByName.values()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventar (").append(spaceUsed()).append("/").append(capacity()).append(")\n");
        if (itemsByName.isEmpty()) {
            sb.append("Leer");
        } else {
            for (Item item : itemsByName.values()) {
                sb.append(item.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}


