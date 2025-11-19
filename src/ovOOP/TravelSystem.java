package ovOOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class TravelSystem {
    int[] trainLineTransferPairs;
    String[] stationsOnLineAlongRoute;
    int totalDistanceTravelled;

    public TravelSystem(int[] trainLineTransferPairs, String[] stationsOnLineAlongRoute, int totalDistanceTravelled) {
        this.trainLineTransferPairs = trainLineTransferPairs;
        this.stationsOnLineAlongRoute = stationsOnLineAlongRoute;
        this.totalDistanceTravelled = totalDistanceTravelled;
    }

    public int[] getTrainLineTransferPairs() {
        return trainLineTransferPairs;
    }

    public String[] getStationsOnLineAlongRoute() {
        return stationsOnLineAlongRoute;
    }

    public int getTotalDistanceTravelled() {
        return totalDistanceTravelled;
    }

    static double calculateCost(boolean businessClass, int distanceTraveling, double conversionRate) {
        if (Main.userID == 0) {
            return 0;
        }

        double totalCost = 0;
        double fuelCostPerLiter = 21.7;
        double totalFuelCost = distanceTraveling * fuelCostPerLiter;
        totalCost += totalFuelCost; // total fuel cost
        double randomFactor = Math.random() * 0.3 + 1;
        totalCost *= (randomFactor / 500.0) * randomFactor; // random factor

        if (businessClass) {
            totalCost *= 1.7;
        } else {
            totalCost *= 0.9;
        }

        totalCost *= 1.09; // taxes
        totalCost *= 1.20; // margin
        totalCost += 2; // addition for minimal cost
        totalCost *= conversionRate; // conversion rate for different currentStationrencies
        totalCost = Math.round(totalCost * 100) / 100.0;

        return totalCost;
    }

    static void createInvoice(Scanner scanner, double totalCost, boolean businessClass, String origin,
            String destination) {

        boolean willPrint = (OptionsSystem.showOption(scanner, "Would you like to print your invoice?", "Yes,No") == 1);

        if (!willPrint) {
            MenuSystem.startMenu(scanner);
            return;
        }

        int invoiceId = (int) (Math.random() * 900000) + 100000; // 100000 to 999999

        // Reverse calculation (assuming conversionRate = 1 for simplicity, otherwise
        // divide by it)
        double costWithoutConversion = totalCost; // divide by conversionRate if needed
        double costMinusBaseFare = costWithoutConversion - 2; // subtract base fare
        double costMinusProfit = costMinusBaseFare / 1.20; // remove profit margin
        double costMinusVAT = costMinusProfit / 1.09; // remove VAT
        double travelPrice = costMinusVAT; // this is the fuel+random*class factor
        double vat = costMinusProfit - costMinusVAT; // 9% VAT portion
        double profitMargin = costMinusBaseFare - costMinusProfit; // 20% profit portion
        double baseFare = 2.0;

        DataSystem data = new DataSystem(Main.userID);

        System.out.println(ColorSystem.colorPalette[1] + "====================================================");
        System.out.println(ColorSystem.colorPalette[0] + ColorSystem.BOLD + " Transport - INVOICE #"
                + invoiceId + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "====================================================");
        System.out.println(ColorSystem.colorPalette[1] + "Thank you for using " + " Transport for your traveling!"
                + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "----------------------------------------------------");
        System.out.println(ColorSystem.colorPalette[1] + "Invoice to: " + ColorSystem.colorPalette[1] + data.getUsername()
                + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "----------------------------------------------------");
        System.out.println(ColorSystem.colorPalette[0] + "From: " + ColorSystem.colorPalette[1] + origin + ColorSystem.RESET
                + ColorSystem.colorPalette[0] + "  To: " + ColorSystem.colorPalette[1] + destination + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "----------------------------------------------------");

        System.out.println(ColorSystem.colorPalette[1] + "Base fare      : " + ColorSystem.colorPalette[0]
                + ColorSystem.withLargeIntegers(baseFare) + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "VAT (9%)       : " + ColorSystem.colorPalette[0]
                + ColorSystem.withLargeIntegers(vat) + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "Profit Margin  : " + ColorSystem.colorPalette[0]
                + ColorSystem.withLargeIntegers(profitMargin) + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "Travel price   : " + ColorSystem.colorPalette[0]
                + ColorSystem.withLargeIntegers(travelPrice) + ColorSystem.RESET);

        totalCost = baseFare + vat + profitMargin + travelPrice;
        System.out.println(ColorSystem.colorPalette[1] + "----------------------------------------------------");
        System.out.println(ColorSystem.colorPalette[1] + ColorSystem.BOLD + "Total price    : " + ColorSystem.colorPalette[1]
                + ColorSystem.withLargeIntegers(totalCost) + ColorSystem.RESET);
        System.out.println(
                ColorSystem.colorPalette[1] + "====================================================" + ColorSystem.RESET);
    }

    static void travelMenu(Scanner scanner) {

        int target = OptionsSystem.showOption(scanner, "Travel Menu", "To destination,Lines,Main menu");
        switch (target) {
            case 1:
                toDestinationMenu(scanner);
                break;
            case 2:
                showLines(scanner);
                break;
            case 3:
                MenuSystem.startMenu(scanner);
            default:
                System.out.println(ColorSystem.RED + "That is not a valid option" + ColorSystem.RESET);
                MenuSystem.startMenu(scanner);
        }
    }

    static void showLines(Scanner scanner) {
        DataSystem data = new DataSystem(Main.userID);

        List<Integer> possibleLines = DataSystem.listPossibleLines(data.getLocation());

        int[] possibleLinesArray = possibleLines.stream().mapToInt(Integer::intValue).toArray();

        List<String> lines = new ArrayList<>();

        for (int i : possibleLinesArray) {
            lines.addAll(Arrays.asList(DataSystem.getLine(i)));
            lines.addAll(Arrays.asList(DataSystem.getLine(i)));
            for (String j : lines) {
                System.out.print(j);
                System.out.print(" -> ");
            }
            System.out.println(" | ");
            lines.clear();
            System.out.println();
        }
        System.out.println("Press enter to continue...");
        scanner.nextLine();

        TravelSystem.travelMenu(scanner);
    }

    static void travelMapGoTravel(int[] secondsTravelling, String[] cityNames, int mapWidth, int mapHeight) {
        MapGenerationSystem mapGenerator = new MapGenerationSystem();

        mapGenerator.initializeMap(mapWidth, mapHeight, '3');

        String[] cityArray = cityNames;

        mapGenerator.generateLine(cityArray, 15);
    }

    static void toDestinationMenu(Scanner scanner) {

        DataSystem data = new DataSystem(Main.userID);

        System.out.println(ColorSystem.colorPalette[1] + "╔════════════════════════════════════════════════════╗");
        System.out.println(
                ColorSystem.colorPalette[0] + "  You are currentStationly at: " + ColorSystem.colorPalette[1] + data.getLocation());
        System.out.println(
                ColorSystem.colorPalette[1] + "╚════════════════════════════════════════════════════╝" + ColorSystem.RESET);

        System.out.println(ColorSystem.colorPalette[1] + "Please select a country you want to go to:" + ColorSystem.RESET);

        List<String> availableStationsOnLine = new ArrayList<>();

        for (String cityName : data.CITIES) {
            if (!cityName.equalsIgnoreCase("portal") && !cityName.equalsIgnoreCase(data.getLocation())) {
                availableStationsOnLine.add(cityName);
            }
        }

        int target = OptionsSystem.showOption(scanner, "StationsOnLine", String.join(",", availableStationsOnLine)) - 1;

        System.out.println(ColorSystem.colorPalette[0] + "Selected destination: " + ColorSystem.colorPalette[1]
                + availableStationsOnLine.get(target) + ColorSystem.RESET);

        String destination = availableStationsOnLine.get(target);
        TravelSystem route = findRoute(destination);

        String routeString;
        if (route.getStationsOnLineAlongRoute() != null && route.getStationsOnLineAlongRoute().length > 0) {
            routeString = String.join(" -> ", route.getStationsOnLineAlongRoute());
        } else {
            routeString = "No route found";
        }

        String transferString;
        if (route.getTrainLineTransferPairs() != null && route.getTrainLineTransferPairs().length > 0) {
            // Build formatted string like "from 6 -> 7 and from 7 -> 10"
            StringBuilder lineBuilder = new StringBuilder();
            int[] transfers = route.getTrainLineTransferPairs();
            for (int i = 0; i < transfers.length; i += 2) {
                if (i > 0) {
                    lineBuilder.append(" and ");
                }
                lineBuilder.append("from ").append(transfers[i]).append(" -> ").append(transfers[i + 1]);
            }
            transferString = lineBuilder.toString();
        } else {
            transferString = "No line transfers found";
        }
        System.out
                .println(ColorSystem.colorPalette[0] + "Route: " + ColorSystem.colorPalette[1] + routeString + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[0] + "Distance: " + ColorSystem.colorPalette[1] + route.getTotalDistanceTravelled()
                + "Km" + ColorSystem.RESET);
        System.out
                .println(ColorSystem.colorPalette[0] + "Line's: " + ColorSystem.colorPalette[1] + transferString + ColorSystem.RESET);
        createInvoice(scanner, calculateCost(data.isFirstClass(), route.getTotalDistanceTravelled(), data.getConversionRate()),
            data.isFirstClass(), data.getLocation(), destination);

        data.setLocation(destination);

        data.setBalance(data.getBalance()
            - calculateCost(data.isFirstClass(), route.getTotalDistanceTravelled(), data.getConversionRate()));

        System.out.println(ColorSystem.BRIGHT_PURPLE + "Press enter to continue...");
        scanner.nextLine();

        String[] stationsOnLineOnRoute = route.getStationsOnLineAlongRoute();

        int[] segmentDurations = new int[stationsOnLineOnRoute.length];
        for (int idx = 0; idx < segmentDurations.length; idx++) {
            segmentDurations[idx] = route.getTotalDistanceTravelled() / stationsOnLineOnRoute.length / 100;
        }

        travelMapGoTravel(segmentDurations, stationsOnLineOnRoute, 125, 10);

        scanner.nextLine();

        TravelSystem.travelMenu(scanner);

        // continue here with additional ui elements
    }
    
    static TravelSystem findRoute(String destination) {
        DataSystem data = new DataSystem(Main.userID);
        String start = data.getLocation();

        if (start == null || destination == null) {
            return new TravelSystem(new int[0], new String[0], 0);
        }

        if (start.equalsIgnoreCase(destination)) {
            return new TravelSystem(new int[0], new String[] { start }, 0);
        }

        class Station {
            String stationName;
            int accumulatedDistance;
            Station previousStation;
            int arrivalLineId;
            Station(String stationName, int accumulatedDistance, Station previousStation, int arrivalLineId) {
                this.stationName = stationName;
                this.accumulatedDistance = accumulatedDistance;
                this.previousStation = previousStation;
                this.arrivalLineId = arrivalLineId;
            }
        }

        List<Station> toCheck = new ArrayList<>();
        Set<String> checked = new HashSet<>();

        toCheck.add(new Station(start, 0, null, -1));

        Station destinationStation = null;

        while (!toCheck.isEmpty()) {
            int closestStationIndex = 0;
            for (int i = 1; i < toCheck.size(); i++) {
                if (toCheck.get(i).accumulatedDistance < toCheck.get(closestStationIndex).accumulatedDistance) {
                    closestStationIndex = i;
                }
            }

            Station currentStation = toCheck.remove(closestStationIndex);

            if (checked.contains(currentStation.stationName.toLowerCase()))
                continue;

            checked.add(currentStation.stationName.toLowerCase());

            if (currentStation.stationName.equalsIgnoreCase(destination)) {
                destinationStation = currentStation;
                break;
            }

            List<Integer> linesContainingStation = DataSystem.listPossibleLines(currentStation.stationName);
            for (int line : linesContainingStation) {
                String[] stationsOnLine = DataSystem.getLine(line);
                if (stationsOnLine == null || stationsOnLine.length == 0)
                    continue;

                int stationOnLineIndex = -1;
                for (int i = 0; i < stationsOnLine.length; i++) {
                    if (stationsOnLine[i].equalsIgnoreCase(currentStation.stationName)) {
                        stationOnLineIndex = i;
                        break;
                    }
                }
                if (stationOnLineIndex == -1)
                    continue;

                int[] neighbouringStationsIndices = new int[] { stationOnLineIndex - 1, stationOnLineIndex + 1 }; // can change this to only make the program look forwards
                for (int neighbouringStationIndex : neighbouringStationsIndices) {
                    if (neighbouringStationIndex < 0 || neighbouringStationIndex >= stationsOnLine.length)
                        continue;
                    String neighbouringStationName = stationsOnLine[neighbouringStationIndex];

                    if (checked.contains(neighbouringStationName.toLowerCase()))
                        continue;

                    int stationDistance = DataSystem.getSequentialDistanceOnLine(line, currentStation.stationName, neighbouringStationName);
                    if (stationDistance <= 0)
                        continue;

                    int newAccumulatedDistance = currentStation.accumulatedDistance + stationDistance;

                    boolean updated = false;
                    for (int d = 0; d < toCheck.size(); d++) {
                        Station s = toCheck.get(d);
                        if (s.stationName.equalsIgnoreCase(neighbouringStationName)) {
                            if (newAccumulatedDistance < s.accumulatedDistance) {
                                s.accumulatedDistance = newAccumulatedDistance;
                                s.previousStation = currentStation;
                                s.arrivalLineId = line;
                            }
                            updated = true;
                            break;
                        }
                    }

                    if (!updated) {
                        toCheck.add(new Station(neighbouringStationName, newAccumulatedDistance, currentStation, line));
                    }
                }
            }
        }
        if (destinationStation == null) {
            return new TravelSystem(new int[0], new String[0], 0);
        }

        List<String> pathStationNames = new ArrayList<>();
        List<Integer> connectionLineIds = new ArrayList<>();

        Station station = destinationStation;
        while (station != null) {
            pathStationNames.add(0, station.stationName);
            if (station.arrivalLineId != -1) {
                connectionLineIds.add(0, station.arrivalLineId);
            }
            station = station.previousStation;
        }

        List<Integer> lineTransferPairs = new ArrayList<>();
        if (!connectionLineIds.isEmpty()) {
            int prevLine = connectionLineIds.get(0);
            for (int i = 1; i < connectionLineIds.size(); i++) {
                int lineId = connectionLineIds.get(i);
                if (lineId != prevLine) {
                    lineTransferPairs.add(prevLine);
                    lineTransferPairs.add(lineId);
                    prevLine = lineId;
                }
            }
        }

        int[] transfersArr = lineTransferPairs.stream().mapToInt(Integer::intValue).toArray();
        String[] stationsOnLineArr = pathStationNames.toArray(new String[0]);
        int totalDistance = destinationStation.accumulatedDistance;

        return new TravelSystem(transfersArr, stationsOnLineArr, totalDistance);
    }
}
