package datasource;

import domain.Events;
import domain.SwimTime;
import domain.Swimmer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    private final String swimmerFileDatabase = "swimmerDatabase.csv";
    private final String swimTimeFileDatabase = "SwimTimeDatabase.csv";

    public ArrayList<Swimmer> retrieveSwimmerDatabase() {
        ArrayList<Swimmer> swimmerListFile = new ArrayList<Swimmer>();
        try {
            Scanner sc = new Scanner(new File(swimmerFileDatabase));

            while (sc.hasNextLine()) {
                String linje = sc.nextLine();
                String[] attributes = linje.split(";");
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
                        Boolean.parseBoolean(attributes[11]), // isArchived
                        LocalDate.parse(attributes[12]) // paymentDate

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
            PrintStream out = new PrintStream(swimmerFileDatabase);
            for (Swimmer swimmer : swimmerList) {
                out.println(swimmerToFileWriter(swimmer));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String swimmerToFileWriter(Swimmer swimmer) {
        return (swimmer.getName() + ";" + swimmer.getAddress() + ";" + swimmer.getPhoneNumber() + ";" +
                swimmer.getMail() + ";" + swimmer.getBirthday() + ";" + swimmer.getCreationDate() + ";" +
                swimmer.getMemberID() + ";" + swimmer.getHasPaid() + ";" + swimmer.isActive() + ";" +
                swimmer.isCompetitor() + ";" + swimmer.isStudent() + ";" + swimmer.isArchived()) + ";" +
                swimmer.getPaymentDate();
    }


    public ArrayList<SwimTime> retrieveSwimTimeDatabase() {
        ArrayList<SwimTime> swimTimeListFile = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(swimTimeFileDatabase));

            while (sc.hasNextLine()) {
                String linje = sc.nextLine();
                String[] attributes = linje.split(";");
                SwimTime swimTime = new SwimTime(
                        Integer.parseInt(attributes[0]),
                        Double.parseDouble(attributes[1]),
                        Events.valueOf(attributes[2]),
                        attributes[3],
                        LocalDate.parse(attributes[4])
                );
                swimTimeListFile.add(swimTime);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return swimTimeListFile;
    }

    public void overwriteSwimTimeDatabase(ArrayList<SwimTime> swimTimeList) {
        try {
            PrintStream out = new PrintStream(swimTimeFileDatabase);
            for (SwimTime swimTime : swimTimeList) {
                out.println(swimTimeToFileWriter(swimTime));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String swimTimeToFileWriter(SwimTime swimTime) {
        return swimTime.getMemberID() + ";"
                + swimTime.getTime() + ";"
                + swimTime.getEvent() + ";"
                + swimTime.getPlaceSet() + ";"
                + swimTime.getDateSet() + ";";
    }

}
