package org.example;

import java.util.Scanner;

public class KassererUserInterface {
    Scanner scanner;
    Controller controller;

    public KassererUserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void kassererUI() {
        initialLoad();
        //userMenu();
    }

    private void initialLoad() {
        controller.initialLoad();
    }
}
