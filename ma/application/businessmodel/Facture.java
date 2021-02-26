package ma.application.businessmodel;

import java.util.Date;
import java.util.List;

public class Facture {


    private String numero;
    private Date date;
    //many to one
    private Client client;

    //One to many
    private List<LigneDeCommande> lignesDeCommande;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<LigneDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public void setLignesDeCommande(List<LigneDeCommande> lignesDeCommande) {
        this.lignesDeCommande = lignesDeCommande;
    }

    public Facture(String numero, Date date, Client client) {
        this.numero = numero;
        this.date = date;
        this.client = client;
    }

}
