package org.example;

import org.example.Player.Player;
import org.example.Room.Direction;
import org.example.Room.RoomConnections;

import java.util.Scanner;

public class Game {
    private static final String COMMAND_TAKE = "nimm";
    private static final String COMMAND_DROP = "lege";
    private static final String COMMAND_USE = "nutze";
    private static final String COMMAND_EQUIP = "rüste";
    private static final String COMMAND_EQUIP_SUFFIX = "aus";
    private static final String COMMAND_STATUS = "status";
    private static final String COMMAND_LOOK = "schauen";
    private static final String COMMAND_INVENTORY = "inventar";
    private static final String COMMAND_NORTH = "w";
    private static final String COMMAND_WEST = "a";
    private static final String COMMAND_SOUTH = "s";
    private static final String COMMAND_EAST = "d";
    private static final String COMMAND_QUIT = "quit";
    private static final String COMMAND_ATTACK = "angreife";
    private static final String COMMAND_FLEE = "fliehen";
    private static final String COMMAND_HELP = "hilfe";
    private boolean isStarted = false;
    private final Scanner scanner = new Scanner(System.in);
    private final RoomConnections world = new RoomConnections();
    private final Player player = new Player(world.getStartRoom());

    public boolean isStarted() {
        return isStarted;
    }

    public void start() {
        isStarted = true;
        showStartScreen();
        awaitFirstCommand();
    }

    private void showStartScreen() {
        System.out.println("Willkommen, Abenteurer!");
        System.out.println();
        System.out.println("Die Legende von Kuroshi im Valley of the Cursed Seals ");
        System.out.println();
        System.out.println("Vor langer Zeit tobte ein Krieg zwischen den verborgenen Dörfern. Unter ihnen war Kagemura, ein kleines, aber stolzes Dorf, das für seine einzigartigen Wind- und Erde-Jutsus berühmt war. Die Shinobi aus Kagemura galten als unerschütterlich wie Fels und schnell wie der Sturm – eine gefährliche Kombination, die vielen Feinden Furcht einflösste.");
        System.out.println();
        System.out.println("Doch im Schatten dieser Kriege erhob sich ein Mann: General Akurai, ein abtrünniger Ninja, der die Macht der verfluchten Siegel für sich entdeckte. Mit seiner Armee zog er durch das Land und brachte ganze Dörfer unter seine Kontrolle. Sein Blick fiel schließlich auf Kagemura, und ein erbarmungsloser Krieg begann.");
        System.out.println();
        System.out.println("Inmitten dieser Schlachten stand Kuroshi, ein junger Ninja, der schon früh die Talente seines Dorfes in sich vereinte – den scharfen Wind, der wie Klingen schneidet, und die unerschütterliche Erde, die Schutz und Stärke verleiht. Doch auch Kuroshi konnte den Untergang nicht aufhalten. Bei einer letzten Schlacht wurde er besiegt, gefangen genommen und in das finstere Valley of the Cursed Seals geworfen.");
        System.out.println();
        System.out.println("Dieses Tal ist kein gewöhnlicher Ort. Die Siegel, die an uralten Felsen brennen, verformen die Natur und verderben jeden, der zu lange darin verweilt. Aus den Tiefen steigen feindliche Ninja auf – Krieger, die dem Willen des Generals folgen. Manche tragen die Zeichen des Fluchs, ihre Jutsus verzerrt und tödlicher als je zuvor.");
        System.out.println();
        System.out.println("Doch Kuroshi gibt nicht auf. In seinem Herzen trägt er den Schwur, eines Tages nach Kagemura zurückzukehren, um sein Dorf wieder aufzubauen und den Frieden zurückzubringen. Um dies zu erreichen, muss er dem Tal entkommen, seine Kräfte zurückgewinnen und sich dem General im entscheidenden Kampf stellen.");
        System.out.println();
        System.out.println("Dies ist der Beginn deiner Reise, Kuroshi.");
        System.out.println("Dein Ziel:");
        System.out.println("Entkomme aus dem Valley of the Cursed Seals.");
        System.out.println("Besiege den General, der den Krieg begann.");
        System.out.println("Kehre zurück nach Kagemura – als Held deines Volkes.");
        System.out.println();
    }

