package service;

import org.example.model.db.Repository;
import org.example.model.service.UserValidation;
import org.example.entity.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ValidationTest {

    @Spy
    Repository repository = new Repository();

    @Test
    public void validationTrueTest(){
        User user = new User("a", "a", "a","a");
        repository.addUser(new User("a", "a", "a","a"));
        boolean a = UserValidation.validation("a");
        assertEquals(true, a);
    }

    @Test
    public void validationFalseTest(){
        User user = new User("a", "a", "a","a");
        repository.addUser(new User("a", "a", "a","a"));
        boolean a = UserValidation.validation("b");
        assertEquals(false, a);
    }
}
