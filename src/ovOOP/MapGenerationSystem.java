package ovOOP;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MapGenerationSystem {

    public class City {
        String name;
        int color;

        City(String name, int color) {
            this.name = name;
            this.color = color;
        }
    }

    public class Line {
        String name;
        String[] citiesInLine;
        int lineColor;

        Line(String name, String[] citiesInLine, int lineColor) {
            this.name = name;
            this.citiesInLine = citiesInLine;
            this.lineColor = lineColor;
        }
    }

    public void initializeCities(String[] cityList, boolean debug) {
        String[] cities = cityList;
        if (debug) {
            for (String i : cities) {
                System.out.println(i);
            }
        }
    }

    public int[][] createEmptyMap(int mapWidth, int mapHeight, int backgroundColor) {
        int[][] mapValues = new int[mapWidth][mapHeight];

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                mapValues[i][j] = backgroundColor;
            }
        }
        return mapValues;
    }

    public void displayMap(int[][] map, Scanner scanner, boolean debugSendRawMap) {
        for (int[] row : map) {
            for (int num : row) {
                if (debugSendRawMap) {
                    System.out.print(num);
                } else {
                    switch (num) {
                        case 0:
                            System.out.print(ColorSystem.BLUE_BG + " " + ColorSystem.RESET);
                            break;
                        case 1:
                            System.out.print(ColorSystem.BRIGHT_BLACK_BG + " " + ColorSystem.RESET);
                            break;
                        case 2:
                            System.out.print(ColorSystem.GREEN_BG + " " + ColorSystem.RESET);
                            break;
                        case 3:
                            System.out.print(" ");
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println(ColorSystem.CYAN + "Press enter to continue");
        scanner.nextLine();

    }

    public int[][] paintPixel(int[][] map, int xCoordinate, int yCoordinate, int selectedColor) {
        int[][] updatedMap = map;

        updatedMap[xCoordinate][yCoordinate] = selectedColor;

        return updatedMap;
    }

    public int[][] paintSquare(int[][] map, int xCoordinate, int yCoordinate, int selectedColor,
            int squareHeight, int squareWidth) {
        int[][] updatedMap = map;

        int rows = map.length;
        int cols = map[0].length;

        int xStart = xCoordinate - squareWidth / 2;
        int yStart = yCoordinate - squareHeight / 2;

        for (int i = 0; i < squareWidth; i++) {
            for (int j = 0; j < squareHeight; j++) {
                int x = xStart + i;
                int y = yStart + j;

                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    updatedMap[x][y] = selectedColor;
                }
            }
        }

        return updatedMap;
    }

    public int[][] paintLine(int[][] map, int fromX, int fromY, int toX, int toY, int selectedColor) {
        int[][] updatedMap = map;

        int dx = toX - fromX;
        int dy = toY - fromY;

        if (dx == 0) { // Vertical line
            int startY = Math.min(fromY, toY);
            int endY = Math.max(fromY, toY);
            for (int y = startY; y <= endY; y++) {
                updatedMap = paintPixel(updatedMap, fromX, y, selectedColor);
            }
            return updatedMap;
        }

        double m = (double) dy / dx;

        int startX = Math.min(fromX, toX);
        int endX = Math.max(fromX, toX);
        int initialY = (startX == fromX) ? fromY : toY;

        for (int x = startX; x <= endX; x++) {
            int y = (int) Math.round(initialY + m * (x - startX));
            updatedMap = paintPixel(updatedMap, x, y, selectedColor);
        }

        return updatedMap;
    }

}
