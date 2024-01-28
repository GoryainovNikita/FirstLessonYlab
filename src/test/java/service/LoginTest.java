package service;

import org.example.model.db.Repository;
import org.example.model.service.Login;
import org.example.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

    @Spy
    Repository repository = new Repository();

    @Test
    public void loginTest(){
        User user = new User("a", "a", "a","a");
        repository.addUser(new User("a", "a", "a","a"));
        User login = Login.login("a", "a");
        assertEquals(user, login);
    }


}
