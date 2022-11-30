package domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Database {
    private ArrayList<Swimmer> swimmerList;
    private ArrayList<Swimmer> swimmerSearchList;
    private ArrayList<Swimmer> seniorTeam;
    private ArrayList<Swimmer> juniorTeam;
    private ArrayList<SwimTime> swimTimeList;


    public Database() {
        swimmerList = new ArrayList<>();
        swimmerSearchList = new ArrayList<>();
        seniorTeam = new ArrayList<>();
        juniorTeam = new ArrayList<>();
        swimTimeList = new ArrayList<>();
    }
    
    public void addSwimmerToDatabase(Swimmer swimmer) {
        swimmerList.add(swimmer);
    }
    public void createSwimmer(String name, String address, String phoneNumber, String mail, LocalDate birthday, boolean isCompetitor, boolean isStudent){
        int memberID = getSwimmerList().size();
        Swimmer swimmer = new Swimmer(name, address, phoneNumber, mail, birthday, memberID, isCompetitor, isStudent);
        addSwimmerToDatabase(swimmer);
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

    public void setSwimTimeDatabase(ArrayList<SwimTime>swimTimeList){
        this.swimTimeList = swimTimeList;
    }
    private ArrayList<Swimmer> getCompetitorList(){
        ArrayList<Swimmer> competitorList = new ArrayList<>();
        for(Swimmer swimmer : swimmerList){
            if(swimmer.isCompetitor()){
                competitorList.add(swimmer);
            }
        }
        return competitorList;
    }

    public void setCompetitorsToTeams(){
        ArrayList<Swimmer>competitorList = getCompetitorList();
        for(Swimmer swimmer : competitorList){
            long years = ChronoUnit.YEARS.between(swimmer.getBirthday(),LocalDate.now());
            if (years >= 18){
               seniorTeam.add(swimmer);
            }
            else {
                juniorTeam.add(swimmer);
            }
        }
    }



    public ArrayList<Swimmer> getJuniorTeam() {
        return juniorTeam;
    }
    public ArrayList<Swimmer>getSeniorTeam(){
    return seniorTeam;
    }
    public ArrayList<SwimTime>getSwimTimeList(){
        return swimTimeList;
    }
}
