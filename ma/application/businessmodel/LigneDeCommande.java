package ma.application.businessmodel;

public class LigneDeCommande {
    private Long id;
    private Integer qte;
    private Double souTotal;
    private Facture facture;
    private Produit produit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Double getSouTotal() {
        return souTotal;
    }

    public void setSouTotal(Double souTotal) {
        this.souTotal = souTotal;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public LigneDeCommande(Long id, Integer qte, Double souTotal, Facture facture, Produit produit) {
        this.id = id;
        this.qte = qte;
        this.souTotal = souTotal;
        this.facture = facture;
        this.produit = produit;
    }
}
