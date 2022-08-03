package africa.semicolon.newTrueCallerApp.data.repositories;

import africa.semicolon.newTrueCallerApp.data.models.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactRepositoryImplTest {
    @Test
    void saveContact_CountIncreasesTest() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Semicolon");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");

        contactRepository.save(contact1);
        assertEquals(1, contactRepository.count());
    }

    @Test
    void saveContact_findByIdTest() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Semicolon");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);
        assertEquals(1, contactRepository.count());

        Contact savedContact = contactRepository.findById(1);
        assertEquals("Chibuzo", savedContact.getFirstName());
    }

    @Test
    void saveContact_deleteContactByIdTest() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Semicolon");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);
        assertEquals(1, contactRepository.count());

        contactRepository.delete(1);
        assertEquals(0, contactRepository.count());
    }

    @Test
    void saveContacts_deleteAContactByContactName() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Semicolon");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Ace");
        contact2.setLastName("Boyo");
        contact2.setEmail("ace@boyo.com");
        contact2.setPhoneNumber("123409877");
        contactRepository.save(contact2);
        assertEquals(2, contactRepository.count());

        contactRepository.delete(contact1);
        Contact savedContact = contactRepository.findById(2);
        assertEquals("Ace", savedContact.getFirstName());
        assertEquals(1, contactRepository.count());
    }

    @Test
    void saveContacts_findContactByFirstName() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Semicolon");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);
        Contact contact2 = new Contact();
        contact2.setFirstName("Ace");
        contact2.setLastName("Boyo");
        contact2.setEmail("ace@boyo.com");
        contact2.setPhoneNumber("123409877");
        contactRepository.save(contact2);
        Contact contact3 = new Contact();
        contact3.setFirstName("Chibuzo");
        contact3.setLastName("Africa");
        contact3.setEmail("africaChibuzo@semic.com");
        contact3.setPhoneNumber("090887");
        contactRepository.save(contact3);
        assertEquals(3, contactRepository.count());

        assertEquals(2,contactRepository.findByFirstName("Chibuzo").size());
    }

    @Test
    void saveContacts_findContactByLastName() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Africa");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);
        Contact contact2 = new Contact();
        contact2.setFirstName("Ace");
        contact2.setLastName("Boyo");
        contact2.setEmail("ace@boyo.com");
        contact2.setPhoneNumber("123409877");
        contactRepository.save(contact2);
        Contact contact3 = new Contact();
        contact3.setFirstName("Chibuzo");
        contact3.setLastName("Africa");
        contact3.setEmail("africaChibuzo@semic.com");
        contact3.setPhoneNumber("090887");
        contactRepository.save(contact3);
        assertEquals(3, contactRepository.count());

        assertEquals(2,contactRepository.findByLastName("africa").size());
    }

    @Test
    void saveContacts_updateContactTest() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Africa");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);
        assertEquals(1, contactRepository.count());

        contact1.setFirstName("Ace");
        contact1.setLastName("Boyo");
        contact1.setEmail("ace@boyo.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);

        assertEquals(1, contactRepository.count());
        assertEquals("Ace", contactRepository.findById(1).getFirstName());
    }

    @Test
    void saveContacts_findAllContacts() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        Contact contact1 = new Contact();
        contact1.setFirstName("Chibuzo");
        contact1.setLastName("Africa");
        contact1.setEmail("semi@semic.com");
        contact1.setPhoneNumber("123409877");
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Ace");
        contact2.setLastName("Boyo");
        contact2.setEmail("ace@boyo.com");
        contact2.setPhoneNumber("123409877");
        contactRepository.save(contact2);

        assertEquals(2,contactRepository.findAll().size());
    }
}
