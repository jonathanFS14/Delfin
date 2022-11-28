package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    
    Database database;
    Filehandler filehandler;
    
    public Controller() {
        database = new Database();
        filehandler = new Filehandler();
    }
    
    public void createSwimmer(String name, String address, String phoneNumber, String mail, LocalDate birthday, boolean isCompetitor, boolean isStudent) {
        int memberID = database.getSwimmerList().size();
        Swimmer swimmer = new Swimmer(name, address, phoneNumber, mail, birthday, memberID, isCompetitor, isStudent);

        database.addSwimmerToDatabase(swimmer);
    }
    
    public ArrayList<Swimmer> searchForMembers(String searchParameter) {
        return database.searchForMembers(searchParameter);
    }
    
    public ArrayList<Swimmer> searchForArchived(String searchParameter) {
        return database.searchForArchived(searchParameter);
    }
    
    public ArrayList<Swimmer> getSwimmerList() {
        return database.getSwimmerList();
    }
    
    public void overwriteSwimmerDatabase() {
        filehandler.overwriteSwimmerDatabase(database.getSwimmerList());
    }
    
    public void initialLoad() {
        database.setSwimmerDatabase(filehandler.retrieveSwimmerDatabase());
    }
    
}
