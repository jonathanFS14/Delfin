package test;
import domain.Swimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class DatabaseTest {

    Swimmer swimmer1;
    Swimmer swimmer2;
    Swimmer swimmer3;
    Swimmer swimmer4;
    Swimmer swimmer5;
    ArrayList<Swimmer>swimmerList;
    ArrayList<Swimmer>seniorTeam;
    ArrayList<Swimmer>juniorTeam;


    @BeforeEach
    void createSwimmer(){
        swimmer1 = new Swimmer("navn1", "addresse", "nummer", "mail", LocalDate.parse("2000-11-29"), 111, true, true);
        swimmer2 = new Swimmer("navn2", "addresse", "nummer", "mail", LocalDate.parse("2010-11-28"), 111, true, true);
        swimmer3 = new Swimmer("navn3", "addresse", "nummer", "mail", LocalDate.parse("1996-11-28"), 111, true, true);
        swimmer4 = new Swimmer("navn4", "addresse", "nummer", "mail", LocalDate.parse("2013-11-28"), 111, true, true);
        swimmer5 = new Swimmer("navn5", "addresse", "nummer", "mail", LocalDate.parse("2000-11-28"), 111, true, true);
        swimmerList = new ArrayList<>(Arrays.asList(swimmer1,swimmer2,swimmer3,swimmer4,swimmer5));
        seniorTeam = new ArrayList<>();
        juniorTeam = new ArrayList<>();
    }

    @Test
    void findSwimmerAge() {
        long years = ChronoUnit.YEARS.between( swimmer1.getBirthday(),LocalDate.now());
        assertTrue(years==22);
    }

    @Test
    void setSwimmersToTeams(){
        for(Swimmer swimmer : swimmerList){
            long years = ChronoUnit.YEARS.between( swimmer.getBirthday(),LocalDate.now());
            if (years >= 18){
                seniorTeam.add(swimmer);
            }
            else {
                juniorTeam.add(swimmer);
            }
        }
        assertTrue(seniorTeam.size()==3);
        assertTrue(juniorTeam.size()==2);
    }
}