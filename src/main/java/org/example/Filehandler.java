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
                        attributes[0], // name
                        attributes[1], // address
                        attributes[2], // phoneNumber
                        attributes[3], // mail
                        LocalDate.parse(attributes[4]), // birthday
                        LocalDate.parse(attributes[5]), // creationDate
                        Integer.parseInt(attributes[6]), // memberID
                        Boolean.parseBoolean(attributes[7]), // hasPaid
                        Boolean.parseBoolean(attributes[8]), // isActive
                        Boolean.parseBoolean(attributes[9]), // isCompetitor
                        Boolean.parseBoolean(attributes[10]), // isStudent
                        Boolean.parseBoolean(attributes[11]) // isArchived
                );
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
                swimmer.getMail() + "," + swimmer.getBirthday() + "," + swimmer.getCreationDate() + "," +
                swimmer.getMemberID() + "," + swimmer.isHasPaid() +  "," + swimmer.isActive() + "," +
                swimmer.isCompetitor() + "," + swimmer.isStudent() + "," + swimmer.isArchived());
    }

}
