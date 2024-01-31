package org.example.entity.audit;

import org.example.entity.user.User;
import org.example.model.db.DataBase;

import java.util.ArrayList;
import java.util.List;

public class Audit {
    private static volatile Audit audit;

    public static Audit getAudit(){
        if(audit == null) {
            synchronized (Audit.class) {
                if (audit == null) {
                    audit = new Audit();
                }
            }
        }
        return audit;
    }
    private Audit() {
    }

    private List<String> actions = new ArrayList<>();

    public void addAction(User user, String string){
        actions.add("Пользователь " + user.getFirstName() + " " + user.getLastName() + " " + string);
    }

    public List<String> getActions(){
        return new ArrayList<>(actions);
    }
}
