package db;

import org.example.model.repository.UserRepository;
import org.example.entity.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RepositoryTest {

    @Spy
    UserRepository repository = new UserRepository();

    @Test
    public void addAndGetUserTest(){
        User user = new User("a", "a", "a","a");
        repository.addUser(new User("a", "a", "a","a"));
        User user1 = repository.getUserById(1);
        assertEquals(user, user1);
    }

    @Test
    public void getUserByLoginTest(){
        User user = new User("a", "a", "a","a");
        repository.addUser(new User("a", "a", "a","a"));
        User userByLogin = repository.getUserByLogin("a");
        assertEquals(user, userByLogin);
    }

    @Test
    public void getUserByLoginExceptionTest(){
        assertThrows(NoSuchElementException.class,() -> repository.getUserByLogin("b"));
    }




}
