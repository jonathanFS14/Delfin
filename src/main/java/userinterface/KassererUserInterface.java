package userinterface;

import domain.Controller;

import java.util.Scanner;

public class KassererUserInterface extends MainUI {

    public void kassererUI() {
        initialLoad();
        //userMenu();
    }

    private void initialLoad() {
        controller.initialLoad();
    }
}
