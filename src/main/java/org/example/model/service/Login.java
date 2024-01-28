package org.example.model.service;

import org.example.model.db.Repository;
import org.example.model.user.User;

/**
 * Класс отвечающий за логику входа в программу. Проверяет подходит ли пароль
 */

public class Login {
    private static Repository repo = new Repository();

    /**
     * Проверяет валидацию, и пускает либо не пускает пользователя
     * @param login
     * @param password
     * @return
     */
    public static User login(String login, String password) {
        if (Validation.validation(login)) {
            User user = repo.getUserByLogin(login);
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
