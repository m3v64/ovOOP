package ovOOP;

import java.util.Scanner;

public class Balance {
    static void manageBalance(Scanner scanner) {
        Data data = new Data(Main.userID);
        System.out.println(Travel.ANSI_BLUE + "---------------------------------------");
        System.out.println(Travel.ANSI_CYAN + "Please choose from the following options");
        System.out.println(Travel.ANSI_YELLOW + "the balance of the user " + data.getUsername() + " is " + data.getBalance());
        int balanceMenue = Option.showOption(scanner, "deposit,withdraw");
    }
}
