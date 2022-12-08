package userinterface;

import domain.Swimmer;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class FormandUserInterface extends SuperUI {

    public void formandUI() {
        userMenu();
    }

    private void userMenu() {
        int userInput;
        do {
            insertSeperatorLine(50);
            System.out.println("""                
                    Hvad vil du gøre?                     
                    1. Opret ny svømmer/ gendan gammel svømmer
                    2. Rediger en eksisterende svømmer
                    3. Vis alle svømmere
                    7. Arkivér svømmer (afmeld)
                    8. Log ud                
                    9. afslut programmet""");

            userInput = readInt();
            switch (userInput) {
                case 1 -> createSwimmerMenu();
                case 2 -> editSwimmer();
                case 3 -> {
                    insertSeperatorLine(215);
                    controller.printAllMembers();
                    insertSeperatorLine(215);
                }
                case 7 -> archiveSwimmer();
                case 8 -> logOut();
                case 9 -> endProgram();
                default -> System.out.println("Ugyldigt valg");
            }

        } while (userInput != 9 && userInput != 8);
    }

    private void createSwimmerMenu() {
        insertSeperatorLine(50);
        System.out.println("""
                Vil du oprette et helt nyt medlem eller gendanne et gammel medlem?
                1. Opret nyt medlem
                2. Gendan gammelt medlem
                9. fortryd""");
        insertSeperatorLine(50);
        int input = readInt();
        switch (input) {
            case 1 -> createNewSwimmer();
            case 2 -> restoreArchivedSwimmer();
            case 3 -> controller.printAllMembers();

            case 8 -> archiveSwimmer();
            case 9 -> System.out.println("Vender tilbage til menuen");
            default -> System.out.println("Ugyldig input");
        }
    }

    private void archiveSwimmer() {
        insertSeperatorLine(50);
        System.out.println("Indtast medlem der skal arkiveres");
        String searchParameter = scanner.nextLine();
        Swimmer swimmer = controller.searchForMember(searchParameter);
        if(swimmer == null){
            System.out.println("Kunne ikke finde medlem med " + searchParameter);
        }
        else {
            swimmer.setArchived(true);
            swimmer.setHasPaid(false);
            insertSeperatorLine(50);
            System.out.println(swimmer.getName() + " er nu arkiveret.");
        }
    }

    private void restoreArchivedSwimmer() {
        insertSeperatorLine(50);
        System.out.println("Indtast medlem der skal genoprettes");
        String searchParameter = scanner.nextLine();

        ArrayList<Swimmer> localSwimmerList = controller.searchForArchived(searchParameter);

        if (localSwimmerList.isEmpty()) {
            System.out.println("Kunne ikke finde medlemmet");
        } else {
            System.out.println("Vælg hvem der skal genoprettes (indtast tal)");
            for (Swimmer swimmer : localSwimmerList) {
                System.out.println(localSwimmerList.indexOf(swimmer) + 1 + ". " + swimmer.getName());
            }
        }
        int chooseSwimmer = readInt();
        Swimmer swimmer = localSwimmerList.get(chooseSwimmer - 1);

        swimmer.setArchived(false);
        insertSeperatorLine(50);
        System.out.println(swimmer.getName() + " er nu genoprettet.");

    }

    private void createNewSwimmer() {
        String navn;
        String address;
        String phoneNumber;
        String mail;
        boolean isCompetitor;
        boolean isStudent;
        LocalDate birthday;

        System.out.println("Indtast svømmerens navn");
        navn = readString();
        System.out.println("Indtast svømmerens addresse");
        address = readString();
        System.out.println("Indtast svømmerens telefonnummer");
        phoneNumber = readString();
        System.out.println("Indtast svømmerens mail");
        mail = readString();
        System.out.println("Indtast svømmerens fødselsdato (med formattet ÅÅÅÅ-MM-DD");
        birthday = validateBirthdayString();
        System.out.println("Er det en konkurrencesvømmer? ja/nej");
        isCompetitor = yesOrNoToBoolean();
        System.out.println("Er svømmeren studerende? ja/nej");
        isStudent = yesOrNoToBoolean();
        insertSeperatorLine(50);

        System.out.println("Du er ved at tilføje følgende svømmer:" + "\nNavn: " + navn + "\nAddresse: " + address + "\nTelefonnr: " + phoneNumber + "\nMail: " + mail + "\nFødselsdato: " + birthday + "\nEr konkurrencesvømmer: " + booleanToYesOrNo(isCompetitor) + "\nEr studerende: " + booleanToYesOrNo(isStudent) + "\n\nBekræft venligst (Ja/Nej) ");
        insertSeperatorLine(50);
        if (yesOrNoToBoolean()) {
            controller.createSwimmer(navn, address, phoneNumber, mail, birthday, isCompetitor, isStudent);
            System.out.println(navn + " er tilføjet");
        } else System.out.println("Annulerer...");
    }

    private boolean validateBirthdayLocaleDate(LocalDate birthday) {
        return Period.between(birthday, LocalDate.now()).isNegative();
    }

    private LocalDate validateBirthdayString() {
        LocalDate localDate = null;
        String input;
        do {
            input = readString();
            try {
                localDate = LocalDate.parse(input);
                if (validateBirthdayLocaleDate(localDate)) {
                    System.out.println("den valgte fødselsdag er ikke mulig");
                    localDate = null;
                }
            } catch (Exception e) {
                System.out.println("den valgte fødselsdag er ikke mulig");
            }

        } while (localDate == null);
        return localDate;
    }

    private void editSwimmer() {
        System.out.println("Indtast medlem der skal redigeres");
        String searchParameter = scanner.nextLine();
        Swimmer swimmer = controller.searchForMember(searchParameter);
        if(swimmer == null){
            System.out.println("Kunne ikke finde medlem med " + searchParameter);
        }
        else {
            System.out.println("""
                    Hvad vil du redigere?
                    1. Navn
                    2. Addresse
                    3. Telefon nummer
                    4. Mail
                    5. Aktivitetsstatus
                    6. Konkurence status
                    7. Studie status
                    9. Fortryd
                    """);
            int menuInput = readInt();

            switch (menuInput) {
                case 1 -> editSwimmerName(swimmer);
                case 2 -> editSwimmerAddress(swimmer);
                case 3 -> editSwimmerPhoneNumber(swimmer);
                case 4 -> editSwimmerMail(swimmer);
                case 5 -> editSwimmerActiveStatus(swimmer);
                case 6 -> editSwimmerCompetitionStatus(swimmer);
                case 7 -> editStudyStatus(swimmer);
                case 9 -> System.out.println("Vender tilbage til menuen");
                default -> System.out.println("Ugyldig input");
            }
        }
    }

    private void editSwimmerName(Swimmer swimmer) {
        System.out.println("Rediger " + swimmer.getName() + " eller tryk enter for at fortryde");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            swimmer.setName(newName);
        }
    }

    private void editSwimmerAddress(Swimmer swimmer) {
        System.out.println("Rediger " + swimmer.getAddress() + " eller tryk enter for at fortryde");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            swimmer.setAddress(newAddress);
        }
    }

    private void editSwimmerPhoneNumber(Swimmer swimmer) {
        System.out.println("Rediger " + swimmer.getPhoneNumber() + " eller tryk enter for at fortryde");
        String newPhoneNumber = scanner.nextLine();
        if (!newPhoneNumber.isEmpty()) {
            swimmer.setPhoneNumber(newPhoneNumber);
        }
    }

    private void editSwimmerMail(Swimmer swimmer) {
        System.out.println("Rediger " + swimmer.getMail() + " eller tryk enter for at fortryde");
        String newMail = scanner.nextLine();
        if (!newMail.isEmpty()) {
            swimmer.setMail(newMail);
        }
    }

    private void editSwimmerActiveStatus(Swimmer swimmer) {
        System.out.println("Sæt" + swimmer.getName() + "'s aktivitetsstatus");
        int inputActiveStatus;
        do {
            System.out.println("""
                    1. Aktivt medlemsskab
                    2. Passivt medlemsskab
                    3. fortryd
                    """);
            inputActiveStatus = readInt();
            switch (inputActiveStatus) {
                case 1:
                    swimmer.setActive(true);
                    break;
                case 2:
                    swimmer.setActive(false);
                    break;
                case 3:
                    System.out.println("Vender tilbage");
                default:
                    System.out.println("ugyldigt input, prøv igen");
            }
        } while (inputActiveStatus != 1 && inputActiveStatus != 2 && inputActiveStatus != 3);
    }

    private void editSwimmerCompetitionStatus(Swimmer swimmer) {
        System.out.println("Sæt" + swimmer.getName() + "'s konkurrencestatus");
        int inputCompetitionStatus;
        do {
            System.out.println("""
                    1. Konkurrencesvømmer
                    2. Motionssvømmer
                    3. fortryd
                    """);
            inputCompetitionStatus = readInt();
            switch (inputCompetitionStatus) {
                case 1 -> swimmer.setCompetitor(true);
                case 2 -> swimmer.setCompetitor(false);
                case 3 -> System.out.println("Vender tilbage");
                default -> System.out.println("ugyldigt input, prøv igen");
            }
        } while (inputCompetitionStatus != 1 && inputCompetitionStatus != 2 && inputCompetitionStatus != 3);
    }

    private void editStudyStatus(Swimmer swimmer) {
        System.out.println("Sæt" + swimmer.getName() + "'s studiestatus");
        int inputStudyStatus;
        do {
            System.out.println("""
                    1. Medlem er studerende
                    2. Medlem er ikke studerende
                    3. fortryd
                    """);
            inputStudyStatus = readInt();
            switch (inputStudyStatus) {
                case 1:
                    swimmer.setStudent(true);
                    break;
                case 2:
                    swimmer.setStudent(false);
                    break;
                case 3:
                    System.out.println("Vender tilbage");
                default:
                    System.out.println("ugyldigt input, prøv igen");
            }
        } while (inputStudyStatus != 1 && inputStudyStatus != 2 && inputStudyStatus != 3);
    }
}