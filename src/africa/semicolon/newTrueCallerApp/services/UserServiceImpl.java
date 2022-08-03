package africa.semicolon.newTrueCallerApp.services;

import africa.semicolon.newTrueCallerApp.data.models.Contact;
import africa.semicolon.newTrueCallerApp.data.models.User;
import africa.semicolon.newTrueCallerApp.data.repositories.ContactRepository;
import africa.semicolon.newTrueCallerApp.data.repositories.ContactRepositoryImpl;
import africa.semicolon.newTrueCallerApp.data.repositories.UserRepository;
import africa.semicolon.newTrueCallerApp.data.repositories.UserRepositoryImpl;
import africa.semicolon.newTrueCallerApp.dtos.requests.ContactRequest;
import africa.semicolon.newTrueCallerApp.dtos.requests.RegisterRequest;
import africa.semicolon.newTrueCallerApp.dtos.responses.AllContactResponse;
import africa.semicolon.newTrueCallerApp.dtos.responses.ContactResponse;
import africa.semicolon.newTrueCallerApp.dtos.responses.RegisterUserResponse;
import africa.semicolon.newTrueCallerApp.exceptions.UserExistsException;
import africa.semicolon.newTrueCallerApp.utils.Mapper;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private RegisterUserResponse uResponse = new RegisterUserResponse();
    private final ContactService contactService;

    public UserServiceImpl(UserRepository userRepository, ContactService contactService) {
        this.userRepository = userRepository;
        this.contactService = contactService;
    }

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
        ContactRepository contactRepository = new ContactRepositoryImpl();
        this.contactService = new ContactServiceImpl(contactRepository);

    }

    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) throws UserExistsException {
        validateUser(registerRequest);
        User newUserToAdd = new User();
        Mapper.map(registerRequest, newUserToAdd);
        userRepository.saveUser(newUserToAdd);
        uResponse.setMessage("New user " + registerRequest.getFirstName() + " " + registerRequest.getLastName() + " successfully registered.");
        return uResponse;
    }

    private void validateUser(RegisterRequest registerRequest) throws UserExistsException {
        var savedUser = userRepository.findByEmail(registerRequest.getEmail());
        if (savedUser != null) {
            throw new UserExistsException("User with " + registerRequest.getEmail() + " already exists.");
        }
    }

    @Override
    public int numberOfUsers() {
        return userRepository.count();
    }

    @Override
    public ContactResponse addContact(ContactRequest contactRequest) {
        Contact newContact = new Contact();
        Mapper.map(contactRequest, newContact);
        var savedContact = contactService.saveContact(newContact);
        User user = userRepository.findByEmail(contactRequest.getUserEmail());
        user.getContacts().add(savedContact);
        userRepository.saveUser(user);
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setMessage(String.format("%s successfully added.", contactRequest.getFirstName()));
        return contactResponse;
    }

    @Override
    public void deleteContact(String contact) {
    }

    @Override
    public List<AllContactResponse> findContactsBelongingTo(String email) {
        var user = userRepository.findByEmail(email);
        List<Contact> allUserContacts = user.getContacts();
        List<AllContactResponse> response = new ArrayList<>();
        allUserContacts.forEach(contact -> {
            AllContactResponse singleResponse = new AllContactResponse();
            Mapper.map(singleResponse, contact);
            response.add(singleResponse);
        });
        return response;
    }
}
