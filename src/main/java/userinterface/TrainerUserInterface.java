package userinterface;

import Comparator.TimeComparator;
import domain.Events;
import domain.SwimTime;
import domain.Swimmer;

import java.util.ArrayList;
import java.util.Collections;

public class TrainerUserInterface extends SuperUI {


    public void trainerUI() {
        controller.setCompetitorsToTeams();
        userMenu();
    }

    public void userMenu() {
        int userInput;
        do {
            System.out.println("""    
                                 
                     Hvad vil du gøre?                       
                    1. Se hold
                    2. indtast tid
                    3. se en svømmers profil
                    4. top 5 hurtigste svømmere
                    8. Log ud
                    9. Afslut program
                     """);
            userInput = readInt();
            switch (userInput) {
                case 1 -> showTeamMenu();
                case 2 -> setTimeForSwimmer();
                case 3 -> showSwimmerProfile();
                case 4 -> showTopFive();
                case 8 -> logOut();
                case 9 -> endProgram();
                default -> System.out.println("Ugyldig input");
            }
        }
        while (userInput != 9 && userInput != 8);
    }

    public void showTeamMenu() {
        int userChoice;
        do {
            controller.initialLoad();
            System.out.println("""
                    hvilket hold vil du se?
                    1. Senior holdet
                    2. Junior holdet
                    3. Vend tilbage
                    """);
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> printTeam(controller.getSeniorTeam());
                case 2 -> printTeam(controller.getJuniorTeam());
                case 3 -> userMenu();
                default -> System.out.println("Ugyldig input");
            }
        }
        while (userChoice != 1 || userChoice != 2 || userChoice != 3);
    }

    public void setTimeForSwimmer() {
        System.out.println("Indtast hvilken svømmer du vil registrere en tid for");
        String searchParameter = readString();
        Swimmer swimmer = controller.searchForMember(searchParameter);

        int memberID = swimmer.getMemberID();

        Events event = controller.selectEvent();

        System.out.println("Indtast tid");
        double swimTime = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Indtast stævnet, hvor tiden blev sat (Tryk enter hvis det var en træningstid)");
        String userInput = scanner.nextLine();
        String placeSet;
        if (userInput.isEmpty())
            placeSet = "Træning";
        else
            placeSet = userInput;

        System.out.println("\nDu er ved at tilføje følgende tid til " + swimmer.getName() +
                "\nTid: " + swimTime +
                "\nDisciplin: " + event +
                "\nHvorhenne: " + placeSet +
                "\n\nBekræft venligst (Ja/Nej) "
        );
        if (yesOrNoToBoolean())
            controller.createSwimTime(memberID, swimTime, event, placeSet);
    }


    private void printTeam(ArrayList<Swimmer> teamList) {
        for (Swimmer swimmer : teamList) {
            System.out.println(teamList.indexOf(swimmer) + 1 + ". " + swimmer.getName());
        }
    }

    private void showTopFive() {
        int userChoice;
        ArrayList<Swimmer> localTeamList = new ArrayList<>();
        ArrayList<SwimTime> localSwimTimeList = new ArrayList<>();
        ArrayList<SwimTime> top5Times = new ArrayList<>();

        Events event;

        //Vælger holdet man vil se tider for
        System.out.println("""
                Indtast hvilket hold du ønsker at finde top 5
                1. Junior
                2. Senior""");
        do {
            userChoice = scanner.nextInt();
            if (userChoice == 1) {
                localTeamList = controller.getJuniorTeam();
            } else if (userChoice == 2) {
                localTeamList = controller.getSeniorTeam();
            }
        } while (userChoice != 1 && userChoice != 2);

        //Metode til at vælge disciplin med indbygget menu
        event = controller.selectEvent(); //Vælger disciplin

        //Disse to for loops samler alle svømmetider for et bestemt hold i en bestemt disciplin
        for (Swimmer s : localTeamList) {
            for (SwimTime st : controller.getSwimTimeList()) {
                if (st.getMemberID() == s.getMemberID() && st.getEvent() == event && !st.getPlaceSet().equals("Træning")) {
                    localSwimTimeList.add(st);
                }
            }
        }

        //Tjekker om det man har søgt på er tomt.
        if (localSwimTimeList.isEmpty()) {
            System.out.println("Ingen tider");
        } else {
            //Sorterer den samlede liste af tider efter hurtigeste tid
            Collections.sort(localSwimTimeList, new TimeComparator());

            //Flytter kun den hurtigste tid for hver svømmer i en liste.
            for (Swimmer s : localTeamList) {
                for (SwimTime st : localSwimTimeList) {
                    if (s.getMemberID() == st.getMemberID()) {
                        top5Times.add(st);
                        break;
                    }
                }
            }

            sortAndPrintTop5(top5Times);
        }
    }
    private void sortAndPrintTop5(ArrayList<SwimTime> top5Times) {
        //Sortere top5 listen og printer de 5 hurtigeste tider. Hvis der ikke er 5 tider så fyldes resten ud med "N/A"
        Collections.sort(top5Times, new TimeComparator());
        for (int i = 0; i < 5; i++) {
            try {
                SwimTime st = top5Times.get(i);
                System.out.println(i + 1 + ". " + getSwimmerNameFromID(st.getMemberID()) + ": " + st.getTime());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(i + 1 + ". N/A");
            }
        }
    }

    private String getSwimmerNameFromID(int memberID) {
        String swimmerName = "SwimmerName";
        for (Swimmer s : controller.getSwimmerList()) {
            if (memberID == s.getMemberID()) {
                swimmerName = s.getName();
                break;
            }
        }
        return swimmerName;
    }

    private void showSwimmerProfile() {
        System.out.println("Indtast medlem du vil kigge på");
        Swimmer swimmer = controller.searchForMember(scanner.nextLine());
        int searchID = swimmer.getMemberID();
        ArrayList<SwimTime> swimTimeList = controller.getSwimTimeList();
        System.out.println(String.format("┃ %-20s │  %-10s │  %-20s ┃", "Disciplin", "Tid", "Sted"));
        for (SwimTime s : swimTimeList) {
            if (searchID == s.getMemberID()) {
                System.out.println(s.printSwimTime());
            }
        }
    }
}

