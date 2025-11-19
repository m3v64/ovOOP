package ovOOP.model;

import java.util.List;

/**
 * POJO model representing a train company with its lines.
 * Used for JSON serialization/deserialization with Gson.
 */
public class TrainCompany {
    private String company;
    private List<TrainLine> lines;

    public TrainCompany() {
        // Default constructor for Gson
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<TrainLine> getLines() {
        return lines;
    }

    public void setLines(List<TrainLine> lines) {
        this.lines = lines;
    }
}
