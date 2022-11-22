package org.example;

public class Controller {

    Database database;
    Filehandler filehandler;

    public Controller(){
         database = new Database();
        filehandler = new Filehandler();
    }


}
