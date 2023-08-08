package fr.haarpanet.model;

public class Personne {
    private final Identite identite;
    private Contact contact;
    private Long id;

    public Personne(Identite identite) {
        this.identite = identite;
    }

    public Identite getIdentite() {
        return identite;
    }

    public Contact getContact() {
        return contact;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Personne(Identite identite, Contact contact) {
        this.identite = identite;
        this.contact = contact;
    }

}
