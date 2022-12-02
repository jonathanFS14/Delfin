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

    public ArrayList<SwimTime> getSwimTimeList(){
        return database.getSwimTimeList();
    }

    public void printAllMembers() {
        String a, b, c, d, e, f, g, h, i, j, k, l;
        System.out.println(String.format("┃ %-4s │  %-20s │  %-20s │  %-20s │  %-25s │  %-15s │  %-10s │  %-10s │  %-20s │  %-10s │  %-10s │  %-15s │ ", "ID", "Navn", "Addresse", "Tlf", "Mail", "Fødselsdag", "Betalt", "Aktiv", "Konkurrencesvømmer", "Studerende", "Arkiveret", "Betalingsdato"));
        for (Swimmer swimmer : getSwimmerList()) {
            a = String.valueOf(swimmer.getMemberID());
            b = swimmer.getName();
            c = swimmer.getAddress();
            d = swimmer.getPhoneNumber();
            e = swimmer.getMail();
            f = swimmer.getBirthday().toString();
            g = booleanToYesOrNo(swimmer.getHasPaid());
            h = booleanToYesOrNo(swimmer.isActive());
            i = booleanToYesOrNo(swimmer.isCompetitor());
            j = booleanToYesOrNo(swimmer.isStudent());
            k = booleanToYesOrNo(swimmer.isArchived());
            l = swimmer.getPaymentDate().toString();

            String s = String.format("┃ %-4s │  %-20s │  %-20s │  %-20s │  %-25s │  %-15s │  %-10s │  %-10s │  %-20s │  %-10s │  %-10s │  %-15s │ ", a, b, c, d, e, f, g, h, i, j, k, l);
            System.out.println(s);

        }
    }

    private String booleanToYesOrNo(boolean bool) {
        String answer = null;
        if (bool)
            answer = "Ja";
        else if (!bool)
            answer = "Nej";
        else
            System.out.println("Forkert input.");
        return answer;
    }
    
}
