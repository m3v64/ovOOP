package ovOOP;

import java.util.Scanner;

public class Main {
    public static int userID = 0;

    public static boolean gameActive = false;

    public static String challengeCity = "null";
    public static void main(String[] args) {
        try {
            DataSystem.preloadAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner initialScanner = new Scanner(System.in);

        MenuSystem.clear();
        
        MenuSystem.startMenu(initialScanner);
    }
}