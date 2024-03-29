package org.example.model.service;

import org.example.entity.audit.Audit;
import org.example.model.repository.AuditRepository;
import org.example.model.repository.UserRepository;
import org.example.entity.user.User;

/**
 * Класс отвечающий за регистрацию пользователя.
 */
public final class UserRegistration {
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
        UserRepository.addUser(user);
        User userByLogin = UserRepository.getUserByLogin(login);
        Audit audit = new Audit("Зарегистрировался", userByLogin.getId());
        AuditRepository.addAudit(audit);
        return true;
    }
}
