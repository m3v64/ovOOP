package ovOOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerationSystem {

    // City representation
    public static class City {
        int x, y;
        String name;
        int color;

        City(String name, int color) {
            this.name = name;
            this.color = color;
        }
    }

    // Connection representation
    private static class Connection {
        City cityA;
        City cityB;
        int distance; // can be used for scaling or labels

        Connection(City a, City b, int distance) {
            this.cityA = a;
            this.cityB = b;
            this.distance = distance;
        }
    }

    private List<City> cities = new ArrayList<>();
    private List<Connection> connections = new ArrayList<>();
    private String[][] mapArray;
    private int mapWidth;
    private int mapHeight;
    private Random random = new Random();

    // Generate empty map
    public void generateEmptyMap(int width, int height) {
        mapWidth = width;
        mapHeight = height;
        mapArray = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mapArray[y][x] = ColorSystem.BRIGHT_GREEN_BG + " ";
            }
        }
    }

    // Add city with random placement
    public City addCity(String name, int color) {
        City city = new City(name, color);
        city.x = random.nextInt(mapWidth - 4) + 2;
        city.y = random.nextInt(mapHeight - 4) + 2;
        cities.add(city);
        return city;
    }

    // Add a connection between cities with a distance
    public void connectCities(City a, City b, int distance) {
        connections.add(new Connection(a, b, distance));
    }

    // Draw map: cities + connections
    public void drawMap() {
        // Draw connections first
        for (Connection conn : connections) {
            paintMapLine(conn.cityA.x, conn.cityA.y, conn.cityB.x, conn.cityB.y, 2); // subway line color
            drawDistanceLabel(conn); // optional: label distance
        }

        // Draw cities
        for (City city : cities) {
            paintMapSquare(city.x, city.y, 3, 3, city.color);
            drawCityLabel(city);
        }
    }

    // Draw city name above or beside the city square
    private void drawCityLabel(City city) {
        int labelX = Math.min(city.x + 4, mapWidth - 1);
        int labelY = Math.max(city.y - 1, 0);
        for (int i = 0; i < city.name.length(); i++) {
            if (labelX + i < mapWidth) {
                mapArray[labelY][labelX + i] = ColorSystem.RESET + city.name.charAt(i);
            }
        }
    }

    // Draw distance label near the middle of the connection
    private void drawDistanceLabel(Connection conn) {
        int midX = (conn.cityA.x + conn.cityB.x) / 2;
        int midY = (conn.cityA.y + conn.cityB.y) / 2;
        String distStr = String.valueOf(conn.distance);
        for (int i = 0; i < distStr.length(); i++) {
            if (midX + i < mapWidth) {
                mapArray[midY][midX + i] = ColorSystem.RESET + distStr.charAt(i);
            }
        }
    }

    // Display the map
    public void displayMap() {
        for (int y = 0; y < mapArray.length; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < mapArray[0].length; x++) {
                row.append(mapArray[y][x]).append(ColorSystem.RESET);
            }
            System.out.println(row);
        }
    }

    // Paint a pixel
    public void paintMapPixel(int x, int y, int pickedColor) {
        if (y < 0 || y >= mapArray.length || x < 0 || x >= mapArray[0].length) return;

        String color;
        switch (pickedColor) {
            case 0 -> color = ColorSystem.GREEN_BG;
            case 1 -> color = ColorSystem.BLUE_BG;
            case 2 -> color = ColorSystem.WHITE_BG;
            case 3 -> color = ColorSystem.BLACK_BG;
            case 4 -> color = ColorSystem.BRIGHT_GREEN_BG;
            case 5 -> color = ColorSystem.BRIGHT_BLACK_BG;
            default -> color = ColorSystem.BRIGHT_GREEN_BG;
        }
        mapArray[y][x] = color + " ";
    }

    // Paint a square
    public void paintMapSquare(int x, int y, int width, int height, int pickedColor) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                paintMapPixel(x + col, y + row, pickedColor);
            }
        }
    }

    // Bresenham's line algorithm
    public void paintMapLine(int x1, int y1, int x2, int y2, int pickedColor) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            paintMapPixel(x1, y1, pickedColor);
            if (x1 == x2 && y1 == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) { err -= dy; x1 += sx; }
            if (e2 < dx) { err += dx; y1 += sy; }
        }
    }
}
