package fr.haarpanet.model;

import java.util.Objects;

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

    public Contact() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && Objects.equals(email, contact.email) && Objects.equals(telephone, contact.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, telephone, id);
    }
}
