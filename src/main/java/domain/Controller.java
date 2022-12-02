package domain;
import datasource.Filehandler;
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
        database.createSwimmer(name, address, phoneNumber, mail, birthday, isCompetitor, isStudent);
        overwriteSwimmerDatabase();
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
    public void overwriteSwimTimeDatabase(){
        filehandler.overwriteSwimTimeDatabase(database.getSwimTimeList());
    }
    public void initialLoad() {
        database.setSwimmerDatabase(filehandler.retrieveSwimmerDatabase());
        database.setSwimTimeDatabase(filehandler.retrieveSwimTimeDatabase());
    }

    public void setCompetitorsToTeams(){
        database.setCompetitorsToTeams();
    }

    public ArrayList<Swimmer>getSeniorTeam(){
        return database.getSeniorTeam();
    }
    public ArrayList<Swimmer>getJuniorTeam(){
        return database.getJuniorTeam();
    }

    public Swimmer searchForMember (String searchName){
        return database.searchForMember(searchName);
    }

    public Events selectEvent (){
      return database.selectEvent();
    }

    public void createSwimTime(int memberID, double time, Events event, String placeSet){
        database.createSwimTime(memberID,time,event,placeSet);
        overwriteSwimTimeDatabase();
    }

    
}
