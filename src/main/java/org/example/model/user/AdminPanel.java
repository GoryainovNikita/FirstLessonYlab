package org.example.model.user;

import org.example.model.db.Repository;

import java.util.List;

/**
 * Класс отвечающий за администрирования.
 */

public class AdminPanel {

    /**
     * Код для становления администратором.
     */
    private static final String CODEADMIN = "admin";
    private static Repository repo = new Repository();

    public static String getCode(){
        return CODEADMIN;
    }

    public static List<User> getUsers(){
        return repo.getUsers();
    }

    public static User getUserById(int id){
        return  repo.getUserById(id);
    }
}
