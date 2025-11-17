package ovOOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

public class TravelSystem {

    int[] trainLineTransfers; // structure [from, to, from, to]
    String[] passingCities; // structure ["giad", "etc..."]
    int distanceTraveld;

    public TravelSystem(int[] trainLineTransfers, String[] passingCities, int distanceTraveld) {
        this.trainLineTransfers = trainLineTransfers;
        this.passingCities = passingCities;
        this.distanceTraveld = distanceTraveld;
    }

    public int[] trainLineTransfers() {
        return trainLineTransfers;
    }

    public String[] passingCities() {
        return passingCities;
    }

    public int distanceTraveld() {
        return distanceTraveld;
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
        totalCost *= conversionRate; // conversion rate for different currencies
        totalCost = Math.round(totalCost * 100) / 100.0;

        return totalCost;
    }

    static void createInvoice(Scanner scanner, double totalCost, boolean businessClass, String origin,
            String destination, String trainCompany) {

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
        System.out.println(ColorSystem.colorPalette[0] + ColorSystem.BOLD + trainCompany + " Transport - INVOICE #"
                + invoiceId + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[1] + "====================================================");
        System.out.println(ColorSystem.colorPalette[1] + "Thank you for using " + trainCompany + " Transport for your traveling!"
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
                ColorSystem.colorPalette[0] + "  You are currently at: " + ColorSystem.colorPalette[1] + data.getLocation());
        System.out.println(
                ColorSystem.colorPalette[1] + "╚════════════════════════════════════════════════════╝" + ColorSystem.RESET);

        System.out.println(ColorSystem.colorPalette[1] + "Please select a country you want to go to:" + ColorSystem.RESET);

        List<String> cities = new ArrayList<>();

        for (String i : data.CITIES) {
            if (!i.equalsIgnoreCase("portal")) {
                if (!i.equalsIgnoreCase(data.getLocation())) {
                    cities.add(i);
                }
            }
        }

        int target = OptionsSystem.showOption(scanner, "Cities", String.join(",", cities)) - 1;
        String trainCompany;
        int trainCompanyIndex = OptionsSystem.showOption(scanner, "Select your prefered railway corporation",
                "MVU public transport corporation,Predia railway logistics");
        switch (trainCompanyIndex) {
            case 1:
                trainCompany = "MVU";
                break;
            case 2:
                trainCompany = "Predia";
                break;
            default:
                trainCompany = "MVU";
                break;
        }

        System.out.println(ColorSystem.colorPalette[0] + "Selected destination: " + ColorSystem.colorPalette[1]
                + cities.get(target) + ColorSystem.RESET);

        String destination = cities.get(target);
        TravelSystem route = findRoute(destination);

        String routeStr;
        if (route.passingCities() != null && route.passingCities().length > 0) {
            routeStr = String.join(" -> ", route.passingCities());
        } else {
            routeStr = "No route found";
        }

        String lineStr;
        if (route.trainLineTransfers() != null && route.trainLineTransfers().length > 0) {
            // Build formatted string like "from 6 -> 7 and from 7 -> 10"
            StringBuilder lineBuilder = new StringBuilder();
            int[] transfers = route.trainLineTransfers();
            for (int i = 0; i < transfers.length; i += 2) {
                if (i > 0) {
                    lineBuilder.append(" and ");
                }
                lineBuilder.append("from ").append(transfers[i]).append(" -> ").append(transfers[i + 1]);
            }
            lineStr = lineBuilder.toString();
        } else {
            lineStr = "No line transfers found";
        }
        System.out
                .println(ColorSystem.colorPalette[0] + "Route: " + ColorSystem.colorPalette[1] + routeStr + ColorSystem.RESET);
        System.out.println(ColorSystem.colorPalette[0] + "Distance: " + ColorSystem.colorPalette[1] + route.distanceTraveld()
                + "Km" + ColorSystem.RESET);
        System.out
                .println(ColorSystem.colorPalette[0] + "Line's: " + ColorSystem.colorPalette[1] + lineStr + ColorSystem.RESET);
        createInvoice(scanner, calculateCost(data.isFirstClass(), route.distanceTraveld(), data.getConversionRate()),
                data.isFirstClass(), data.getLocation(), destination, trainCompany);

        data.setLocation(destination);

        data.setBalance(data.getBalance()
                - calculateCost(data.isFirstClass(), route.distanceTraveld(), data.getConversionRate()));

        System.out.println(ColorSystem.BRIGHT_PURPLE + "Press enter to continue...");
        scanner.nextLine();

        String[] passingCities = route.passingCities;

        int[] distance = new int[passingCities.length];
        for (int i : distance) {
            distance[i] = route.distanceTraveld / passingCities.length / 100;
        }

        travelMapGoTravel(distance, passingCities, 125, 10);

        scanner.nextLine();

        TravelSystem.travelMenu(scanner);

        // continue here with additional ui elements
    }