    public String processCommand(String command) {
        if (command == null) {
            return "";
        }
        String normalized = command.trim().toLowerCase();
        String[] parts = command.split(" ");
        
        if (player.isInBattle()) {
            if (parts.length == 2 && parts[0].equalsIgnoreCase(COMMAND_ATTACK)) {
                return player.attackEnemy(parts[1].trim());
            }
            if (normalized.equals(COMMAND_FLEE)) {
                return player.flee();
            }
            return "Im Kampf kannst du nur 'angreife <gegner>' oder 'fliehen' verwenden.";
        }
        
        if (parts.length == 2 && parts[0].equalsIgnoreCase(COMMAND_TAKE)) {
            return player.takeItem(parts[1].trim());
        }
        if (parts.length == 2 && parts[0].equalsIgnoreCase(COMMAND_DROP)) {
            return player.dropItem(parts[1].trim());
        }
        if (parts.length == 2 && parts[0].equalsIgnoreCase(COMMAND_USE)) {
            return player.usePotion(parts[1].trim());
        }
        if (parts.length == 3 && parts[0].equalsIgnoreCase(COMMAND_EQUIP) && parts[2].equalsIgnoreCase(COMMAND_EQUIP_SUFFIX)) {
            return player.equipWeapon(parts[1].trim());
        }
        return switch (normalized) {
            case COMMAND_STATUS -> player.getStatusString();
            case COMMAND_LOOK -> player.getCurrentRoomDescription();
            case COMMAND_INVENTORY -> player.showInventory();
            case COMMAND_NORTH -> player.move(Direction.NORTH);
            case COMMAND_WEST -> player.move(Direction.WEST);
            case COMMAND_SOUTH -> player.move(Direction.SOUTH);
            case COMMAND_EAST -> player.move(Direction.EAST);
            case COMMAND_HELP -> {
                showCommandHelp();
                yield "";
            }
            case COMMAND_QUIT -> {
                isStarted = false;
                yield "Spiel beendet. Auf Wiedersehen!";
            }
            default -> "Unbekannter Befehl";
        };
    }

    private void showCommandHelp() {
        System.out.println("=== BEFEHLE ===");
        System.out.println();
        System.out.println("Bewegung:");
        System.out.println("  w - Gehe nach Norden");
        System.out.println("  a - Gehe nach Westen");
        System.out.println("  s - Gehe nach Süden");
        System.out.println("  d - Gehe nach Osten");
        System.out.println();
        System.out.println("Information:");
        System.out.println("  status - Zeige deinen Status");
        System.out.println("  schauen - Schaue dich im Raum um");
        System.out.println("  inventar - Zeige dein Inventar");
        System.out.println();
        System.out.println("Gegenstände:");
        System.out.println("  nimm <gegenstand> - Nimm einen Gegenstand auf");
        System.out.println("  lege <gegenstand> - Lege einen Gegenstand ab");
        System.out.println("  nutze <trank> - Verwende einen Trank");
        System.out.println("  rüste <waffe> aus - Rüste eine Waffe aus");
        System.out.println();
        System.out.println("Kampf:");
        System.out.println("  angreife <gegner> - Greife einen Gegner an");
        System.out.println("  fliehen - Fliehe aus dem Kampf");
        System.out.println();
        System.out.println("Sonstiges:");
        System.out.println("  quit - Spiel beenden");
        System.out.println("  hilfe - Zeige die Befehle an");
        System.out.println();
    }

    private void awaitFirstCommand() {
        boolean helpShown = false;
        
        while (isStarted && !helpShown) {
            System.out.print("Gib deinen ersten Befehl ein (Tipp: 'hilfe'): ");
            String input = "";
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
            if (input.trim().equalsIgnoreCase(COMMAND_HELP)) {
                showCommandHelp();
                helpShown = true;
            } else {
                System.out.println("Bitte gib den Befehl 'hilfe' ein.\n");
            }
        }
        
        while (isStarted) {
            System.out.print("Gib deinen Befehl ein: ");
            String input = "";
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
            String response = processCommand(input);
            if ("Unbekannter Befehl".equals(response)) {
                System.out.println("Falscher Befehl eingegeben. Bitte gib einen gültigen Befehl ein.");
            } else if (!response.isEmpty()) {
                System.out.println(response+"\n");
            }
            
            if (player.currentRoomHasEnemies() && !player.isInBattle()) {
                System.out.println("In diesem Raum ist ein Gegner! Möchtest du gegen ihn kämpfen, damit du neue Schätze finden kannst im Raum? (y/n):");
                String battleResponse = "";
                if (scanner.hasNextLine()) {
                    battleResponse = scanner.nextLine().trim().toLowerCase();
                }
                if (battleResponse.equals("y")) {
                    player.startBattle();
                    System.out.println("Der Kampf beginnt!\n");
                } else if (battleResponse.equals("n")) {
                    String result = player.declineBattle();
                    System.out.println(result+"\n");
                } else {
                    System.out.println("Bitte antworte mit y oder n.\n");
                }
            }
        }
    }
}
