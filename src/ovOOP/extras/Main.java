package ovOOP.extras;

import java.util.Scanner;

public class Main {
    public static int userID = 0;

    public static void mainExtras(String[] args) {
        Scanner initialScanner = new Scanner(System.in);

        System.out.println("Earned " + GameSystem.playGame(6) + " By playing the minigame!");

        // MenuSystem.clear();

        // MenuSystem.startMenu(initialScanner);
    }

}