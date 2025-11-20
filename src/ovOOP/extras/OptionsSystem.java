package ovOOP.extras;

import java.util.Scanner;

public class OptionsSystem {

    private static void printDivider() {
        System.out.println(ColorSystem.colorPalette[0] + "========================================");
    }

    private static void printOption(int index, String option) {
        System.out.printf(ColorSystem.colorPalette[0] + "  %2d. %s%n", index, option.trim());
    }

    public static int showOption(Scanner scanner, String header, String options) {
        String regex = "[,]";
        String[] optionArray = options.split(regex);
        int i = 0;
        printDivider();
        System.out.println(ColorSystem.colorPalette[1] + header);
        System.out.println(ColorSystem.colorPalette[1] + "Please select an option:");
        printDivider();
        for (String option : optionArray) {
            i++;
            printOption(i, option);
        }
        printDivider();
        System.out.print(ColorSystem.colorPalette[1] + "Enter your choice (1-" + i + "): ");

        int chosen = 0;
        while (chosen == 0) {
            try {
                chosen = scanner.nextInt();
                scanner.nextLine();
                if (chosen > i || chosen < 1) {
                    System.out.println(ColorSystem.colorPalette[0] + "  [!] Invalid input! Please choose a number between 1 and " + i + ".");
                    System.out.print(ColorSystem.colorPalette[1] + "Enter your choice (1-" + i + "): ");
                    chosen = 0;
                }
            } catch (Exception e) {
                System.out.println(ColorSystem.colorPalette[0] + "  [!] Invalid input! Please choose a number between 1 and " + i + ".");
                System.out.print(ColorSystem.colorPalette[1] + "Enter your choice (1-" + i + "): ");
                scanner.nextLine(); // consume invalid input
                chosen = 0;
            }
        }
        printDivider();
        return chosen;
    }
}
