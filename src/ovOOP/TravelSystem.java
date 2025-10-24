package ovOOP;

import java.util.ArrayList;
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
        double fuelCostPerLiter = 2.17;
        double totalFuelCost = (distanceTraveling / 500.0) * fuelCostPerLiter;
        totalCost += totalFuelCost; // total fuel cost
        double randomFactor = Math.random() * 0.3 + 1;
        totalCost *= randomFactor * (randomFactor / 2); // random factor

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

    static void CreateInvoice(Scanner scanner, double totalCost, boolean isFirstClass, String origin,String destination, String trainCompany) {
        System.out.println(ColorSystem.BRIGHT_BLUE + "Would you like to print your invoice?" + ColorSystem.RESET);
        boolean willPrint = (OptionsSystem.showOption(scanner, "Yes,No") == 1);

        if (!willPrint) {
            MenuSystem.startMenu(scanner);
            return;
        }
        int invoiceId = (int) (Math.random() * 900000) + 100000; // 100000 to 999999

        System.out.println(ColorSystem.BRIGHT_CYAN + "====================================================");
        System.out.println(ColorSystem.BRIGHT_BLUE + ColorSystem.BOLD + trainCompany + " Transport - INVOICE #" + invoiceId+ ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "====================================================");
        System.out.println(ColorSystem.CYAN + "Thank you for using " + trainCompany + " Transport for your traveling!"+ ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");
        DataSystem data = new DataSystem(Main.userID);
        System.out.println(ColorSystem.BRIGHT_CYAN + "Invoice to: " + ColorSystem.BRIGHT_CYAN + data.getUsername()+ ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");
        System.out.println(ColorSystem.BRIGHT_BLUE + "From: " + ColorSystem.BRIGHT_CYAN + origin + ColorSystem.RESET +ColorSystem.BRIGHT_BLUE + "  To: " + ColorSystem.BRIGHT_CYAN + destination + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");

        // Base fare
        double baseFare = 2.00;
        System.out.println(ColorSystem.BRIGHT_CYAN + "Base fare      : " + ColorSystem.BRIGHT_BLUE+ ColorSystem.withLargeIntegers(baseFare) + ColorSystem.RESET);

        // Fixed VAT and profit
        double vat = 0.03;
        double profitMargin = 0.07;
        System.out.println(ColorSystem.BRIGHT_CYAN + "VAT (9%)       : " + ColorSystem.BRIGHT_BLUE+ ColorSystem.withLargeIntegers(vat) + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "Profit Margin  : " + ColorSystem.BRIGHT_BLUE+ ColorSystem.withLargeIntegers(profitMargin) + ColorSystem.RESET);

        // Travel price
        double travelPrice = 0.37;
        System.out.println(ColorSystem.BRIGHT_CYAN + "Travel price   : " + ColorSystem.BRIGHT_BLUE+ ColorSystem.withLargeIntegers(travelPrice) + ColorSystem.RESET);

        // Total cost
        totalCost = baseFare + vat + profitMargin + travelPrice;
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");
        System.out.println(ColorSystem.BRIGHT_CYAN + ColorSystem.BOLD + "Total price    : " + ColorSystem.BRIGHT_CYAN+ ColorSystem.withLargeIntegers(totalCost) + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "====================================================" + ColorSystem.RESET);
    }

    static void travelMenu(Scanner scanner) {
        MenuSystem.clear();

        int target = OptionsSystem.showOption(scanner, "To destination,Lines,Map");
        switch (target) {
            case 1:
                toDestinationMenu(scanner);
                break;
            case 2:
                break;
            case 3:
                mapMenu(scanner);
                break;
            default:
                System.out.println(ColorSystem.RED + "That is not a valid option" + ColorSystem.RESET);
                MenuSystem.startMenu(scanner);
        }
    }

    static void mapMenu(Scanner scanner) {
        MapGenerationSystem initialMapGenerator = new MapGenerationSystem();

        initialMapGenerator.generateEmptyMap(120, 34);

        // Store the returned City objects
        MapGenerationSystem.City cityDryard = initialMapGenerator.addCity("Dryard", 1);
        MapGenerationSystem.City cityGiad = initialMapGenerator.addCity("Giad", 3);
        MapGenerationSystem.City cityGhostle = initialMapGenerator.addCity("Ghostle", 3);

        // Now you can connect them
        initialMapGenerator.connectCities(cityGhostle, cityGiad, 300);
        initialMapGenerator.connectCities(cityDryard, cityGiad, 300);

        initialMapGenerator.drawMap();

        initialMapGenerator.displayMap();

        scanner.nextLine();

        TravelSystem.travelMenu(scanner);
    }

    static void toDestinationMenu(Scanner scanner) {
        MenuSystem.clear();

        DataSystem data = new DataSystem(Main.userID);

        System.out.println(ColorSystem.BRIGHT_CYAN + "╔════════════════════════════════════════════════════╗");
        System.out.println(ColorSystem.BRIGHT_BLUE + "  You are currently at: " + ColorSystem.BRIGHT_CYAN + data.getLocation());
        System.out.println(ColorSystem.BRIGHT_CYAN + "╚════════════════════════════════════════════════════╝" + ColorSystem.RESET);

        System.out.println(ColorSystem.CYAN + "Please select a country you want to go to:" + ColorSystem.RESET);

        List<String> cities = new ArrayList<>();

        for (String i : data.CITIES) {
            cities.add(i);
        }

        int target = OptionsSystem.showOption(scanner, String.join(",", cities)) - 1;

        System.out.println(ColorSystem.BRIGHT_BLUE + "Selected destination: " + ColorSystem.BRIGHT_CYAN+ cities.get(target) + ColorSystem.RESET);

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
        System.out.println(ColorSystem.BRIGHT_BLUE + "Route: " + ColorSystem.BRIGHT_CYAN + routeStr + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_BLUE + "Distance: " + ColorSystem.BRIGHT_CYAN + route.distanceTraveld() + "Km" + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_BLUE + "Line's: " + ColorSystem.BRIGHT_CYAN + lineStr + ColorSystem.RESET);

        System.out.println("========================================");
        System.out.println("is that correct?");
        System.out.println("Y/n");
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
