package africa.semicolon.newTrueCallerApp.services;

import africa.semicolon.newTrueCallerApp.data.models.Contact;
import africa.semicolon.newTrueCallerApp.data.repositories.ContactRepository;
import africa.semicolon.newTrueCallerApp.data.repositories.ContactRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactServiceImplTest {
    @Test
    void testThatContactIsAdded() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        ContactService contactService = new ContactServiceImpl(contactRepository);
        Contact contact = new Contact("Semicolon","Alarm","read@gmail.com","1234567890");
        contactService.saveContact(contact);
        assertEquals(1,contactService.getNumberOfContacts());
        assertEquals(1, contact.getId());
    }
}
