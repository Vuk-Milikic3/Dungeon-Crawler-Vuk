package org.example;

public class RoomConnections {
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

        kerker.addPotion(new PotionItem("Kleiner Kyubi-Heiltrank", 25, 0));
        waffenkammer.addPotion(new PotionItem("Kleines Chakra-Elixier", 0, 20));
        bibliothek.addPotion(new PotionItem("Grosser Weisen-Heiltrank", 60, 0));
        bibliothek.addPotion(new PotionItem("Grosses Weisen-Chakra-Elixier", 0, 50));

        kerker.addWeapon(new WeaponItem("Kunai (6)", 8, 6));
        waffenkammer.addWeapon(new WeaponItem("Kurzschwert", 12, 1));
        waffenkammer.addWeapon(new WeaponItem("Shuriken (6)", 7, 6));
        bibliothek.addWeapon(new WeaponItem("Katana", 16, 1));

    }

    public Room getStartRoom() {
        return kerker;
    }
}


