package main.model;

import java.util.Random;

public class AdultAccount extends Account {
    
    public AdultAccount() {
        //TODO: create adult account builder pattern
        Random r = new Random();
        int tmpUID = r.nextInt(100000);
        super.setUID(tmpUID);
    }

    @Override
    public boolean login(String str, String pass) {
        return true;
    }

}
