package org.example.model.service;

import org.example.model.db.Repository;

import java.util.NoSuchElementException;

/**
 * Класс отвечающий за валидацию пользователя. Проверяет существует ли такой пользователь вообще
 */

public class Validation {

    private static Repository repo = new Repository();

    public static boolean validation(String login){
        try{
            repo.getUserByLogin(login);
        }
        catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}
