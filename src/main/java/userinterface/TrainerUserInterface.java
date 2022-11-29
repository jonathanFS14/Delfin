package userinterface;
import domain.Controller;

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
    }

    public void sehold(){
        System.out.println("""
                hvilket hold vil du se?
                1.
                """);
    }
}
