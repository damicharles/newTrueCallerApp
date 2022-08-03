package africa.semicolon.newTrueCallerApp.controller;

import africa.semicolon.newTrueCallerApp.data.models.Contact;
import africa.semicolon.newTrueCallerApp.dtos.requests.ContactRequest;
import africa.semicolon.newTrueCallerApp.dtos.requests.RegisterRequest;
import africa.semicolon.newTrueCallerApp.dtos.responses.AllContactResponse;
import africa.semicolon.newTrueCallerApp.dtos.responses.ContactResponse;
import africa.semicolon.newTrueCallerApp.dtos.responses.RegisterUserResponse;
import africa.semicolon.newTrueCallerApp.exceptions.UserExistsException;
import africa.semicolon.newTrueCallerApp.services.UserService;
import africa.semicolon.newTrueCallerApp.services.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {
    public UserService userService = new UserServiceImpl();
    @PostMapping("/user")

    public RegisterUserResponse registerUser(@RequestBody RegisterRequest registerRequest) throws UserExistsException {
        return userService.register(registerRequest);
    }
    @PatchMapping("/user")

    public ContactResponse addContact(@RequestBody ContactRequest contactRequest) {
        return userService.addContact(contactRequest);
    }
    @GetMapping("/user/{email}")

    public List<AllContactResponse> findContactBelongingTo(@PathVariable String email) {
        return userService.findContactsBelongingTo(email);
    }
}
