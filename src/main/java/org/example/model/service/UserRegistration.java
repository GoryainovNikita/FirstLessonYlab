package org.example.model.service;

import org.example.entity.audit.Audit;
import org.example.model.db.Repository;
import org.example.entity.user.User;

/**
 * Класс отвечающий за регистрацию пользователя.
 */
public final class UserRegistration {

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
        if (UserValidation.validation(login)) {
            return false;
        }
        User user = new User(firstName, lastName, login, password);
        repo.addUser(user);
        Audit.getAudit().addAction(user, "зарегистрировался");
        return true;
    }
}
