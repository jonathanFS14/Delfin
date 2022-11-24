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
        Integer.parseInt(String.format("%03d", memberID));

        Swimmer swimmer = new Swimmer(name, address, phoneNumber, mail, birthday, memberID, isCompetitor, isStudent);
        database.addSwimmerToDatabase(swimmer);
    }

    public ArrayList<Swimmer> searchForSwimmers(String searchParameter){
        return database.searchForMembers(searchParameter);
    }

    public ArrayList<Swimmer> getSwimmerList(){
            return database.getSwimmerList();
    }

    public void overwriteSwimmerDatabase(){
        filehandler.overwriteSwimmerDatabase(database.getSwimmerList());
    }

    public void initialLoad() {
        database.setSwimmerDatabase(filehandler.retrieveSwimmerDatabase());
    }
}
