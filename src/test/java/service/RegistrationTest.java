package service;

import org.example.model.repository.UserRepository;
import org.example.model.service.UserRegistration;
import org.example.entity.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RegistrationTest {

    @Spy
    UserRepository repository = new UserRepository();

    @Test
    public void registrationTest(){
        User user = new User("a", "a", "a","a");
        boolean registration = UserRegistration.registration("a", "a", "a", "a");
        assertEquals(user, repository.getUserByLogin("a"));
        assertEquals(true, registration);
    }
}
