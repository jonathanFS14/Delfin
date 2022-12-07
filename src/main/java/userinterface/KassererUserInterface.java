package userinterface;

import domain.Swimmer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class KassererUserInterface extends SuperUI {
    public void kassererUI() {
        automaticRestanceCheck();
        userMenu();
    }

    private void userMenu() {
        int userInput;
        do {
            insertSeperatorLine(50);
            System.out.println("""
                    1. Ændre et medlems betalingsstatus
                    2. Tjek et medlems betalingsstatus
                    3. Vis forventet indtjening
                    4. Vis oversigt over medlemmer i restance
                    8. Log ud
                    9. Afslut Program""");

            userInput = readInt();
            insertSeperatorLine(50);

            switch (userInput) {
                case 1 -> editSwimmerHasPaid();
                case 2 -> checkMembersPaymentStatus();
                case 3 -> showExpectedIncome();
                case 4 -> showMembersInRestance();
                case 8 -> logOut();
                case 9 -> endProgram();
                default -> System.out.println("Ugyldigt valg");
            }
        } while (userInput != 9 && userInput != 8);
    }

    private void checkMembersPaymentStatus() {
        System.out.println("Indtast medlem du vil tjekke betalingsstatus på");
        insertSeperatorLine(50);
        Swimmer swimmer = controller.searchForMember(scanner.nextLine());

        if (swimmer != null) {
            insertSeperatorLine(50);
            if (swimmer.getHasPaid()) {
                System.out.print(swimmer.getName() + " har betalt kontingent den " + swimmer.getPaymentDate());
            } else {
                System.out.print(swimmer.getName() + " er i restance og skylder " + getKontingentPrice(swimmer) + " kr.\n");
            }
        } else {
            System.out.println("Kunne ikke finde medlemmet");
        }
    }

    private void showExpectedIncome() {
        double total = 0;
        ArrayList<Swimmer> localList = controller.getSwimmerList();

        // Junior
        System.out.println("Forventet indtjening fra Juniorer: ");
        for (Swimmer swimmer : localList) {
            if (ChronoUnit.YEARS.between(swimmer.getBirthday(), LocalDate.now()) < 18) {
                total += getKontingentPrice(swimmer);
                System.out.printf("| %-20s | %-7s kr |\n", swimmer.getName(), String.valueOf(getKontingentPrice(swimmer)));
            }
        }
        insertSeperatorLine(50);

        //Senior
        System.out.println("Forventet indtjening fra Seniorer: ");
        for (Swimmer swimmer : localList) {
            if (ChronoUnit.YEARS.between(swimmer.getBirthday(), LocalDate.now()) >= 18) {
                total += getKontingentPrice(swimmer);
                System.out.printf("|  %-20s |  %-7s kr |\n", swimmer.getName(), String.valueOf(getKontingentPrice(swimmer)));
            }
        }
        insertSeperatorLine(50);
        System.out.println("Total forventet betaling for næste år: " + total);
    }

    public double getKontingentPrice(Swimmer swimmer) {
        double price = 500; //Hvis medlemmet er passiv så er deres kontingent på 500.
        long age = ChronoUnit.YEARS.between(swimmer.getBirthday(), LocalDate.now());
        if (swimmer.isActive() && !swimmer.isArchived()) {
            if (18 > age) {
                price = 1000;
            } else if (18 <= age && age < 60) {
                price = 1600;
            } else if (age >= 60) {
                price = 1200;
            }
        }
        if (swimmer.isStudent()) {
            price *= 0.75;
        }
        return price;
    }

    private void showMembersInRestance() {
        System.out.println("Følgende medlemmer er i restance: ");
        insertSeperatorLine(50);
        int i = 1;
        for (Swimmer swimmer : controller.getSwimmerList())
            if (!swimmer.getHasPaid())
                System.out.println(i++ + ". " + swimmer.getName());
    }

    private void automaticRestanceCheck() {
        for (Swimmer swimmer : controller.getSwimmerList()) {
            if (swimmer.getPaymentDate().plusYears(1).isBefore(LocalDate.now()))
                swimmer.setHasPaid(false);

        }
    }

    private void editSwimmerHasPaid() {
        System.out.println("Indtast medlem der skal redigeres");
        insertSeperatorLine(50);
        Swimmer swimmer = controller.searchForMember(scanner.nextLine());
        insertSeperatorLine(50);

        if (swimmer != null) {
            System.out.println("Hvordan vil du ændre " + swimmer.getName() + "'s betalingsstatus");
            int inputActiveStatus;
            insertSeperatorLine(50);
            do {
                System.out.println("""
                        1. Sæt betalingsstatus til betalt
                        2. Sæt betalingsstatus til ikke betalt
                        3. Fortryd""");
                inputActiveStatus = readInt();
                insertSeperatorLine(50);
                switch (inputActiveStatus) {
                    case 1:
                        swimmer.setHasPaid(true);
                        swimmer.setPaymentDate(LocalDate.now());
                        System.out.println("Betalingsstatus ændret til betalt");
                        break;
                    case 2:
                        swimmer.setHasPaid(false);
                        System.out.println("Betalingsstatus ændret til ikke betalt");
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
