package org.example;

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

}
