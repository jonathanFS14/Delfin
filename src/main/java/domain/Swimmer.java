package domain;

import java.time.LocalDate;

public class Swimmer {
    
    private String name;
    private String address;
    private String phoneNumber;
    private String mail;
    private LocalDate birthday;
    private final LocalDate creationDate;
    private final int memberID;
    private boolean hasPaid;
    private boolean isActive;
    private boolean isCompetitor;
    private boolean isStudent;
    private boolean isArchived;
    private LocalDate paymentDate;





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
        paymentDate = LocalDate.parse("2000-01-01");

    }
    
    //Denne constructor bruges når der skal læses fra filen i filehandler så alle attributer kommer med.
    public Swimmer(String name, String address, String phoneNumber, String mail, LocalDate birthday, LocalDate creationDate, int memberID, boolean hasPaid, boolean isActive, boolean isCompetitor, boolean isStudent, boolean isArchived, LocalDate paymentDate) {
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
        this.paymentDate = paymentDate;

    }

    public LocalDate getPaymentDate() {
        return paymentDate;
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

    
    public LocalDate getCreationDate() {
        return creationDate;
    }
    
    public int getMemberID() {
        return memberID;
    }
    
    public boolean getHasPaid() {
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

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
}
