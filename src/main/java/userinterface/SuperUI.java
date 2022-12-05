package userinterface;

import domain.Controller;

import java.util.Scanner;

public class SuperUI {
   protected Scanner scanner = new Scanner(System.in);
    static Controller controller = new Controller();


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
                case "nej", "no", "n" ->{
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

    public void endProgram() {
        boolean input;
        System.out.println("""
                Er du sikker på du vil forlade programmet? Ja/Nej""");
        do {
            input = yesOrNoToBoolean();
            if (input == input) {
                System.out.println("På gensyn");
                System.exit(0);
            } else if (input != input ) {
                System.out.println("Vender tilbage");
            } else {
                System.out.println("Ugyldig input");
            }
        }
        while (!input && input);
    }

    protected void logOut(){
        System.out.println("Logger ud.");
    }
    protected void insertSeperatorLine(int numberOfLines) {
        for(int i = 0; i < numberOfLines; ++i) {
            System.out.print("━");
        }
        System.out.println("");
    }

}
