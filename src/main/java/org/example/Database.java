package org.example;

import java.util.ArrayList;

public class Database {
    private ArrayList<Swimmer> swimmerList;
    private ArrayList<Swimmer> swimmerSearchList;
    
    public Database() {
        swimmerList = new ArrayList<>();
        swimmerSearchList = new ArrayList<>();
    }
    
    public void addSwimmerToDatabase(Swimmer swimmer) {
        swimmerList.add(swimmer);
    }
    
    //Kan søge både med medlemmets navn og deres medlemsnummer
    public ArrayList<Swimmer> searchForMembers(String searchParameter) {
        
        swimmerSearchList.clear();
        
        for (Swimmer swimmer : swimmerList) {
            if (!swimmer.isArchived()) {
                if (swimmer.getName().toLowerCase().contains(searchParameter.toLowerCase())) {
                    swimmerSearchList.add(swimmer);
                } else if (String.valueOf(swimmer.getMemberID()).equals(searchParameter)) {
                    swimmerSearchList.add(swimmer);
                }
            }
        }
        return swimmerSearchList;
    }
    
    public ArrayList<Swimmer> searchForArchived(String searchParameter) {
        
        swimmerSearchList.clear();
        
        for (Swimmer swimmer : swimmerList) {
            if (swimmer.isArchived()) {
                if (swimmer.getName().toLowerCase().contains(searchParameter.toLowerCase())) {
                    swimmerSearchList.add(swimmer);
                } else if (String.valueOf(swimmer.getMemberID()).equals(searchParameter)) {
                    swimmerSearchList.add(swimmer);
                }
            }
        }
        return swimmerSearchList;
    }
    
    public ArrayList<Swimmer> getSwimmerList() {
        return swimmerList;
    }
    
    public void setSwimmerDatabase(ArrayList<Swimmer> swimmerList) {
        this.swimmerList = swimmerList;
    }
    
}
