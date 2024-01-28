package org.example.model.service;

import org.example.model.db.Repository;
import org.example.model.user.User;

/**
 * Класс отвечающий за регистрацию пользователя.
 */
public final class Registration {

    private static Repository repo = new Repository();

    /**
     * Метод проверяющий существует ли такой пользователь, если нет то регистрирует его.
     * @param firstName
     * @param lastName
     * @param login
     * @param password
     * @return
     */

    public static boolean registration(String firstName, String lastName,String login, String password) {
        if (Validation.validation(login)) {
            return false;
        }
        User user = new User(firstName, lastName, login, password);
        repo.addUser(user);
        user.getAudit().add("Регистрация пользователя");
        return true;
    }
}
