package org.example.model.db;

import org.example.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для взаимодействия с базой данных.
 */
public final class UserRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/ylab?currentSchema=ylab";
    private static final String USER_NAME = "user";
    private static final String PASSWORD = "root";

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static DataBase dataBase;

    public UserRepository() {
        this.dataBase = DataBase.getDataBase();
    }

    public void addUser(User user){
        String addUser = "INSERT into users (firstName, lastName, login, password, role) VALUES (?, ?, ?, ?, ?,);";
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(addUser);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, 0);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }
    public User getUserByLogin(String login){
        return getUser("SELECT * from users where login = " + login);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
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

    public User getUserById(int id){
        return getUser( "SELECT * from users where id = " + id);
    }

    private User getUser(String str){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(str);
            while (resultSet.next()){
                int idUser = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String loginUser = resultSet.getString("login");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");
                User user = new User(idUser,firstName,lastName,loginUser,password);
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
