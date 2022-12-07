package domain;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class SwimTime {

    private int memberID;
    private double time; //mm.ss
    private Events event;
    private String placeSet; //Stævne eller træning
    private LocalDate dateSet;

    public SwimTime(int memberID, double time, Events event, String placeSet, LocalDate dateSet) { //constructor for stævnetider
        this.memberID = memberID;
        this.time = time;
        this.event = event;
        this.placeSet = placeSet;
        this.dateSet = dateSet;
    }

    public int getMemberID() {
        return memberID;
    }

    public double getTime() {
        return time;
    }

    public Events getEvent() {
        return event;
    }

    public String getPlaceSet() {
        return placeSet;
    }

    public LocalDate getDateSet() {
        return dateSet;
    }

    public String convertSecondsToMinutes(double time) {
        String formattedTime;
        DecimalFormat df = new DecimalFormat("00.00");
        if (time > 60) {
            String s = (df.format(time % 60));
            int m = Math.floorDiv((int) time, 60);
            formattedTime = String.format("%d.%s", m, s);
        } else
            formattedTime = df.format(time);

        return formattedTime;
    }

    public String printSwimTime() {
        return String.format("┃ %-20s │ %9s │ %-25s │ %-12s ┃", event, convertSecondsToMinutes(time), placeSet, dateSet);
    }
}
