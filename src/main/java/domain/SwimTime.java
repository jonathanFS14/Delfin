package domain;

public class SwimTime {

    int memberID;
    double time; //mm.ss

    Events event;
    String placeSet; //Stævne eller træning



    public SwimTime(int memberID, double time, Events event, String placeSet){ //constructor for stævnetider
        this.memberID = memberID;
        this.time = time;
        this.event = event;
        this.placeSet = placeSet;
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

    public String printSwimTime() {

        return String.format("┃ %-20s │  %-10.2f │  %-20s ┃", event, time, placeSet);
    }

}
