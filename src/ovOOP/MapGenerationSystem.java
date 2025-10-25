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
    public void generateCity(int forcedX, int forcedY, String cityName, boolean atCity) {
        char cityChar = atCity ? 'C' : 'V';
        paintSquareLayer(cityLayer, forcedX, forcedY, cityChar, 2, 2);

        // Center city name above city
        int textX = forcedX - (cityName.length() / 2);
        int textY = Math.max(forcedY - 2, 0);
        paintTextLayer(textLayer, textX, textY, cityName);
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
    public void paintLineLayer(char[][] layer, int fromX, int fromY, int toX, int toY, char character) {
        int dx = toX - fromX;
        int dy = toY - fromY;

        if (dx == 0) { // Vertical
            int startY = Math.min(fromY, toY);
            int endY = Math.max(fromY, toY);
            for (int y = startY; y <= endY; y++) {
                if (y >= 0 && y < mapHeight && fromX >= 0 && fromX < mapWidth) {
                    layer[y][fromX] = character;
                }
            }
            return;
        }

        double m = (double) dy / dx;
        int startX = Math.min(fromX, toX);
        int endX = Math.max(fromX, toX);
        int initialY = (startX == fromX) ? fromY : toY;

        for (int x = startX; x <= endX; x++) {
            int y = (int) Math.round(initialY + m * (x - startX));
            if (y >= 0 && y < mapHeight && x >= 0 && x < mapWidth) {
                layer[y][x] = character;
            }
        }
    }

    // Display all layers merged
    public void displayMap(Scanner scanner, boolean debugSendRawMap) {
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
                        case 'C':
                            System.out.print(ColorSystem.GREEN_BG + " " + ColorSystem.RESET);
                            break;
                        case 'V':
                            System.out.print(ColorSystem.YELLOW_BG + " " + ColorSystem.RESET);
                            break;
                        case '#':
                            System.out.print(ColorSystem.BRIGHT_BLACK_BG + " " + ColorSystem.RESET);
                            break;
                        case ' ':
                        case '.':
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
        scanner.nextLine();
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
