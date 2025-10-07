package ovOOP;

import java.util.Scanner;

public class Main {
    public static String currentUsername;
    public static double currentBalance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        clear();

        Travel.startMenu(scanner);

    }

    public static void clear() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("\n");
        }
    }
}
