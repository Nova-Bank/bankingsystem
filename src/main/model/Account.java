package accounts;
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

    public String getName() {
        return this.fullName;
    }

    public Date getDOB() {
        return this.DOB;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getUID() {
        return this.UID;
    }

    public String getPassword() {
        return this.password;
    }

}