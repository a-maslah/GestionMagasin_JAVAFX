package ma.application.businessmodel;

public class Produit {
    private Long id;
    private String designation;
    private Double prix;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Produit(Long id, String designation, Double prix) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
    }
}

