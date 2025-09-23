package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private final int capacity;
    private final List<Object> items = new ArrayList<>();

    public Inventory(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public int used() {
        return items.size();
    }

    public int capacity() {
        return capacity;
    }

    public boolean add(Object item) {
        if (item == null || isFull()) return false;
        return items.add(item);
    }

    public boolean remove(Object item) {
        if (item == null) return false;
        return items.remove(item);
    }

    public List<Object> list() {
        return Collections.unmodifiableList(items);
    }
}


