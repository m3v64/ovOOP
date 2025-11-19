package ovOOP;

import java.util.Scanner;

public class MapGenerationSystem {

    // Layers
    private char[][] backgroundLayer; // Empty spaces or terrain
    private char[][] cityLayer; // Cities / villages
    private char[][] textLayer; // Names / labels
    private char[][] roadLayer; // Roads or lines

    private int mapWidth;
    private int mapHeight;

    // Initialize all layers
    public void initializeMap(int mapWidth, int mapHeight, char backgroundChar) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        backgroundLayer = new char[mapHeight][mapWidth];
        cityLayer = new char[mapHeight][mapWidth];
        textLayer = new char[mapHeight][mapWidth];
        roadLayer = new char[mapHeight][mapWidth];

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                backgroundLayer[y][x] = backgroundChar;
                cityLayer[y][x] = ' ';
                textLayer[y][x] = ' ';
                roadLayer[y][x] = ' ';
            }
        }
    }

    // Generate city and optionally add text above it
    public void generateCity(int x, int y, String cityName, boolean atCity) {
        char cityChar = atCity ? '2' : '1';
        if (cityName.equalsIgnoreCase("portal")){ cityChar = '4';}
        paintSquareLayer(cityLayer, x, y, cityChar, 2, 2);

        // Center city name above city
        int textX = x - (cityName.length() / 2);
        int textY = Math.max(y - 2, 0);
        paintTextLayer(textLayer, textX, textY, cityName);
    }

    public void generateLine(String[] citiesInLine, int distanceBetweenCities) {

        // Iterate over each city
        for (int i = 0; i < citiesInLine.length; i++) {
            int x = 6 + distanceBetweenCities * i;
            int y = (mapHeight / 2);

            // Draw the city
            boolean atCity = i == 0 || i == citiesInLine.length - 1;
            generateCity(x, y, citiesInLine[i], atCity);

            // Draw the connecting line from previous city
            if (i > 0) {
                int prevX = 6 + distanceBetweenCities * (i - 1);
                int prevY = (mapHeight / 2);
                paintLineLayer(backgroundLayer, prevX, prevY, x, y, '5');
            }

        }
        displayMap(false);
    }

    // Paint text on a layer
    public void paintTextLayer(char[][] layer, int xCoordinate, int yCoordinate, String inputText) {
        char[] text = inputText.toCharArray();
        for (int i = 0; i < text.length; i++) {
            int x = xCoordinate + i;
            int y = yCoordinate;
            if (y >= 0 && y < mapHeight && x >= 0 && x < mapWidth) {
                // Only write on empty cells of the layer
                if (layer[y][x] == ' ') {
                    layer[y][x] = text[i];
                }
            }
        }
    }

    // Paint square on a specific layer
    public void paintSquareLayer(char[][] layer, int xCoordinate, int yCoordinate, char character,
            int squareHeight, int squareWidth) {
        int xStart = xCoordinate - squareWidth / 2;
        int yStart = yCoordinate - squareHeight / 2;

        for (int i = 0; i < squareWidth; i++) {
            for (int j = 0; j < squareHeight; j++) {
                int x = xStart + i;
                int y = yStart + j;
                if (x >= 0 && x < mapWidth && y >= 0 && y < mapHeight) {
                    layer[y][x] = character;
                }
            }
        }
    }

    // Paint line on a layer
    // Paint a line on a layer using Bresenham's line algorithm
    public void paintLineLayer(char[][] layer, int fromX, int fromY, int toX, int toY, char character) {
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);
        int sx = fromX < toX ? 1 : -1;
        int sy = fromY < toY ? 1 : -1;
        int err = dx - dy;

        int x = fromX;
        int y = fromY;

        while (true) {
            if (x >= 0 && x < mapWidth && y >= 0 && y < mapHeight) {
                layer[y][x] = character;
            }

            if (x == toX && y == toY)
                break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }

    // Display all layers merged
    public void displayMap(boolean debugSendRawMap) {
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                char c;
                // Determine topmost visible character
                if (textLayer[y][x] != ' ')
                    c = textLayer[y][x];
                else if (cityLayer[y][x] != ' ')
                    c = cityLayer[y][x];
                else if (roadLayer[y][x] != ' ')
                    c = roadLayer[y][x];
                else
                    c = backgroundLayer[y][x];

                if (debugSendRawMap)
                    System.out.print(c);
                else {
                    switch (c) {
                        case '1':
                            System.out.print(ColorSystem.GREEN_BG + " " + ColorSystem.RESET);
                            break;
                        case '2':
                            System.out.print(ColorSystem.YELLOW_BG + " " + ColorSystem.RESET);
                            break;
                        case '3':
                            System.out.print(ColorSystem.BRIGHT_BLACK_BG + " " + ColorSystem.RESET);
                            break;
                        case '4':
                            System.out.print(ColorSystem.BRIGHT_PURPLE_BG + " " + ColorSystem.RESET);
                            break;
                        case '5':
                            System.out.print(ColorSystem.BLUE_BG + " " + ColorSystem.RESET);
                            break;
                        default:
                            System.out.print(c);
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println(ColorSystem.CYAN + "Press enter to continue");
    }

    // Access individual layers if needed
    public char[][] getBackgroundLayer() {
        return backgroundLayer;
    }

    public char[][] getCityLayer() {
        return cityLayer;
    }

    public char[][] getTextLayer() {
        return textLayer;
    }

    public char[][] getRoadLayer() {
        return roadLayer;
    }
}
