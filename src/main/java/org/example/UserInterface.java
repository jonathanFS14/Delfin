package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void startProgram(){
    //TODO basal UI så man kan teste
        System.out.println("Velkommen til systemet");
        System.out.println("Midlertidlig UI, vi gør det pænere senere x(");
        System.out.println("Hvad vil du gøre?");
        System.out.println("""
                1. Opret ny svømmer
                """);
        int valg = scanner.nextInt();

        switch (valg) {
            case 1 -> menuCreateNewSwimmer();
        }
    }

    public void menuCreateNewSwimmer() {
        String navn;
        String address;
        String phoneNumber;
        String mail;
        String birthdayString;
        boolean isCompetitor;
        boolean isStudent;

        System.out.println("Indtast svømmerens navn");
        navn = scanner.nextLine();
        System.out.println("Indtast svømmerens addresse");
        address = scanner.nextLine();
        System.out.println("Indtast svømmerens telefonnummer");
        phoneNumber = scanner.nextLine();
        System.out.println("Indtast svømmerens mail");
        mail = scanner.nextLine();
        System.out.println("Indtast svømmerens fødselsdato (på formen ÅÅÅÅ-MM-DD");
        birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString);
        System.out.println("Er det en konkurrencesvømmer? ja/nej");
        isCompetitor = yesOrNoToBoolean(scanner.nextLine());
        System.out.println("Er svømmeren studerende?");
        isStudent = yesOrNoToBoolean(scanner.nextLine());


        controller.createSwimmer(navn,address,phoneNumber,mail,birthday,isCompetitor,isStudent);
    }

    public boolean yesOrNoToBoolean(String yesOrNo) {
        boolean answer = false;
        if (yesOrNo.equalsIgnoreCase("ja")){
            answer = true;
        } else if (yesOrNo.equalsIgnoreCase("nej")){
            answer = false;
        } else {
            System.out.println("Forkert input.");
            // TODO burde køre i loop sådan at man skal prøve igen hvis input er forkert.
        }
        return answer;
    }


}
