package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void startProgram() {
        userMenu();
    }

    private void userMenu() {
        int userChoice;
        do {
            System.out.println("""
                    Velkommen til Delfinen svømmeklub
                    Midlertidlig UI, vi gør det pænere senere x(
                                    
                    Hvad vil du gøre?                       
                    1. Opret ny svømmer
                    2. Rediger en eksisterende svømmer
                                    
                    9. afslut programmet
                    """);
            userChoice = readInt();
            switch (userChoice) {
                case 1 -> createNewSwimmer();
                case 2 -> editSwimmer();
                case 9 -> {
                    System.out.println("\n Lukker programmet");
                    System.exit(0);
                }
                default -> System.out.println("Ugyldigt valg");
            }
        } while (userChoice != 9);
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


    private void editSwimmer() {
        System.out.println("Indtast medlem der skal redigeres");
        String searchParameter = scanner.nextLine();

        ArrayList<Swimmer> localSwimmerList = controller.searchForSwimmers(searchParameter);

        if (localSwimmerList.isEmpty()) {
            System.out.println("Kunne ikke finde medlemmet");
        } else {
            System.out.println("Vælg hvem der skal redigeres");
            for (Swimmer swimmer : localSwimmerList) {
                System.out.println(localSwimmerList.indexOf(swimmer) + 1 + ". " + swimmer.getName() + "\t" + swimmer.getMemberID() );
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
                    int newPhoneNumberInt = readInt();
                    String newPhoneNumberString = String.valueOf(newPhoneNumberInt);
                    if (!newPhoneNumberString.isEmpty()) {
                        swimmer.setPhoneNumber(newPhoneNumberString);
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
            }
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

}
