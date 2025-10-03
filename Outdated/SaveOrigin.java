package ovOOP;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;

public class SaveOrigin {

    public void saveOrigin(String newOrigin, String user, String filename) {
        JSONArray originsArray;

        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            originsArray = new JSONArray(content);
        } catch (java.io.IOException e) {
            originsArray = new JSONArray();
        }

        originsArray.put(this.toJson(user, newOrigin));

        try (FileWriter file = new FileWriter(filename)) {
            file.write(originsArray.toString(4));
            System.out.println("Origin saved to " + filename);
        } catch (java.io.IOException e) {
            System.out.println("Error saving origin: " + e.getMessage());
        }
    }

    public JSONObject toJson(String user, String newOrigin) {
        return new JSONObject()
                .put("user", user)
                .put("origin", newOrigin);
    }

    public String loadLastOrigin(String user, String filename) {
        JSONArray originsArray;

        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            originsArray = new JSONArray(content);
        } catch (java.io.IOException e) {
            return null;
        }

        String lastOrigin = null;

        for (int i = 0; i < originsArray.length(); i++) {
            JSONObject obj = originsArray.getJSONObject(i);
            if (obj.getString("user").equals(user)) {
                lastOrigin = obj.getString("origin");
            }
        }

        return lastOrigin;
    }
}
