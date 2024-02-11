package org.example.model.service;

import org.example.aop.annotations.Loggable;
import org.example.model.repository.UserRepository;
import org.example.entity.user.User;

/**
 * Класс отвечающий за логику входа в программу. Проверяет подходит ли пароль
 */

public class UserLogin {
    /**
     * Проверяет валидацию, и пускает либо не пускает пользователя
     * @param login
     * @param password
     * @return
     */

    @Loggable
    public static User login(String login, String password) {
        if (UserValidation.validation(login)) {
            User user = UserRepository.getUserByLogin(login);
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
