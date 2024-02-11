package org.example.entity.user.userDTO;

import java.util.Objects;

public final class UserDTO {

    private final int id;

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String login;

    public UserDTO(int id, String firstName, String lastName, String password, String login) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(password, userDTO.password) && Objects.equals(login, userDTO.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, password, login);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
