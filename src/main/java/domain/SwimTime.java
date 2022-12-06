package domain;

import java.time.LocalDate;

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

    public String printSwimTime() {

        return String.format("┃ %-20s │ %-5.2f sek │ %-20s │ %-12s ┃", event, time, placeSet, dateSet);
    }

}
