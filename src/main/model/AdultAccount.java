package main.model;

import java.util.ArrayList;
import java.util.Random;

public class AdultAccount extends Account {
    
    //Adult has max 4 children under his name
    private final int MAX_CHILDREN = 4;
    private ArrayList<ChildAccount> children = new ArrayList<>();

    public AdultAccount() {
        Random r = new Random();
        int tmpUID = r.nextInt(100000);
        super.setUID(tmpUID);
    }

    public void addChild(ChildAccount child) throws Exception {

        if (children.size() < MAX_CHILDREN) {
            children.add(child);

            for (int i=0;i<children.size();i++) {
                System.out.println(children.get(i).getName());
            };
        } else {
            throw new Exception("Failed to add child.  Max children reached");
        }
    }

    @Override
    public boolean login(String str, String pass) {
        return true;
    }

}
