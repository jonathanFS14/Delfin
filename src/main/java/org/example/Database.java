package org.example;

import java.util.ArrayList;

public class Database {

    ArrayList<Swimmer> swimmerList = new ArrayList<Swimmer>();
    ArrayList<Swimmer> swimmerSearchList = new ArrayList<>();

    //TODO createSwimmer()

 public Swimmer createSwimmer(){
     Swimmer newSwimmer = new Swimmer();
     return newSwimmer;
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
