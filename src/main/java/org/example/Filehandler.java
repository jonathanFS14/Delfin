package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    String fileName = "swimmerDatabase.csv";

    public ArrayList<Swimmer> retrieveSwimmerDatabase() {
        ArrayList<Swimmer> swimmerListFile = new ArrayList<Swimmer>();
        try {
            Scanner sc = new Scanner(new File(fileName));

            while (sc.hasNextLine()) {
                String linje = sc.nextLine();
                String[] attributes = linje.split(",");
                Swimmer swimmer = new Swimmer(
                        attributes[0],
                        attributes[1],
                        attributes[2],
                        attributes[3],
                        LocalDate.parse(attributes[4]),
                        Integer.parseInt(attributes[5]),
                        Boolean.parseBoolean(attributes[6]),
                        Boolean.parseBoolean(attributes[7]));
                swimmerListFile.add(swimmer);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return swimmerListFile;
    }

    public void overwriteSwimmerDatabase(ArrayList<Swimmer> swimmerList) {
        try {
            PrintStream out = new PrintStream(fileName);
            for (Swimmer swimmer : swimmerList) {
                out.println(toFileWriter(swimmer));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String toFileWriter(Swimmer swimmer) {
        return (swimmer.getName() + "," + swimmer.getAddress() + "," + swimmer.getPhoneNumber()+ "," +
                swimmer.getMail() + "," + swimmer.getBirthday() + "," + swimmer.getMemberID() + "," +
                swimmer.isActive() + "," + swimmer.isCompetitor() + "," + swimmer.isStudent() + "," +
                swimmer.isArchived());
    }

}
