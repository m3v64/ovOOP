package ovOOP;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuSystem {
    static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        DataSystem data = new DataSystem(Main.userID);

        ColorSystem.colorPalette[0] = data.getMainPalette();
        ColorSystem.colorPalette[1] = data.getSecondaryPalette();

        System.out.println(ColorSystem.colorPalette[0] + "+------------------------------------------------+");
        System.out.println(ColorSystem.colorPalette[0] + "|                                                |");
        System.out.println(ColorSystem.colorPalette[1] + "|   _______ ___    _________ _______ ________    |");
        System.out.println(ColorSystem.colorPalette[1] + "|   __  __ \\__ |  / /__  __ \\__  __ \\___  __ \\   |");
        System.out.println(ColorSystem.colorPalette[1] + "|   _  / / /__ | / / _  / / /_  / / /__  /_/ /   |");
        System.out.println(ColorSystem.colorPalette[1] + "|   / /_/ / __ |/ /  / /_/ / / /_/ / _  ____/    |");
        System.out.println(ColorSystem.colorPalette[1] + "|   \\____/  _____/   \\____/  \\____/  /_/         |");
        System.out.println(ColorSystem.colorPalette[1] + "|                                                |");
        System.out.println(ColorSystem.colorPalette[0] + "+------------------------------------------------+");
        System.out.println(ColorSystem.colorPalette[0] + "=======================================");
        int input = 0;

        int currentUser = data.getUserID();
        if (currentUser != 0) {
            System.out.println(
                    ColorSystem.colorPalette[1] + String.format("Logged in as: %-20s", data.getUsername()) +
                            ColorSystem.colorPalette[1] + " | Balance: "
                            + ColorSystem.withLargeIntegers(data.getBalance()));
            System.out.println(ColorSystem.colorPalette[0] + "You are at " + data.getLocation());
            DataSystem.updateTime();
            DecimalFormat df = new DecimalFormat("00");
            System.out.println(
                    ColorSystem.colorPalette[0] + "It is currently " + df.format(DataSystem.getHour()) + ":"
                            + df.format(DataSystem.getMinute()));
            System.out.println(ColorSystem.colorPalette[0] + "The " + DataSystem.getDay() + "th of "
                    + DataSystem.getMonthName(DataSystem.getMonth()));
            System.out.println(ColorSystem.colorPalette[0] + "---------------------------------------");
            input = OptionsSystem.showOption(scanner, "Main Menu",
                    "Start traveling,Settings,Manage balance,Exit system,Credits");
        } else {
            System.out.println(ColorSystem.colorPalette[1] + "You are not currently logged in.");
            System.out.println(ColorSystem.colorPalette[1] + "Please log in before using any traveling features.");
            System.out.println(ColorSystem.colorPalette[0] + "---------------------------------------");
            input = OptionsSystem.showOption(scanner, "Main Menu", "Account Settings,Credits");
        }
        if (currentUser != 0) {
            if (input == 5) {
                // Credits go here
                showCredits(scanner);
            } else if (input == 1) {
                // Travel Menu
                TravelSystem.travelMenu(scanner);
            } else if (input == 2) {
                MenuSystem.showSettingsScreen(scanner);
                // Accounts system goes here
            } else if (input == 3) {
                BalanceSystem.manageBalance(scanner);
            } else if (input == 4) {
                System.out.println(ColorSystem.RED + "Exiting System");
                System.exit(0);
                // Exits the system
            }
        } else {
            switch (input) {
                case 1 -> AccountSystem.displayAccounts(scanner);
                case 2 -> showCredits(scanner);
            }
        }
    }

    static void changeColorPalette(Scanner scanner) {

        int color1 = OptionsSystem.showOption(scanner, "Choose the first color!",
                "Black,Red,Green,Yellow,Blue,Purple,Cyan,White,Bright Black,Bright Red,Bright Green,Bright Yellow,Bright Blue, Bright Purple, Bright Cyan, Bright White");
        int color2 = OptionsSystem.showOption(scanner, "Choose the second color!",
                "Black,Red,Green,Yellow,Blue,Purple,Cyan,White,Bright Black,Bright Red,Bright Green,Bright Yellow,Bright Blue, Bright Purple, Bright Cyan, Bright White");
        String[] Colors = ColorSystem.colorPaletteOptions;
        ColorSystem.colorPalette[0] = Colors[color1 - 1];
        ColorSystem.colorPalette[1] = Colors[color2 - 1];

        DataSystem data = new DataSystem(Main.userID);
        data.setMainPalette(ColorSystem.colorPalette[0]);
        data.setSecondaryPalette(ColorSystem.colorPalette[1]);

        startMenu();
    }

    static void showVisualSettingsScreen(Scanner scanner) {
        int target = OptionsSystem.showOption(scanner, "Please choose the setting you want to change",
                "Color Palette,Back");

        switch (target) {
            case 1:
                changeColorPalette(scanner);
                break;
            case 2:
                showSettingsScreen(scanner);
                ;
                break;
        }
    }

    static void showSettingsScreen(Scanner scanner) {
        int target = OptionsSystem.showOption(scanner, "Settings",
                "Account settings,Visual settings,Debug settings,Main Menu");

        switch (target) {
            case 1:
                AccountSystem.displayAccounts(scanner);
                break;
            case 2:
                showVisualSettingsScreen(scanner);
                break;
            case 3:
                break;
            case 4:
                startMenu();
                break;
        }
    }

    static void showCredits(Scanner scanner) {
        System.out.println(ColorSystem.colorPalette[0] + "==========================================");
        System.out.println(ColorSystem.colorPalette[0] + "                CREDITS                   ");
        System.out.println(ColorSystem.colorPalette[0] + "==========================================");
        System.out.println(ColorSystem.colorPalette[1] + " Developers      : Morris van Uden, Max Viehöfer");
        System.out.println(ColorSystem.colorPalette[1] + " Teacher         : Erik Seldenthuis");
        System.out.println(ColorSystem.colorPalette[1] + " Class           : TIA4V1B");
        System.out.println(ColorSystem.colorPalette[1] + " Web Page        : https://ovoop.m3v.dev (Soon)");
        System.out.println(ColorSystem.colorPalette[1] + " GitHub Repo     : https://github.com/m3v64/ovOOP");
        System.out.println(ColorSystem.colorPalette[0] + "==========================================");
        System.out.println();
        System.out.println(ColorSystem.BRIGHT_PURPLE + "Press enter to continue...");
        scanner.nextLine();
        startMenu();
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
