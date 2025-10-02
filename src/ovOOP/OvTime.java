package ovOOP;

import java.time.LocalTime;

public class OvTime {
    static int hours;
    static int minutes;
    private int seconds;
    static boolean peakStarted;

    public OvTime() {
        updateTime();
    }

    public void updateTime() {
        LocalTime time = LocalTime.now();
        hours = time.getHour();
        minutes = time.getMinute();
        seconds = time.getSecond();
        peakStarted = isPeakHour(hours);
    }

    private boolean isPeakHour(int hour) {
        return (hour >= 7 && hour <= 9) || (hour >= 16 && hour <= 18);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean hasPeakStarted() {
        return peakStarted;
    }
}
