package africa.semicolon.newTrueCallerApp.data.repositories;

import africa.semicolon.newTrueCallerApp.data.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryImplTest {
    @Test
    void testThatUserIsAdded() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Praise");
        newUser.setPin("1234");
        userRepository.saveUser(newUser);
        assertEquals(1, userRepository.count());
    }

    @Test
    void addUser_deleteUserById() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Praise");
        newUser.setPin("1234");
        userRepository.saveUser(newUser);
        userRepository.delete(1);
        assertEquals(0, userRepository.count());
        assertNull(userRepository.findById(1));
    }

    @Test
    void addUser_deleteUser() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Praise");
        newUser.setPin("1234");
        userRepository.saveUser(newUser);
        userRepository.delete(newUser);
        assertEquals(0, userRepository.count());
        assertNull(userRepository.findById(1));
    }

    @Test
    void addUser_testThatPinIsCorrect() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Praise");
        newUser.setPin("1234");
        userRepository.saveUser(newUser);
        assertEquals("Eden", userRepository.find("Eden").getFirstName());
        assertTrue(userRepository.find("Eden").isLocked("1234"));
    }

    @Test
    void saveUser_updateUser() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Praise");
        newUser.setPin("1234");
        userRepository.saveUser(newUser);
        assertEquals(1, userRepository.count());
        newUser.setFirstName("DuckAss");
        newUser.setLastName("Obong");
        newUser.setPin("5678");
        userRepository.saveUser(newUser);
        assertEquals(1, userRepository.count());
        assertEquals("DuckAss", userRepository.findById(1).getFirstName());
        assertTrue(userRepository.find("DuckAss").isLocked("5678"));
    }

    @Test
    void saveContacts_viewAllUsersByFirstName() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        User newUser2 = new User();
        User newUser3 = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Praise");
        newUser.setPin("1234");
        newUser2.setFirstName("Eden");
        newUser2.setLastName("Obong");
        newUser2.setPin("5678");
        newUser3.setFirstName("Favour");
        newUser3.setLastName("Drake");
        newUser3.setPin("3459");
        userRepository.saveUser(newUser);
        userRepository.saveUser(newUser2);
        userRepository.saveUser(newUser3);
        assertEquals(2, userRepository.findByFirstName("Eden").size());
    }

    @Test
    void saveContacts_viewAllUsersByLastName() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        User newUser2 = new User();
        User newUser3 = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Drake");
        newUser.setPin("1234");
        newUser2.setFirstName("Eden");
        newUser2.setLastName("Obong");
        newUser2.setPin("5678");
        newUser3.setFirstName("Favour");
        newUser3.setLastName("Drake");
        newUser3.setPin("3459");
        userRepository.saveUser(newUser);
        userRepository.saveUser(newUser2);
        userRepository.saveUser(newUser3);
        assertEquals(2, userRepository.findByLastName("Drake").size());
    }

    @Test
    void saveContacts_viewAllUsers() {
        UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User();
        User newUser2 = new User();
        User newUser3 = new User();
        newUser.setFirstName("Eden");
        newUser.setLastName("Drake");
        newUser.setPin("1234");
        newUser2.setFirstName("Eden");
        newUser2.setLastName("Obong");
        newUser2.setPin("5678");
        newUser3.setFirstName("Favour");
        newUser3.setLastName("Drake");
        newUser3.setPin("3459");
        userRepository.saveUser(newUser);
        userRepository.saveUser(newUser2);
        userRepository.saveUser(newUser3);
        assertEquals(3, userRepository.findAll().size());
    }
}
