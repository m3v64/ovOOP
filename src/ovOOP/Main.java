package ovOOP;

import java.util.Scanner;

public class Main {
    public static int userID = 0;
    public static void main(String[] args) {
        // Preload all JSON data at startup
        DataSystem.preloadAllData();

        Scanner initialScanner = new Scanner(System.in);

        MenuSystem.clear();

        MenuSystem.startMenu(initialScanner);
    }
}