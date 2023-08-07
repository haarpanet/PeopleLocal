package fr.haarpanet.model;

public class Contact {
    private final Email email;
    private String telephone;
    private long id;

    public Contact(Email email) {
        this.email = email;
    }


    public Email getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(long id) {
        this.id = id;
    }
}
