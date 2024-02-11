package org.example.model.service;

import org.example.aop.annotations.Loggable;
import org.example.model.repository.UserRepository;

import java.util.NoSuchElementException;

/**
 * Класс отвечающий за валидацию пользователя. Проверяет существует ли такой пользователь вообще
 */

public class UserValidation {


    @Loggable
    public static boolean validation(String login){
        if(UserRepository.getUserByLogin(login) == null){
            return false;
        }
        return true;
    }
}
