package fr.haarpanet.model;

public class Telephone {

    private final String valeur;
    private Long id;

    public Telephone(String valeur) {
        this.valeur = valeur;
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
}
