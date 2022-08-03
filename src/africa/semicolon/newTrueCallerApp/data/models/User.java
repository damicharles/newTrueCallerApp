package africa.semicolon.newTrueCallerApp.data.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String pin;
    private int id;
    private boolean setLock;
    private List<Contact> contacts = new ArrayList<>();


}
