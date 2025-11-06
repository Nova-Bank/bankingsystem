package main.model;

import java.util.Random;

public class ChildAccount extends Account {

    private AdultAccount adult;

    public ChildAccount(AdultAccount inputAdult) {
        this.adult = inputAdult;
        System.out.println("Added child to adult: " + adult.getName());
        Random r = new Random();
        int tmpUID = r.nextInt(100000);
        super.setUID(tmpUID);
    } 

    @Override
    public boolean login(String str, String pass) {
        return true;
    }
}
