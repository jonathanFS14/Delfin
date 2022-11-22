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


    public Swimmer(String name, String address, String phoneNumber, String mail, LocalDate birthday, int memberID, boolean isActive, boolean isCompetitor, boolean isStudent, boolean isMember ) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthday = birthday;
        creationDate = LocalDate.now();
        this.memberID = memberID;
        hasPaid = false;
        this.isActive = isActive;
        this.isCompetitor = isCompetitor;
        this.isStudent = isStudent;
        this.isMember = isMember;
    }

}
