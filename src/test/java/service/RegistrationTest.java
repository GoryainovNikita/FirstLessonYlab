package service;

import org.example.model.db.Repository;
import org.example.model.service.Registration;
import org.example.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RegistrationTest {

    @Spy
    Repository repository = new Repository();

    @Test
    public void registrationTest(){
        User user = new User("a", "a", "a","a");
        boolean registration = Registration.registration("a", "a", "a", "a");
        assertEquals(user, repository.getUserByLogin("a"));
        assertEquals(true, registration);
    }
}
