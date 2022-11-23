package org.example;

import java.util.ArrayList;

public class Database {

    ArrayList<Swimmer> swimmerList = new ArrayList<>();

    public void addSwimmerToDatabase(Swimmer swimmer){
     swimmerList.add(swimmer);
    }

}