    static TravelSystem findRoute(String destination) {
        DataSystem data = new DataSystem(Main.userID);
        String source = data.getLocation();
        List<String> emptyResult = new ArrayList<>();
        if (source == null)
            return new TravelSystem(new int[0], new String[0], 0);
        if (source.equalsIgnoreCase(destination)) {
            emptyResult.add(source);
            return new TravelSystem(new int[0], emptyResult.toArray(new String[0]), 0);
        }

        // Check if both cities are on the same line - if so, return sequential path
        int commonLine = DataSystem.findLineBetween(source, destination);
        if (commonLine != -1) {
            List<String> sequentialPath = DataSystem.getSequentialPathOnLine(commonLine, source, destination);
            int sequentialDistance = DataSystem.getSequentialDistanceOnLine(commonLine, source, destination);

            if (!sequentialPath.isEmpty()) {
                return new TravelSystem(new int[0], sequentialPath.toArray(new String[0]), sequentialDistance);
            }
        }

        Map<String, Map<String, Integer>> graph = DataSystem.buildGraph();

        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            // Either source or destination not present in graph: return source only
            List<String> path = new ArrayList<>();
            path.add(source);
            return new TravelSystem(new int[0], path.toArray(new String[0]), 0);
        }

        // Dijkstra's algorithm
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            public int compare(String a, String b) {
                return Integer.compare(dist.get(a), dist.get(b));
            }
        });
        pq.add(source);

        while (!pq.isEmpty()) {
            String u = pq.poll();
            if (u.equalsIgnoreCase(destination))
                break;
            int du = dist.get(u);
            Map<String, Integer> neighbors = graph.get(u);
            if (neighbors == null)
                continue;
            for (Map.Entry<String, Integer> e : neighbors.entrySet()) {
                String v = e.getKey();
                int w = e.getValue();
                int alt = du + w;
                if (alt < dist.getOrDefault(v, Integer.MAX_VALUE)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    // reinsert v into priority queue to update its priority
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }

        // Reconstruct path
        List<String> path = new ArrayList<>();
        if (!prev.containsKey(destination)) {
            // No path found
            path.add(source);
            // return object with only passingCities containing source and zero distance
            return new TravelSystem(new int[0], path.toArray(new String[0]), 0);
        }

        String at = destination;
        while (at != null) {
            path.add(0, at);
            at = prev.get(at);
        }

        // ensure source at start
        if (!path.isEmpty() && !path.get(0).equalsIgnoreCase(source)) {
            path.add(0, source);
        }

        // compute total distance and line sequence
        int totalDistance = 0;
        List<Integer> lineSeq = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            String u = path.get(i);
            String v = path.get(i + 1);
            Integer d = null;
            if (graph.containsKey(u)) {
                d = graph.get(u).get(v);
            }
            if (d == null)
                d = 0;
            totalDistance += d;

            int line = DataSystem.findLineBetween(u, v);
            lineSeq.add(line);
        }

        // reduce contiguous same lines
        List<Integer> reduced = new ArrayList<>();
        for (int i = 0; i < lineSeq.size(); i++) {
            int ln = lineSeq.get(i);
            if (reduced.isEmpty() || reduced.get(reduced.size() - 1) != ln) {
                reduced.add(ln);
            }
        }

        // build transfer array as adjacent pairs [from,to,from,to]
        List<Integer> transfers = new ArrayList<>();
        for (int i = 0; i < reduced.size() - 1; i++) {
            transfers.add(reduced.get(i));
            transfers.add(reduced.get(i + 1));
        }

        int[] trainLineTransfers = new int[transfers.size()];
        for (int i = 0; i < transfers.size(); i++)
            trainLineTransfers[i] = transfers.get(i);

        String[] passingCities = path.toArray(new String[0]);
        int distanceTraveld = totalDistance;

        return new TravelSystem(trainLineTransfers, passingCities, distanceTraveld);
    }
}
