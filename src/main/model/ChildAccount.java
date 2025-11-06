package main.model;

public class ChildAccount extends Account {

    public ChildAccount() {
        //TODO: create child account builder pattern 
    } 
    
    @Override
    public boolean login(String str, String pass) {
        return true;
    }
}
