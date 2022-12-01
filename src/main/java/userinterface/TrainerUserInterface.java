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
                    8. Log ud
                    9. Afslut program
                     """);
            userchoice = readInt();
            switch (userchoice) {
                case 1 -> showTeamMenu();

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

    /*
    Træner søger og vælger medlem
    træner vælger om det er stævnetid eller træningstid
    hvis det er stævnetid skal han skrive tid, stævne og disciplin
    hvis det er træningstid skal han kun skrive tid og disciplin
    gemmes i en seperat csv fil
    når der læses fra csv filen kobles den til svømmeren via memberID
     */

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

