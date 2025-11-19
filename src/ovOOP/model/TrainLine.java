package ovOOP.model;

import java.util.Map;

/**
 * POJO model representing a train line with its stations and connections.
 * Used for JSON serialization/deserialization with Gson.
 */
public class TrainLine {
    private int line;
    private String start;
    private boolean mainLine;
    private Map<String, StationConnection> connections;

    public TrainLine() {
        // Default constructor for Gson
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public boolean isMainLine() {
        return mainLine;
    }

    public void setMainLine(boolean mainLine) {
        this.mainLine = mainLine;
    }

    public Map<String, StationConnection> getConnections() {
        return connections;
    }

    public void setConnections(Map<String, StationConnection> connections) {
        this.connections = connections;
    }
}
