package africa.semicolon.newTrueCallerApp.services;

import africa.semicolon.newTrueCallerApp.data.repositories.ContactRepository;
import africa.semicolon.newTrueCallerApp.data.repositories.ContactRepositoryImpl;
import africa.semicolon.newTrueCallerApp.data.repositories.UserRepository;
import africa.semicolon.newTrueCallerApp.data.repositories.UserRepositoryImpl;
import africa.semicolon.newTrueCallerApp.dtos.requests.ContactRequest;
import africa.semicolon.newTrueCallerApp.dtos.requests.RegisterRequest;
import africa.semicolon.newTrueCallerApp.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceImplTest {
    UserService userCreated;
    UserRepository userRepository = new UserRepositoryImpl();
    ContactRepository contactRepository = new ContactRepositoryImpl();
    ContactService contactService = new ContactServiceImpl(contactRepository);
    RegisterRequest userRequest;
    ContactRequest contactRequest;

    @BeforeEach
    void setUp() {
        userCreated = new UserServiceImpl(userRepository, contactService);
        userRequest = new RegisterRequest();
        contactRequest = new ContactRequest();
    }

    @Test
    void testThatNewAccountIsCreated() throws UserExistsException {
        userRequest.setEmail("tee@gmail.com");
        userRequest.setFirstName("Tman");
        userRequest.setLastName("Acesochi");
        userRequest.setPin("7685");
        userCreated.register(userRequest);
        assertEquals(1, userCreated.numberOfUsers());
    }

    @Test
    void testThatDuplicateEmailThrowsException() throws UserExistsException {
        userRequest.setEmail("tee@gmail.com");
        userRequest.setFirstName("Tman");
        userRequest.setLastName("Acesochi");
        userRequest.setPin("7685");
        userCreated.register(userRequest);

        RegisterRequest userRequest2 = new RegisterRequest();
        userRequest2.setEmail("tee@gmail.com");
        userRequest2.setFirstName("Fash");
        userRequest2.setLastName("Perception");
        userRequest2.setPin("4567");
        assertEquals(1, userCreated.numberOfUsers());
        assertThrows(UserExistsException.class, () -> userCreated.register(userRequest2));
    }

    @Test
    void testThatAccountCanAddContacts() throws UserExistsException {
        userRequest.setEmail("tee@gmail.com");
        userRequest.setFirstName("Tman");
        userRequest.setLastName("Acesochi");
        userRequest.setPin("7685");
        userCreated.register(userRequest);

        contactRequest.setFirstName("UCJ");
        contactRequest.setLastName("Kay");
        contactRequest.setEmail("ucjK@semicolon.com");
        contactRequest.setPhoneNumber("1234567890");
        contactRequest.setUserEmail("tee@gmail.com");

        userCreated.addContact(contactRequest);
        assertEquals(1, userCreated.findContactsBelongingTo("tee@gmail.com").size());
    }
}
