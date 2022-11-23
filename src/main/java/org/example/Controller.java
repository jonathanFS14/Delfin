package org.example;

import java.util.ArrayList;

public class Controller {

    Database database;
    Filehandler filehandler;

    public Controller(){
         database = new Database();
        filehandler = new Filehandler();
    }

    public Swimmer createSwimmer(){
        Swimmer swimmer = database.createSwimmer();
        return swimmer;
    }

    public ArrayList<Swimmer>searchForSwimmers(String searchParameter){
        return database.searchForSwimmers(searchParameter);
    }


}
