package userinterface;
import domain.Controller;
import domain.Swimmer;

import java.util.ArrayList;
import java.util.Scanner;
public class TrainerUserInterface {
    Scanner scanner;
    Controller controller;

    public TrainerUserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void trainerUI() {
        controller.initialLoad();
        controller.setCompetitorsToTeams();
        userMenu();
    }

    public void userMenu(){
        System.out.println("""                
                    Hvad vil du gøre?                       
                   1. Se hold
                   2. 
                    """);
        int userchoice = readInt();
        switch (userchoice) {
            case 1 -> showTeamMenu();
        }

    }

    public void showTeamMenu(){
        System.out.println("""
                hvilket hold vil du se?
                1. Senior holdet
                2. Junior holdet
                3. Vend tilbage
                """);
        int userChoice = readInt();
        switch (userChoice) {
            case 1 -> printTeam(controller.getSeniorTeam());
            case 2 -> printTeam(controller.getJuniorTeam());
            case 3 -> userMenu();
            default -> System.out.println("Ugyldig input");
        }
    }

    private void printTeam(ArrayList<Swimmer> teamList){
        int i = 0;
        for(Swimmer swimmer : teamList){
            System.out.println(++i + ". " + swimmer.getName());
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

    //Sørger for at input ikke er tomt. Strukturen i readInt virker ikke for string af en eller anden grund.
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
