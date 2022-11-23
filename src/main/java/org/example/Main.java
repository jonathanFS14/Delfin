package org.example;

import java.time.LocalDate;
import java.time.Period;

public class Main {
    public static void main(String[] args) {
        /*
        LocalDate fødselsdag3 = LocalDate.parse("1998-08-15");
        Period period = Period.between(fødselsdag, fødselsdag3);
        System.out.println(period.getYears() +" år " + period.getMonths()+ " måned " + period.getDays());
        */
        UserInterface userInterface = new UserInterface();
        userInterface.startProgram();
    }
}