package ovOOP;

import java.util.Scanner;

public class Option {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static int showOption(Scanner scanner, String options) {
        String regex = "[,]";
        String[] optionArray = options.split(regex);
        int i = 0;
        System.out.println(ANSI_BLUE + "-----------------------");
        for (String option : optionArray) {
            i++;
            System.out.println(ANSI_GREEN + i + ". " + option);
        }
        System.out.println(ANSI_BLUE + "-----------------------");
        System.out.println(ANSI_CYAN + "Please choose an option from the list");
        int chosen = 0;
        while (chosen == 0) {
            try {
                chosen = scanner.nextInt();
                scanner.nextLine();
                if (chosen > i || chosen < 1) {
                    System.out
                            .println(ANSI_RED + "That is not a valid input! Please choose a number between 1 and " + i);
                            scanner.nextLine();
                    chosen = 0;
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "That is not a valid input! Please choose a number between 1 and " + i);
                scanner.nextLine(); // consume invalid input
                chosen = 0;
            }
        }
        return chosen;
    }
}
