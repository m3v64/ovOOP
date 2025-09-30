package ovOOP;

import java.time.LocalTime;

public class OvTime {
    public int hours;
    public int minutes;
    public int seconds;

    static boolean peakStarted;

    public void getTime() {
        LocalTime time = LocalTime.now();
        hours = time.getHour();
        minutes = time.getMinute();
        seconds = time.getSecond();

        if (hours == 16 || hours == 17 || hours == 18 || hours == 7 || hours == 8 || hours == 9) {
            peakStarted = true;
        } else {
            peakStarted = false;
        }
    }
}
