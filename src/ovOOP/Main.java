package ovOOP;

import java.util.Scanner;

public class Main {
    public static String username;
    public static double Balance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        clear();

        Travel.startMenu(scanner);

    }

    static void clear() {
        for (int i = 0; i < 40; i++) {
            System.out.println("\n");
        }
    }
}
