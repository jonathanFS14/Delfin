package userinterface;
import domain.Controller;
import domain.Events;
import domain.SwimTime;
import domain.Swimmer;

import java.util.ArrayList;
import java.util.Scanner;
public class TrainerUserInterface extends SuperUI{


    public void trainerUI() {
        controller.initialLoad();
        controller.setCompetitorsToTeams();
        userMenu();
    }

    public void userMenu(){
        int userchoice;
        do {
            System.out.println("""                
                     Hvad vil du gøre?                       
                    1. Se hold
                    2. indtast tid
                    3. se alle tider for en svømmer
                    8. Log ud
                    9. Afslut program
                     """);
            userchoice = readInt();
            switch (userchoice) {
                case 1 -> showTeamMenu();
                case 2 -> setTimeForSwimmer();
                case 3 -> System.out.println("");
                case 8 -> logOut();
                case 9 -> endProgram();
                default -> System.out.println("Ugyldig input");
            }
        }
        while (userchoice!=9);
    }

    public void showTeamMenu(){
        int userChoice;
        do {
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
        while(userChoice!=1 || userChoice!=2 || userChoice!=3);
    }

    public void setTimeForSwimmer(){
        System.out.println("Indtast hvilken svømmer du vil registrere en tid for");
        String searchParameter = readString();
        Swimmer swimmer = controller.searchForMember(searchParameter);

        int memberID = swimmer.getMemberID();

        Events event = controller.selectEvent();

        System.out.println("Indtast tid");
        double swimTime = scanner.nextDouble();

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
        if(yesOrNoToBoolean())
        controller.createSwimTime(memberID,swimTime,event,placeSet);
    }


    private void printTeam(ArrayList<Swimmer> teamList){
        for(Swimmer swimmer : teamList){
            System.out.println(teamList.indexOf(swimmer) + 1 + ". " + swimmer.getName());
        }
    }

    //TODO gør metoden færdig
    public void logOut(){
           System.out.println("""
                   Do you want to logOut?
                   ja/nej
                   """);
               boolean input = yesOrNoToBoolean();

               if(input){
                   controller.overwriteSwimTimeDatabase();
                   MainUI mainUI = new MainUI();
                   mainUI.login();
               }

       }

    }

