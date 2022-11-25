package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Swimmer {
    
    private String name;
    private String address;
    private String phoneNumber;
    private String mail;
    private LocalDate birthday;
    private LocalDate creationDate;
    private int memberID;
    private boolean hasPaid;
    private boolean isActive;
    private boolean isCompetitor;
    private boolean isStudent;
    private boolean isArchived;
    private ArrayList<SwimTime> competitionResults;
    private ArrayList<SwimTime> trainingResults;
    
    
    public Swimmer(String name, String address, String phoneNumber, String mail, LocalDate birthday, int memberID, boolean isCompetitor, boolean isStudent) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthday = birthday;
        creationDate = LocalDate.now();
        this.memberID = memberID;
        hasPaid = false;
        isActive = true;
        this.isCompetitor = isCompetitor;
        this.isStudent = isStudent;
        isArchived = false;
        competitionResults = new ArrayList<SwimTime>();
        trainingResults = new ArrayList<SwimTime>();
    }
    
    //Denne constructor bruges når der skal læses fra filen i filehandler så alle attributer kommer med.
    public Swimmer(String name, String address, String phoneNumber, String mail, LocalDate birthday, LocalDate creationDate, int memberID, boolean hasPaid, boolean isActive, boolean isCompetitor, boolean isStudent, boolean isArchived) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthday = birthday;
        this.creationDate = creationDate;
        this.memberID = memberID;
        this.hasPaid = hasPaid;
        this.isActive = isActive;
        this.isCompetitor = isCompetitor;
        this.isStudent = isStudent;
        this.isArchived = isArchived;
        competitionResults = new ArrayList<SwimTime>();
        trainingResults = new ArrayList<SwimTime>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }
    
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    public LocalDate getCreationDate() {
        return creationDate;
    }
    
    public int getMemberID() {
        return memberID;
    }
    
    public boolean isHasPaid() {
        return hasPaid;
    }
    
    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public boolean isCompetitor() {
        return isCompetitor;
    }
    
    public void setCompetitor(boolean competitor) {
        isCompetitor = competitor;
    }
    
    public boolean isStudent() {
        return isStudent;
    }
    
    public void setStudent(boolean student) {
        isStudent = student;
    }
    
    public boolean isArchived() {
        return isArchived;
    }
    
    public void setArchived(boolean archived) {
        isArchived = archived;
    }
    
    public ArrayList<SwimTime> getCompetitionResults() {
        return competitionResults;
    }
    
    public ArrayList<SwimTime> getTrainingResults() {
        return trainingResults;
    }
    
    
}
