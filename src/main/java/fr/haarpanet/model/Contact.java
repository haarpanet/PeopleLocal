package fr.haarpanet.model;

public class Contact {
    private Email email;
    private Telephone telephone;
    private long id;

    public Contact(Email email) {
        this.email = email;
    }

    public Contact(Telephone telephone) {
        this.telephone = telephone;
    }

    public Contact(Email email, Telephone telephone) {
        this.email = email;
        this.telephone = telephone;
    }

    public Email getEmail() {
        return email;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
