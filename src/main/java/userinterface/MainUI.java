package userinterface;

public class MainUI extends SuperUI {

    FormandUserInterface formandUserInterface;
    KassererUserInterface kassererUserInterface;
    TrainerUserInterface trainerUserInterface;

    public MainUI() {
        formandUserInterface = new FormandUserInterface();
        kassererUserInterface = new KassererUserInterface();
        trainerUserInterface = new TrainerUserInterface();
    }

    public void login() {
        controller.initialLoad();
        int input;
        do {
            System.out.println("""
                    Hvem logger du ind som?
                    1. Formand
                    2. Kasserer
                    3. Træner
                    8. Print all members
                    9. Luk programmet""");
            input = scanner.nextInt();
            switch (input) {
                case 1 -> formandUserInterface.formandUI();
                case 2 -> kassererUserInterface.kassererUI();
                case 3 -> trainerUserInterface.trainerUI();
                case 8 -> {
                    insertSeperatorLine(215);
                    controller.printAllMembers();
                    insertSeperatorLine(215);
                }
                case 9 -> endProgram();
                default -> System.out.println("Ugyldigt Input");
            }
        }
        while (input != 1 || input != 2 || input != 3 || input != 9);
    }
}
