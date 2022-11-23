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
    private boolean isMember;
    private ArrayList<SwimTime> competitionResults = new ArrayList<>();
    private ArrayList<SwimTime> trainingResults = new ArrayList<>();


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
        isMember = true;
        competitionResults = new ArrayList<SwimTime>();
        trainingResults = new ArrayList<SwimTime>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public LocalDate getBirthday() {
        return birthday;
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

    public boolean isActive() {
        return isActive;
    }

    public boolean isCompetitor() {
        return isCompetitor;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public boolean isMember() {
        return isMember;
    }

    public ArrayList<SwimTime> getCompetitionResults() {
        return competitionResults;
    }

    public ArrayList<SwimTime> getTrainingResults() {
        return trainingResults;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCompetitor(boolean competitor) {
        isCompetitor = competitor;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public void setMember(boolean member) {
        isMember = member;
    }
}
