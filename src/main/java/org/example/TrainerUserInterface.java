package org.example;

import java.util.Scanner;

public class TrainerUserInterface {
    Scanner scanner;
    Controller controller;

    public TrainerUserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void trainerUI() {
        initialLoad();
        //userMenu();
    }

    private void initialLoad() {
        controller.initialLoad();
    }
}
