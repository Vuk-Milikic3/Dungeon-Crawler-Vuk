package org.example;

public enum Direction {
    NORTH("norden"),
    SOUTH("süden"),
    EAST("osten"),
    WEST("westen");

    private final String label;

    Direction(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}


