package org.example.model.db;

import org.example.entity.user.User;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс для взаимодействия с базой данных.
 */
public final class Repository {

    private static DataBase dataBase;

    public Repository() {
        this.dataBase = DataBase.getDataBase();
    }

    public void addUser(User user){
        dataBase.getUsers().add(user);
    }

    public User getUserByLogin(String login){
        User user = new User(login);
        int i = dataBase.getUsers().indexOf(user);
        if(i<0){
            throw new NoSuchElementException("Пользователь не найден");
        }
        return dataBase.getUsers().get(i);
    }

    public List<User> getUsers(){
        return dataBase.getUsers();
    }

    public User getUserById(int id){
        return dataBase.getUsers().get(id - 1);
    }
}
