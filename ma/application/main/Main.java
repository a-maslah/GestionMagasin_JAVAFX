package ma.application.main;

import ma.application.businessmodel.Client;
import ma.application.businessmodel.Facture;
import ma.application.businessmodel.LigneDeCommande;
import ma.application.businessmodel.Produit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Client client = getClient(Long.valueOf(1));

        System.out.println("Factures du client ayant numéro " + client.getId() + " " + client.getNom());
        System.out.println("");
        System.out.println("");

        List<Facture> facturesClient = getFactures(client);
        for (Facture facture : facturesClient) {
            System.out.println("Numéro " + facture.getNumero());
            System.out.println("Date " + facture.getDate());
            System.out.println("------------------------------------------------");
            System.out.println("    Qte     |   Sous Total  |       Article     ");
            System.out.println("------------------------------------------------");

            for (LigneDeCommande ligneDeCommande : facture.getLignesDeCommande()) {
                System.out.println("      " + ligneDeCommande.getQte() +"    |    " + ligneDeCommande.getSouTotal() +"     |       "+ ligneDeCommande.getProduit().getDesignation());
            }
            System.out.println("------------------------------------------------");
            System.out.println("");
            System.out.println("");
        }


        //
    }

    public static Client getClient(Long idClient) {
        return new Client(Long.valueOf(1), "Ali Moufid");
    }

    public static List<Facture> getFactures(Client client) {

        Produit produit1 = new Produit(Long.valueOf(1), "Iphone X", Double.valueOf(14000.00));
        Produit produit2 = new Produit(Long.valueOf(1), "Airpods 2", Double.valueOf(3000.00));

        Facture facture1 = new Facture("1/A", new Date(2020, 1, 1), client);
        LigneDeCommande ligne11 = new LigneDeCommande(Long.valueOf(1), Integer.valueOf(2), Double.valueOf(28000.00), facture1, produit1);
        LigneDeCommande ligne12 = new LigneDeCommande(Long.valueOf(1), Integer.valueOf(3), Double.valueOf(18000.00), facture1, produit2);
        List<LigneDeCommande> lignesDeCommande1 = new ArrayList<>();
        lignesDeCommande1.add(ligne11);
        lignesDeCommande1.add(ligne12);
        facture1.setLignesDeCommande(lignesDeCommande1);


        Facture facture2 = new Facture("2/A", new Date(2020, 1, 1), client);
        LigneDeCommande ligne21 = new LigneDeCommande(Long.valueOf(1), Integer.valueOf(1), Double.valueOf(14000.00), facture1, produit1);
        LigneDeCommande ligne22 = new LigneDeCommande(Long.valueOf(1), Integer.valueOf(1), Double.valueOf(3000.00), facture1, produit2);
        List<LigneDeCommande> lignesDeCommande2 = new ArrayList<>();
        lignesDeCommande2.add(ligne21);
        lignesDeCommande2.add(ligne22);
        facture2.setLignesDeCommande(lignesDeCommande2);


        List<Facture> facturesClient = new ArrayList<>();
        facturesClient.add(facture1);
        facturesClient.add(facture2);

        client.setFactures(facturesClient);


        // public LigneDeCommande(Long id, Integer qte, Long souTotal, Facture facture, Produit produit) {

        return facturesClient;
    }
}
