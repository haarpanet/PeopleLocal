package fr.haarpanet.model;

public class Telephone {

    private String valeur;
    private Long id;

    public Telephone(String valeur) {
        this.valeur = valeur;
    }

    public Telephone() {

    }

    public Long getId() {
        return id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
