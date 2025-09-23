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

        kerker.addPotion(Potions.newHealPotionSmall());
        waffenkammer.addPotion(Potions.newChakraPotionSmall());
        bibliothek.addPotion(Potions.newHealPotionLarge());
        bibliothek.addPotion(Potions.newChakraPotionLarge());

        kerker.addWeapon(Weapons.newKunaiStack());
        waffenkammer.addWeapon(Weapons.newKurzschwert());
        waffenkammer.addWeapon(Weapons.newShurikenStack());
        bibliothek.addWeapon(Weapons.newKatana());

    }

    public Room getStartRoom() {
        return kerker;
    }
}


