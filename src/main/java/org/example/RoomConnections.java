package org.example;

public class RoomConnections {
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
    private final Room kerker;
    private final Room waffenkammer;
    private final Room bibliothek;

    public RoomConnections() {
        kerker = new Room("Kerker", "Feuchte Steinmauern, spärliches Licht und rostige Gitterstäbe.");
        waffenkammer = new Room("Waffenkammer", "Regale voller alter Waffen, einige noch benutzbar.");
        bibliothek = new Room("Bibliothek", "Staubige Regale mit uralten Schriftrollen und Büchern.");

        kerker.connect(Direction.NORTH, waffenkammer);
        waffenkammer.connect(Direction.SOUTH, kerker);

        waffenkammer.connect(Direction.EAST, bibliothek);
        bibliothek.connect(Direction.WEST, waffenkammer);

    }

    public Room getStartRoom() {
        return kerker;
    }
}


