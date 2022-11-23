package org.example;

import java.util.ArrayList;

public class Database {
    private ArrayList<Swimmer> swimmerList = new ArrayList<Swimmer>();
    private ArrayList<Swimmer> swimmerSearchList = new ArrayList<>();

    public void addSwimmerToDatabase(Swimmer swimmer){
     swimmerList.add(swimmer);
    }

 //Kan søge både med medlemmets navn og deres medlemsnummer
 public ArrayList<Swimmer> searchForSwimmers(String searchParameter){

     swimmerSearchList.clear();

     for(Swimmer swimmer : swimmerSearchList){
         if(swimmer.getName().toLowerCase().contains(searchParameter.toLowerCase())){
             swimmerSearchList.add(swimmer);
         }
         else if (String.valueOf(swimmer.getMemberID()).equals(searchParameter)){
             swimmerSearchList.add(swimmer);
         }
     }

     return swimmerSearchList;
 }

}
