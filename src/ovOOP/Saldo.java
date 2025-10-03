package ovOOP;

import java.io.File;
import java.io.IOException;

public class Saldo {
    static void accountConf(String[] args) {
        try {
            File myObj = new File("C:\\Users\\max\\OneDrive - ROC Nijmegen\\personal\\ovOOP\\data\\accountSaldo.json");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during file creation.");
            e.printStackTrace();
        }
    }
}
