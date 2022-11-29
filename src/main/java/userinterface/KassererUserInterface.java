package userinterface;

import domain.Controller;
import domain.Swimmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class KassererUserInterface {
    Scanner scanner;
    Controller controller;

    public KassererUserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void kassererUI() {
        initialLoad();
        int userInput;
        do {
            System.out.println("""
                1. Ændre et medlems betalingsstatus
                2. Tjek et medlems betalingsstatus
                3. Vis oversigt over forventet indtjening
                4. Vis oversigt over medlemmer i restance
                
                9. Afslut Program""");
            userInput = readInt();

            switch (userInput) {
                case 1 -> editSwimmerHasPaid();
                case 2 -> checkMembersPaymentStatus();
                case 3 -> showExpectedIncome();
                case 4 -> showMembersInRestance();
                case 9 -> endProgram();
                default -> System.out.println("Ugyldigt valg");
            }
        } while (userInput != 9);
    }

    private void endProgram() {
        System.out.println("\n Lukker programmet");
        controller.overwriteSwimmerDatabase();
        System.exit(0);
    }

    private Swimmer searchForMember(String searchParameter) {

        ArrayList<Swimmer> localSwimmerList = controller.searchForMembers(searchParameter);
        Swimmer swimmer = null;

            if (!localSwimmerList.isEmpty()) {
                System.out.println("Vælg hvem du vil tjekke ");
                for (Swimmer s : localSwimmerList) {
                    System.out.println(localSwimmerList.indexOf(s) + 1 + ". " + s.getName());
                }
                int chooseSwimmer = readInt();
                swimmer = localSwimmerList.get(chooseSwimmer - 1);
            }

        return swimmer;
    }

    private void checkMembersPaymentStatus() {
        System.out.println("Indtast medlem du vil tjekke betalingsstatus på");
        Swimmer swimmer = searchForMember(scanner.nextLine());

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


    private void initialLoad() {
        controller.initialLoad();
        
    }

    private void editSwimmerHasPaid() {
        System.out.println("Indtast medlem der skal redigeres");

        Swimmer swimmer = searchForMember(scanner.nextLine());

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

    private int readInt() {
        while (!scanner.hasNextInt()) {
            String text = scanner.next();
            System.out.println(text + " er ugyldig input, indtast igen.");
        }
        int result;
        result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private String readString() {
        String readString;
        do {
            readString = scanner.nextLine();
            if (readString.isEmpty()) {
                System.out.println("Input må ikke være tom");
            }
        }
        while (readString.isEmpty());
        return readString;
    }

}
