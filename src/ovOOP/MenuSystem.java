package ovOOP;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MenuSystem {

    static void startMenu(Scanner scanner) {
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

        if (Main.gameActive) {
            String challengeCity = Main.challengeCity;

            if (challengeCity.equals("null") || challengeCity.equals(data.getLocation())) {
                if (challengeCity.equals(data.getLocation())) {
                    data.setGameXP(data.getGameXP() + 1);
                }
                String[] cities = data.CITIES;
                while (challengeCity.equals(data.getLocation()) || challengeCity.equals("null")) {

                    int randomCityIndex = (int) (Math.random() * cities.length);

                    Main.challengeCity = cities[randomCityIndex];
                    challengeCity = Main.challengeCity;
                }
            }

            System.out.println("Challenge: Go to " + challengeCity + " (" + data.getGameXP() + " xp)");
        }

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
            if (!Main.gameActive) {
                input = OptionsSystem.showOption(scanner, "Main Menu",
                        "Start traveling,Settings,Manage balance,Exit system,Credits");
            } else {
                input = OptionsSystem.showOption(scanner, "Main Menu",
                        "Start traveling,Settings,Earn money,Exit system,Credits");
            }

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
                if (!Main.gameActive) {
                    BalanceSystem.manageBalance(scanner);
                } else {
                    data.setBalance(data.getBalance() + GameSystem.playGame((int) (Math.random() * 11) + 1));
                    MenuSystem.startMenu(scanner);
                }
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

        startMenu(scanner);
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
                break;
        }
    }

    static void showSettingsScreen(Scanner scanner) {
        int target = OptionsSystem.showOption(scanner, "Settings",
                "Account settings,Visual settings,Experiments,Main Menu");

        switch (target) {
            case 1:
                AccountSystem.displayAccounts(scanner);
                break;
            case 2:
                showVisualSettingsScreen(scanner);
                break;
            case 3:
                showExperimentsSettingsScreen(scanner);
                break;
            case 4:
                startMenu(scanner);
                break;
        }
    }

    static void showExperimentsSettingsScreen(Scanner scanner) {
        int targetSetting = OptionsSystem.showOption(scanner, "What experiment do you want to toggle?", "Game,Discord");

        switch (targetSetting) {
            case 1:
                int target = OptionsSystem.showOption(scanner, "Do you want to turn on the game mode?", "True,False");

                Main.gameActive = (target == 1);
                break;
            case 2:
                break;

            default:
                break;
        }
        MenuSystem.startMenu(scanner);

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
        startMenu(scanner);
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
