package org.example.entity.audit;

import org.example.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class Audit {

    private int id;
    private String audit;
    private int userId;

    public Audit(String audit, int userId) {
        this.audit = audit;
        this.userId = userId;
    }

    public String getAudit() {
        return audit;
    }

    public int getUserId() {
        return userId;
    }
}
