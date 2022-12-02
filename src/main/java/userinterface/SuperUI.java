package userinterface;

import domain.Controller;

import java.util.Scanner;

public class SuperUI {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();


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

    protected boolean yesOrNoToBoolean() {
        boolean answer = false;
        int check; //Er der en bedre måde at loop løkken?
        String yesOrNo;
        do {
            yesOrNo = scanner.nextLine();
            switch (yesOrNo.toLowerCase()) {
                case "ja", "yes", "j", "y" -> {
                    answer = true;
                    check = 1;
                }
                case "nej", "no", "n" -> {
                    answer = false;
                    check = 1;
                }
                default -> {
                    System.out.println("Ugyldig input");
                    check = 0;
                }
            }
        }
        while (check == 0);
        return answer;
    }

    protected String booleanToYesOrNo(Boolean bool) {
        String answer = null;
        if (bool)
            answer = "Ja";
        else if (!bool)
            answer = "Nej";
        else
            System.out.println("Forkert input.");
        // TODO burde køre i loop sådan at man skal prøve igen hvis input er forkert.
        return answer;
    }


    //TODO lav en ordentlig endProgram() og logOut()


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
                    break;
                default:
                    System.out.println("Ugyldig input");
                    break;
            }
        }
        while (input != 1 && input != 2);
    }

    public void logOut() {
        System.out.println("Logger ud.");
    }

}
