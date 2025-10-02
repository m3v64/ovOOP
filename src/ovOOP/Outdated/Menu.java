package ovOOP;

public class Menu {
    public int displayMenu() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the MVU train company!");
        System.out.println("1. Book a Ticket");
        System.out.println("2. Manage saldo");
        System.out.println("3. Manage your OV account");
        System.out.println("4. Login / Change account");
        System.out.println("5. Exit");
        System.out.println("----------------------------------------");
        System.out.print("Please select an option: ");
        int select = Main.scanner.nextInt();
        if (select < 1 || select > 5) {
            System.out.println("Invalid option. Please try again.");
            return displayMenu();
        } else {
            return select;
        }

    }
}
