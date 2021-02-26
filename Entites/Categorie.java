package Entites;

import java.util.List;

public class Categorie {
   private int id;
   private String label;
   List<Produit> Produits;
   
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public Categorie(int id, String label) {
	this.id = id;
	this.label = label;
}

public Categorie(String label) {
	this.label = label;
}
@Override
public String toString() {
	return label;
}
public List<Produit> getProduits() {
	return Produits;
}
public void setProduits(List<Produit> produits) {
	Produits = produits;
}
   
   
}
