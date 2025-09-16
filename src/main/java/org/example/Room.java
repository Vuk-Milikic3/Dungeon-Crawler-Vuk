package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class Room {
    private final String name;
    private final String beschreibung;
    private final Map<String, Room> exits = new LinkedHashMap<>(); // why not HashMap and whats the difference? Wie ich verstanden habe machht LinkedHashMap alles in der liste der Reihe nach & HashMap macht es zufällig. Hier benutze ich LinkedHashMap weil ich die Reihenfolge der Ausgänge will.

    public Room(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public void connect(String direction, Room target) {
        exits.put(direction, target);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Raum: ").append(name).append("\n");
        sb.append(beschreibung).append("\n");
        if (exits.isEmpty()) {
            sb.append("Ausgänge: keine");
        } else {
            sb.append("Ausgänge: ").append(String.join(", ", exits.keySet()));
        }
        return sb.toString();
    }
}


