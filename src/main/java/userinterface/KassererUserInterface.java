package userinterface;

import domain.Controller;
import domain.Swimmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class KassererUserInterface extends SuperUI {

    public void kassererUI() {
        initialLoad();
        int userInput;
        do {
            System.out.println("""
                1. Ændre et medlems betalingsstatus
                2. Tjek et medlems betalingsstatus
                3. Vis oversigt over forventet indtjening
                4. Vis oversigt over medlemmer i restance
                
                8. Log ud
                9. Afslut Program""");
            userInput = readInt();

            switch (userInput) {
                case 1 -> editSwimmerHasPaid();
                case 2 -> checkMembersPaymentStatus();
                case 3 -> showExpectedIncome();
                case 4 -> showMembersInRestance();
                case 8 -> logOut();
                case 9 -> endProgram();
                default -> System.out.println("Ugyldigt valg");
            }
        } while (true);
    }

    /*private void endProgram() {
        System.out.println("\n Lukker programmet");
        controller.overwriteSwimmerDatabase();
        System.exit(0);
    }*/

    private void checkMembersPaymentStatus() {
        System.out.println("Indtast medlem du vil tjekke betalingsstatus på");
        Swimmer swimmer = controller.searchForMember(scanner.nextLine());

        if (swimmer != null) {
            if (swimmer.getHasPaid()) {
                System.out.println("Medlemmet har betalt kontingent den " + swimmer.getPaymentDate());
            } else {
                System.out.println("Medlemmet er i restance");
            }
        } else {
            System.out.println("Kunne ikke finde medlemmet");
        }

    }

    private void showExpectedIncome() {
    }

    private void showMembersInRestance() {
    }


    private void automaticRestanceCheck() {
        for (Swimmer swimmer: controller.getSwimmerList()) {
            if (swimmer.getPaymentDate().plusYears(1).isBefore(LocalDate.now()))
                swimmer.setHasPaid(false);

        }
    }

    public void logOut(){
        System.out.println("""
                   Do you want to logOut?
                   ja/nej
                   """);
        boolean input = yesOrNoToBoolean();

        if(input){
            controller.overwriteSwimmerDatabase();
            MainUI mainUI = new MainUI();
            mainUI.login();
        }

    }

    private void editSwimmerHasPaid() {
        System.out.println("Indtast medlem der skal redigeres");
        Swimmer swimmer = controller.searchForMember(scanner.nextLine());

        if (swimmer != null) {
            System.out.println("Sæt " + swimmer.getName() + "'s betalingsstatus");
            int inputActiveStatus;
            do {
                System.out.println("""
                                1. Sæt betalingsstatus til betalt
                                2. Sæt betalingsstatus til ikke betalt
                                3. fortryd
                                """);
                inputActiveStatus = readInt();
                switch (inputActiveStatus) {
                    case 1:
                        swimmer.setHasPaid(true);
                        swimmer.setPaymentDate(LocalDate.now());
                        break;
                    case 2:
                        swimmer.setHasPaid(false);
                        break;
                    case 3:
                        System.out.println("Vender tilbage");
                    default:
                        System.out.println("ugyldigt input, prøv igen");
                }
            } while (inputActiveStatus != 1 && inputActiveStatus != 2 && inputActiveStatus != 3);
        } else {
            System.out.println("Kunne ikke finde medlemmet");
        }
    }


}
