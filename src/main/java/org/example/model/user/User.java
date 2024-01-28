package org.example.model.user;

import org.example.model.meter.MeterWater;
import org.example.model.meter.UserMeter;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс отвечающий за пользователя
 */

public class User {

    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private String login;

    private UserMeter userMeter;

    private List<String> audit;


    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = Role.USER;
        this.login = login;
        userMeter = new UserMeter();
        audit = new ArrayList<>();
    }

    public User(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n" + role + "\n";
    }

    public List<String> getAudit() {
        return audit;
    }

    public UserMeter getUserMeter() {
        return userMeter;
    }
}
