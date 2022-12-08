package test;

import domain.Swimmer;
import userinterface.KassererUserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KassererUserInterfaceTest {

    KassererUserInterface kasserer;
    Swimmer swimmer1;
    Swimmer swimmer2;
    Swimmer swimmer3;
    Swimmer swimmer4;
    Swimmer swimmer5;
    Swimmer swimmer6;
    ArrayList<Swimmer>swimmerList;

    @BeforeEach
            void createSwimmmers() {
        kasserer = new KassererUserInterface();
        swimmer1 = new Swimmer("navn1", "addresse", "nummer", "mail", LocalDate.parse("2010-11-29"), 0, true, true); // Junior studerende
        swimmer2 = new Swimmer("navn2", "addresse", "nummer", "mail", LocalDate.parse("2010-11-28"), 1, true, false); // Junior ikke-studerende
        swimmer3 = new Swimmer("navn3", "addresse", "nummer", "mail", LocalDate.parse("1996-11-28"), 2, true, true); // Senior studerende
        swimmer4 = new Swimmer("navn4", "addresse", "nummer", "mail", LocalDate.parse("1995-11-28"), 3, true, false); // Senior ikke-stud
        swimmer5 = new Swimmer("navn5", "addresse", "nummer", "mail", LocalDate.parse("1955-11-28"), 4, true, true); // Over 60 studerende
        swimmer6 = new Swimmer("navn6", "addresse", "nummer", "mail", LocalDate.parse("1956-11-28"), 4, true, false); // Over 60 ikke-studerende
        swimmerList = new ArrayList<>(Arrays.asList(swimmer1,swimmer2,swimmer3,swimmer4,swimmer5,swimmer6));
    }

    @Test
    void checkKontingent() {
        double s1expected = 750;
        double s2expected = 1000;
        double s3expected = 1200;
        double s4expected = 1600;
        double s5expected = 900;
        double s6expected = 1200;

        assertTrue(kasserer.getKontingentPrice(swimmer1) == s1expected);
        assertTrue(kasserer.getKontingentPrice(swimmer2) == s2expected);
        assertTrue(kasserer.getKontingentPrice(swimmer3) == s3expected);
        assertTrue(kasserer.getKontingentPrice(swimmer4) == s4expected);
        assertTrue(kasserer.getKontingentPrice(swimmer5) == s5expected);
        assertTrue(kasserer.getKontingentPrice(swimmer6) == s6expected);

        swimmer1.setActive(false);
        s1expected = 375;
        assertTrue(kasserer.getKontingentPrice(swimmer1) == s1expected);

        swimmer2.setActive(false);
        s2expected = 500;
        assertTrue(kasserer.getKontingentPrice(swimmer2) == s2expected);


    }

}