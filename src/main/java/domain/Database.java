package domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Database {
    private ArrayList<Swimmer> swimmerList;
    private ArrayList<Swimmer> swimmerSearchList;
    private ArrayList<Swimmer> seniorTeam;
    private ArrayList<Swimmer> juniorTeam;
    private ArrayList<SwimTime> swimTimeList;
    private ArrayList<Events>eventsList;
    Scanner scanner;


    public Database() {
        swimmerList = new ArrayList<>();
        swimmerSearchList = new ArrayList<>();
        seniorTeam = new ArrayList<>();
        juniorTeam = new ArrayList<>();
        swimTimeList = new ArrayList<>();
        eventsList = new ArrayList<>(Arrays.asList(Events.BUTTERFLY50M, Events.BUTTERFLY100M, Events.CRAWL50M, Events.CRAWL100M,Events.RYGCRAWL50M, Events.RYGCRAWL100M,Events.BRYST50M,Events.BRYST100M,Events.MEDLEY100M));
        scanner = new Scanner(System.in);
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
    public Swimmer searchForMember(String searchParameter) {
        swimmerSearchList.clear();
        Swimmer swimmer = null;

        for (Swimmer s : swimmerList) {
            if (!s.isArchived())
                if(s.getName().toLowerCase().contains(searchParameter.toLowerCase()) || String.valueOf(s.getMemberID()).equals(searchParameter))
                    swimmerSearchList.add(s);
            }

        if (swimmerSearchList.size() > 1) {
            System.out.println("Vælg hvem du vil tjekke ");
            for (Swimmer s : swimmerSearchList) {
                System.out.println(swimmerSearchList.indexOf(s) + 1 + ". " + s.getName());
            }
            int chooseSwimmer = scanner.nextInt();
            swimmer = swimmerSearchList.get(chooseSwimmer - 1);
        }
        else if(swimmerSearchList.size() == 1) {
            swimmer = swimmerSearchList.get(0);
            System.out.println("Du søgte " + swimmer.getName() + " frem");
        }
        return swimmer;
    }

 /*   //TODO merge searchformembers med searchformember metoderne
    public Swimmer searchForMember(String searchParameter) {

        Swimmer swimmer = null;

        if (!swimmerList .isEmpty()) {
            System.out.println("Vælg hvem du vil tjekke ");
            for (Swimmer s : swimmerList) {
                System.out.println(swimmerList.indexOf(s) + 1 + ". " + s.getName());
            }
            int chooseSwimmer = scanner.nextInt();
            swimmer = swimmerList.get(chooseSwimmer - 1);
        }

        return swimmer;
    }
    */
    
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
