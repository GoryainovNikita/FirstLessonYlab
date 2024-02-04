package service;

import org.example.model.repository.UserRepository;
import org.example.model.service.UserLogin;
import org.example.entity.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

    @Spy
    UserRepository repository = new UserRepository();

    @Test
    public void loginTest(){
        User user = new User("a", "a", "a","a");
        repository.addUser(new User("a", "a", "a","a"));
        User login = UserLogin.login("a", "a");
        assertEquals(user, login);
    }


}
