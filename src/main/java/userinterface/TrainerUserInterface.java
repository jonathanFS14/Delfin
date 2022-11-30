package userinterface;
import domain.Controller;
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
                    2. Vend tilbage til hoved menu
                     """);
            userchoice = readInt();
            switch (userchoice) {
                case 1 -> showTeamMenu();
                case 2 -> System.out.println("");
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

    private void printTeam(ArrayList<Swimmer> teamList){
        for(Swimmer swimmer : teamList){
            System.out.println(teamList.indexOf(swimmer) + 1 + ". " + swimmer.getName());
        }
    }
}
