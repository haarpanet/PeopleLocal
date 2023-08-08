package fr.haarpanet.model;

public class Email {
    private String valeur;
    private Long id;

    public Email(String valeur) {
        this.valeur = valeur;
    }

    public Email() {

    }

    public Long getId() {
        return id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
