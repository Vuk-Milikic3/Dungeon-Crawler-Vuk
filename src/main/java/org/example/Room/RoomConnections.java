package org.example.Room;

import org.example.Enemy.BasicEnemy;
import org.example.Enemy.EliteEnemy;
import org.example.Item.Potion.Chakra.LargeChakraElixir;
import org.example.Item.Potion.Chakra.SmallChakraElixir;
import org.example.Item.Potion.Heal.LargeHealingPotion;
import org.example.Item.Potion.Heal.SmallHealingPotion;
import org.example.Item.Weapon.Sword;
import org.example.Item.Weapon.ThrowingKnife;

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

        kerker.addPotion(new SmallHealingPotion());
        waffenkammer.addPotion(new SmallChakraElixir());
        bibliothek.addPotion(new LargeHealingPotion());
        bibliothek.addPotion(new LargeChakraElixir());

        kerker.addWeapon(new ThrowingKnife("Kunai-(6)", 8));
        waffenkammer.addWeapon(new Sword("Kurzschwert", 12));
        waffenkammer.addWeapon(new ThrowingKnife("Shuriken-(6)", 7));
        bibliothek.addWeapon(new Sword("Katana", 16));

        waffenkammer.addEnemy(new BasicEnemy("Hidden-Sand-Ninja", 80, 12));
        bibliothek.addEnemy(new EliteEnemy("Obito-Uchiha", 120, 20));
    }

    public Room getStartRoom() {
        return kerker;
    }
}


