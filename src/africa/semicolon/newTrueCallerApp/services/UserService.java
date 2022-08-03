package africa.semicolon.newTrueCallerApp.services;

import africa.semicolon.newTrueCallerApp.data.models.Contact;
import africa.semicolon.newTrueCallerApp.dtos.requests.ContactRequest;
import africa.semicolon.newTrueCallerApp.dtos.requests.RegisterRequest;
import africa.semicolon.newTrueCallerApp.dtos.responses.AllContactResponse;
import africa.semicolon.newTrueCallerApp.dtos.responses.ContactResponse;
import africa.semicolon.newTrueCallerApp.dtos.responses.RegisterUserResponse;
import africa.semicolon.newTrueCallerApp.exceptions.UserExistsException;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterRequest registerRequest) throws UserExistsException; /**throws UserExistsException;**/

    int numberOfUsers();

    ContactResponse addContact(ContactRequest contactRequest);

    void deleteContact(String contact);

    List<AllContactResponse> findContactsBelongingTo(String email);
}
