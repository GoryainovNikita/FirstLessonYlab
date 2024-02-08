package org.example.model.repository;

import org.example.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс для взаимодействия с базой данных.
 */
public final class UserRepository {

    public static void addUser(User user){
        String addUser = "INSERT into users (first_name, last_name, login, password, role) VALUES (?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionDB.getConnection();) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(addUser);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, 0);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static User getUserByLogin(String login) throws NoSuchElementException{
            User user = getUser("SELECT * from users where login = " + "\'" + login + "\'");

        return user;
    }

    public static List<User> getUsers(){
        List<User> users = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String loginUser = resultSet.getString("login");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");
                User user = new User(id,firstName,lastName,loginUser,password);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static User getUserById(int id){
        return getUser( "SELECT * from users where id = " + id);
    }

    private static User getUser(String str){

        try(Connection connection = ConnectionDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(str);
            while (resultSet.next()){
                int idUser = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String loginUser = resultSet.getString("login");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");
                User user = new User(idUser,firstName,lastName,loginUser,password);
                connection.close();
                return user;
            }
        } catch (SQLException e) {
            throw new NoSuchElementException();
        }
        return null;
    }
}
