package userinterface;

import Comparators.TimeComparator;
import domain.Controller;
import domain.Events;
import domain.SwimTime;
import domain.Swimmer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
        System.out.println("Indtast hvilket hold du ønsker at finde top 5, junior/senior");
        int userChoice;
        ArrayList<Swimmer>localTeamList = new ArrayList<>();
        ArrayList<SwimTime>localSwimTimeList = new ArrayList<>();
        Events event;

        do {
            userChoice = scanner.nextInt();
            if (userChoice == 1) {
                //team = "Junior";
                localTeamList = controller.getJuniorTeam();
            } else if (userChoice == 2) {
               // team = "Senior";
                localTeamList = controller.getSeniorTeam();
            }
        } while (userChoice != 1 && userChoice != 2);


        event = controller.selectEvent(); //Vælger disciplin

        for(Swimmer s : localTeamList){ //Disse to for loops samler alle svømmetider for et bestemt hold i en bestemt disciplin
            for(SwimTime st : controller.getSwimTimeList()){
                if(st.getMemberID() == s.getMemberID() && st.getEvent() == event && !st.getPlaceSet().equals("Træning")){
                    localSwimTimeList.add(st);
                   //System.out.println(s.getName() + " " + st.getEvent() + " " + st.getTime() + st.getPlaceSet());
                }
            }
        }
        Collections.sort(localSwimTimeList, new TimeComparator());
        for(int i = 0; i<=4; i++){ //gør at svømmeren navn bliver printet med. EN svømmer må kun dukke op én gang
            SwimTime topTimes = localSwimTimeList.get(i); // gør robust hvis der ikke er nok tider eller svømmere
            System.out.println(topTimes.getMemberID() + " " + topTimes.getEvent() + " " + topTimes.getTime() + " " + topTimes.getPlaceSet());
        }
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

