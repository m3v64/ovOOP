package ovOOP;

import java.util.regex.Pattern;

public class MapGenerationSystem {

    public String[] generateMap(int mapWidth, int mapHeight) {
        String[] mapRows = new String[mapHeight];

        for (int i = 0; i < mapHeight; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < mapWidth; j++) {

                row.append(ColorSystem.BRIGHT_GREEN_BG).append(" ").append(ColorSystem.RESET);
            }
            mapRows[i] = row.toString();
        }

        return mapRows;
    }

    public void displayMap(int mapWidth, int mapHeight) {
        String[] generatedMap = generateMap(mapWidth, mapHeight);

        generatedMap = paintMapLine(0, 0, 6, 6, generatedMap, 1);

        generatedMap = paintMapLine(0, 30, 6, 11, generatedMap, 1);

        generatedMap = paintMapSquare(6, 6, generatedMap, 1, 5, 5);

        // generatedMap = paintMapPixel(1, 1, generatedMap, 2);

        for (String row : generatedMap) {
            System.out.println(row);
        }
    }

    public String[] paintMapLine(int x1, int y1, int x2, int y2, String[] mapArray, int pickedColor) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        int err = dx - dy;

        while (true) {
            mapArray = paintMapPixel(x1, y1, mapArray, pickedColor); // Paint current pixel

            if (x1 == x2 && y1 == y2)
                break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }

        return mapArray;
    };

    public String[] paintMapSquare(int x, int y, String[] mapArray, int pickedColor, int squareWidth,
            int squareHeight) {
        String[] updatedMap = mapArray;

        for (int row = 0; row < squareHeight; row++) {
            for (int col = 0; col < squareWidth; col++) {
                int px = x + col;
                int py = y + row;

                updatedMap = paintMapPixel(px, py, updatedMap, pickedColor);
            }
        }

        return updatedMap;
    }

    public String[] paintMapPixel(int x, int y, String[] mapArray, int pickedColor) {
        if (y < 0 || y >= mapArray.length)
            return mapArray;

        String color;
        switch (pickedColor) {
            case 0 -> color = ColorSystem.GREEN_BG;
            case 1 -> color = ColorSystem.BLUE_BG;
            case 2 -> color = ColorSystem.WHITE_BG;
            case 3 -> color = ColorSystem.BLACK_BG;
            case 4 -> color = ColorSystem.BRIGHT_GREEN_BG;
            default -> color = ColorSystem.BRIGHT_GREEN_BG;
        }

        // Split row into "cells"
        String[] cells = mapArray[y].split(Pattern.quote(ColorSystem.RESET));
        for (int i = 0; i < cells.length; i++) {
            if (i == x) {
                cells[i] = color + " ";
            } else if (!cells[i].isEmpty()) {
                // Preserve previous colors, remove trailing spaces
                cells[i] = cells[i].substring(0, cells[i].length());
            }
        }

        // Reconstruct the row
        StringBuilder newRow = new StringBuilder();
        for (String cell : cells) {
            newRow.append(cell).append(ColorSystem.RESET);
        }

        mapArray[y] = newRow.toString();
        return mapArray;
    }

}