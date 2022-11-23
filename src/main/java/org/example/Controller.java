package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    Database database;
    Filehandler filehandler;

    public Controller(){
        database = new Database();
        filehandler = new Filehandler();
    }

    public void createSwimmer(String name, String address, String phoneNumber, String mail, LocalDate birthday, boolean isCompetitor, boolean isStudent){
        int memberID = database.getSwimmerList().size();

        Swimmer swimmer = new Swimmer(name, address, phoneNumber, mail, birthday, memberID, isCompetitor, isStudent);
        database.addSwimmerToDatabase(swimmer);
    }

    public ArrayList<Swimmer> searchForSwimmers(String searchParameter){
        return database.searchForSwimmers(searchParameter);
    }


}
