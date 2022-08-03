package africa.semicolon.newTrueCallerApp.data.repositories;

import africa.semicolon.newTrueCallerApp.data.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {
    private int counter;
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public Contact save(Contact contact) {
        var contactToEdit = findById(contact.getId());
        if (contactToEdit == null) {
            counter++;
            contact.setId(counter);
            contacts.add(contact);
        } else {
            contactToEdit.setFirstName(contact.getFirstName());
            contactToEdit.setLastName(contact.getLastName());
            contactToEdit.setPhoneNumber(contact.getPhoneNumber());
            contactToEdit.setEmail(contact.getEmail());
        }
        return contact;
    }

    @Override
    public void delete(Contact contactToDelete) {
        for (var contact : contacts) {
            if (contact.getId() == contactToDelete.getId()) {
                contacts.remove(contact);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (var contact : contacts) {
            if (contact.getId() == id) {
                contacts.remove(contact);
                break;
            }
        }
    }

    @Override
    public Contact findById(int id) {
        for (var contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return contacts;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        ArrayList<Contact> contactsOfFirstNames = new ArrayList<>();
        for (var contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName)) {
                contactsOfFirstNames.add(contact);
            }
        }
        return contactsOfFirstNames;
    }

    @Override
    public List<Contact> findByLastName(String lastName) {
        ArrayList<Contact> contactsOfLastNames = new ArrayList<>();
        for (var contact : contacts) {
            if (contact.getLastName().equalsIgnoreCase(lastName)) {
                contactsOfLastNames.add(contact);
            }
        }
        return contactsOfLastNames;
    }

    @Override
    public int count() {
        return contacts.size();
    }
}
