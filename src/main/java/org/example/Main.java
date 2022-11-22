package org.example;

import java.time.LocalDate;
import java.time.Period;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LocalDate fødselsdag = LocalDate.of(1998,8,15);
        LocalDate fødselsdag2 = LocalDate.of(1997,1,22);
        LocalDate fødselsdag3 = LocalDate.parse("1998-08-15");

        Period period = Period.between(fødselsdag2, fødselsdag3);

        System.out.println(period.getYears() +" år " + period.getMonths()+ " måned " + period.getDays());

    }
}