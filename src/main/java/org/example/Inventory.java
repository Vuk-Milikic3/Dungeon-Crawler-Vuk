package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private final int capacity;
    private final List<Item> items = new ArrayList<>();

    public Inventory(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public int spaceUsed() {
        return items.size();
    }

    public int capacity() {
        return capacity;
    }

    public boolean add(Item item) {
        if (item == null || isFull()) return false;
        return items.add(item);
    }

    public boolean remove(Item item) {
        if (item == null) return false;
        return items.remove(item);
    }

    public List<Item> list() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventar (").append(spaceUsed()).append("/").append(capacity()).append(")\n");
        if (items.isEmpty()) {
            sb.append("Leer");
        } else {
            for (Item item : items) {
                sb.append(item.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}


