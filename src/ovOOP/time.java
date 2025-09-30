package ovOOP;

import java.time.LocalTime;

public class time {
    public static int hours;
    public static int minutes;
    public static int seconds;

    public static boolean peakStarted;

    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
        hours = time.getHour();
        minutes = time.getMinute();
        seconds = time.getSecond();

        if (hours == 16 || hours == 17 || hours == 18 || hours == 7 || hours == 8 || hours == 9) {
            peakStarted = true;
        } else {
            peakStarted = false;
        }
        System.out.println(peakStarted);
    }
}
