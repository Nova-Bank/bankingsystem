package main.model;
import java.util.Date;

public abstract class Account {

    private int UID;
    private String email;
    private String password;
    private String fullName;
    private Date DOB;
    private int phoneNumber;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String input) {
        this.email = input;
    }

    public String getName() {
        return this.fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public Date getDOB() {
        return this.DOB;
    }

    public void setDOB(Date input) {
        this.DOB = input;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(int input) {
        this.phoneNumber = input;
    }

    public int getUID() {
        return this.UID;
    }

    public void setUID(int input) {
        this.UID = input;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String input) {
        this.password = input;
    }

    public abstract boolean login(String str, String pass);

}
