package userinterface;

import domain.Controller;

import java.util.Scanner;

public class MainUI {

    Scanner scanner;
    Controller controller;

    public MainUI() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void login() {
        int input;
        do {
            System.out.println("""
                    Hvem logger du ind som?
                    1. Formand
                    2. Kasserer
                    3. Træner
                    9. Luk programmet""");
            input = scanner.nextInt();
            switch (input) {
                case 1 -> controller.formandUI();
                case 2 -> controller.kassererUI();
                case 3 -> controller.trainerUI();
                case 9 -> endProgram();
                default -> System.out.println("Ugyldigt Input");
            }
        }
        while (input != 1 && input != 2 && input != 3 && input != 9);
    }

    public void endProgram() {
        int input;
        System.out.println("""
                Er du sikker på du vil forlade programmet?
                1. ja
                2. nej""");
        do {
            input = readInt();
            switch (input) {
                case 1:
                    System.out.println("På gensyn");
                    System.exit(0);
                    break;
                case 2:
                    System.out.println("Vender tilbage");
                    login();
                    break;
                default:
                    System.out.println("Ugyldig input");
                    break;
            }
        }
        while (input != 1 && input != 2);
    }

    protected int readInt() {
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
    protected String readString() {
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

/*     public void returnToMainUI() {
          int input;
          System.out.println("""
                  Vil du vende tilbage til login skærmen?
                  1. Ja
                  2. Nej""");
          input = scanner.nextInt();
          switch (input){
              case 1 -> mainUI.login();
              case 2 -> userMenu();
          }
    }*/
}
