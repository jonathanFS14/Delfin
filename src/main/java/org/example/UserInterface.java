package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    Controller controller;

    public UserInterface(){
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void startProgram() {
        initialLoad();
        userMenu();
    }

    public void initialLoad(){
        controller.initialLoad();
    }
    public void endProgram(){
        System.out.println("\n Lukker programmet");
        controller.overwriteSwimmerDatabase();
        System.exit(0);
    }

    private void userMenu() {
        int userChoice;
        do {
            System.out.println("""
                    Velkommen til Delfinen svømmeklub
                                    
                    Hvad vil du gøre?                       
                    1. Opret ny svømmer/ gendan gammel svømmer
                    2. Rediger en eksisterende svømmer
                    3. Vis alle svømmere
                    
                    
                    8. Arkivér svømmer (afmeld)                
                    9. afslut programmet
                    """);
            userChoice = readInt();
            switch (userChoice) {
                case 1 -> createSwimmerMenu();
                case 2 -> editSwimmer();
                case 3 -> showAllSwimmers();

                case 9 -> endProgram();
                default -> System.out.println("Ugyldigt valg");
            }
        } while (userChoice != 9);
    }

    private void createSwimmerMenu(){
        System.out.println("""
                Vil du oprette et helt nyt medlem eller gendanne et gammel medlem?
                1. Opret nyt medlem
                2. Gendan gammelt medlem
                9. fortryd
                """);
        int input = readInt();
        switch (input){
            case 1 -> createNewSwimmer();
            case 2 -> restoreArchivedSwimmer();
            case 9 -> System.out.println("Vender tilbage til menuen");
            default -> System.out.println("Ugyldig input");
        }
    }

    private void restoreArchivedSwimmer() {

        System.out.println("Indtast medlem der skal genoprettes");
        String searchParameter = scanner.nextLine();

        ArrayList<Swimmer> localSwimmerList = controller.database.searchForArchived(searchParameter);

        if (localSwimmerList.isEmpty()) {
            System.out.println("Kunne ikke finde medlemmet");
        } else {
            System.out.println("Vælg hvem der skal redigeres (indtast tal)");
            for (Swimmer swimmer : localSwimmerList) {
                System.out.println(localSwimmerList.indexOf(swimmer) + 1 + ". " + swimmer.getName());
            }
        }
        int chooseSwimmer = readInt();
        Swimmer swimmer = localSwimmerList.get(chooseSwimmer - 1);

        swimmer.setArchived(false);
        System.out.println(swimmer.getName() + " er nu genoprettet.");

    }

    private void createNewSwimmer() {
        String navn;
        String address;
        String phoneNumber;
        String mail;
        String birthdayString;
        boolean isCompetitor;
        boolean isStudent;

        System.out.println("Indtast svømmerens navn");
        navn = readString();
        System.out.println("Indtast svømmerens addresse");
        address = readString();
        System.out.println("Indtast svømmerens telefonnummer");
        phoneNumber = readString();
        System.out.println("Indtast svømmerens mail");
        mail = readString();
        System.out.println("Indtast svømmerens fødselsdato (på formen ÅÅÅÅ-MM-DD");
        birthdayString = readString();
        LocalDate birthday = LocalDate.parse(birthdayString);
        System.out.println("Er det en konkurrencesvømmer? ja/nej");
        isCompetitor = yesOrNoToBoolean(readString());
        System.out.println("Er svømmeren studerende?");
        isStudent = yesOrNoToBoolean(readString());

        controller.createSwimmer(navn,address,phoneNumber,mail,birthday,isCompetitor,isStudent);
    }


    private void editSwimmer() {
        System.out.println("Indtast medlem der skal redigeres");
        String searchParameter = scanner.nextLine();

        ArrayList<Swimmer> localSwimmerList = controller.searchForSwimmers(searchParameter);

        if (localSwimmerList.isEmpty()) {
            System.out.println("Kunne ikke finde medlemmet");
        } else {
            System.out.println("Vælg hvem der skal redigeres");
            for (Swimmer swimmer : localSwimmerList) {
                System.out.println(localSwimmerList.indexOf(swimmer) + 1 + ". " + swimmer.getName());
            }
            int chooseSwimmer = readInt();
            Swimmer swimmer = localSwimmerList.get(chooseSwimmer - 1);

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
                //TODO Lav hver switch til sin egen hjælpeemtode.
                case 1:
                    System.out.println("Rediger " + swimmer.getName() + " eller tryk enter for at fortryde");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        swimmer.setName(newName);
                    }
                    break;
                case 2:
                    System.out.println("Rediger " + swimmer.getAddress() + " eller tryk enter for at fortryde");
                    String newAddress = scanner.nextLine();
                    if (!newAddress.isEmpty()) {
                        swimmer.setAddress(newAddress);
                    }
                    break;
                case 3:
                    //TODO test om dette virker - vil sikre der ikke kan komme bogstaver i telefonnummeret.
                    System.out.println("Rediger " + swimmer.getPhoneNumber() + " eller tryk enter for at fortryde");
                    String newPhoneNumber = readString();
                    if(!newPhoneNumber.isEmpty()){
                        swimmer.setPhoneNumber(newPhoneNumber);
                    }

                    break;
                case 4:
                    System.out.println("Rediger " + swimmer.getMail() + " eller tryk enter for at fortryde");
                    String newMail = scanner.nextLine();
                    if (!newMail.isEmpty()) {
                        swimmer.setAddress(newMail);
                    }
                    break;
                case 5:
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
                    break;
                case 6:
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
                            case 1:
                                swimmer.setCompetitor(true);
                                break;
                            case 2:
                                swimmer.setCompetitor(false);
                                break;
                            case 3:
                                System.out.println("Vender tilbage");
                            default:
                                System.out.println("ugyldigt input, prøv igen");
                        }
                    } while (inputCompetitionStatus != 1 && inputCompetitionStatus != 2 && inputCompetitionStatus != 3);
                    break;
                case 7:
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
                    break;
                case 9:
                    System.out.println("Vender tilbage til menuen");
                    break;
                default:
                    System.out.println("Ugyldig input");
                    break;
            }
        }
    }


private void showAllSwimmers(){
        for(Swimmer swimmer : controller.getSwimmerList()){
            System.out.println(swimmer.getName() + " " + swimmer.getCreationDate());
        }
}

    private boolean yesOrNoToBoolean(String yesOrNo) {
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

    //Sørger for at input ikke er tomt. Strukturen i readInt virker ikke for string af en eller anden grund.
    private String readString(){
        String readString;
        do{
        readString = scanner.nextLine();
        if(readString.isEmpty()){
            System.out.println("Input må ikke være tom");
        }
        }
        while(readString.isEmpty());
        return readString;
    }
}

