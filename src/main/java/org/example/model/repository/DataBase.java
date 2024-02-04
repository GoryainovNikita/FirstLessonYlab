package org.example.model.db;

import org.example.entity.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс имитирующий работу БД. Содержит лист всех пользователей.
 */
public class DataBase {

    private static volatile DataBase dataBase;

    public static DataBase getDataBase(){
        if(dataBase == null) {
            synchronized (DataBase.class) {
                if (dataBase == null) {
                    dataBase = new DataBase();
                }
            }
        }
        return dataBase;
    }
    private DataBase() {
    }

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }
}
