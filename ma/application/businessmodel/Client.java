package ma.application.businessmodel;

import java.util.List;

public class Client {
    private  Long id;
    private String nom;

    // Si visibilité
    List<Facture> factures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public Client(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
