package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class Room {
    private final String name;
    private final String beschreibung;
    private final Map<String, Room> exits = new LinkedHashMap<>();

    public Room(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public void connect(RoomConnections.Direction direction, Room target) {
        exits.put(direction.label(), target);
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


