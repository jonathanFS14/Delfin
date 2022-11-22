package org.example;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void startProgram(){
    //TODO basal UI så man kan teste
        System.out.println("Velkommen til systemet");
        System.out.println("Midlertidlig UI, vi gør det pænere senere x(");
        System.out.println("Hvad vil du gøre?");
        System.out.println("""
                1. Opret ny svømmer
                """);
        int valg = scanner.nextInt();
        if(valg == 1){
            controller.createSwimmer();
        }
    }

}
