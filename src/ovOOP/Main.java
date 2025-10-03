package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Saldo.accountConf(args);
        Scanner scanner = new Scanner(System.in);
        Travel.startMenu(scanner);
        
    }
}
