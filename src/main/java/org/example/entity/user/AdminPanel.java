package org.example.entity.user;

import org.example.model.repository.UserRepository;

import java.util.List;

/**
 * Класс отвечающий за администрирования.
 */

public class AdminPanel {

    /**
     * Код для становления администратором.
     */
    private static final String CODEADMIN = "admin";

    public static String getCode(){
        return CODEADMIN;
    }

    public static List<User> getUsers(){
        return UserRepository.getUsers();
    }

    public static User getUserById(int id){
        return  UserRepository.getUserById(id);
    }
}
