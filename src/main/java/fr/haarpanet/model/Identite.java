package fr.haarpanet.model;

public class Identite {
    private final String nomComplet;
    private Long id;

    public Identite(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
