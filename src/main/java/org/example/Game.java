package org.example;

import java.util.Scanner;

public class Game {
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
        return switch (normalized) {
            case "status" -> player.getPlayerStatus().toString();
            case "schauen" -> player.getCurrentRoomDescription();
            case "quit" -> {
                isStarted = false;
                yield "Spiel beendet. Auf Wiedersehen!";
            }
            default -> "Unbekannter Befehl";
        };
    }

    private void awaitFirstCommand() {
        while (isStarted) {
            System.out.print("Gib deinen ersten Befehl ein (Tipp: 'status' oder 'schauen'): ");
            String input = "";
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
            String response = processCommand(input);
            if ("Unbekannter Befehl".equals(response)) {
                System.out.println("Falscher Befehl eingegeben. Bitte gib einen gültigen Befehl ein.");
            } else if (!response.isEmpty()) {
                System.out.println(response);
            }
        }
    }
}
