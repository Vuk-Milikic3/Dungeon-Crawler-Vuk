package org.example.Room;

public enum Direction {
    NORTH("(w) Norden"),
    SOUTH("(s) Süden"),
    EAST("(d) Osten"),
    WEST("(a) Westen");

    private final String label;

    Direction(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}


