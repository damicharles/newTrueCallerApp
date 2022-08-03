package africa.semicolon.newTrueCallerApp.services;

import africa.semicolon.newTrueCallerApp.data.models.Contact;

public interface ContactService {
    Contact saveContact(Contact contact);
    int getNumberOfContacts();
}
