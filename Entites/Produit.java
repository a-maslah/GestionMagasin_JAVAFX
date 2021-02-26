package Entites;

import java.time.LocalDate;

public class Produit {
   private int id;
   private String designation;
   private double prix;
   private int qte;
   private Categorie c;
   private LocalDate date;
   
public Categorie getC() {
	return c;
}
public void setC(Categorie c) {
	this.c = c;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}
public int getQte() {
	return qte;
}
public void setQte(int qte) {
	this.qte = qte;
}
public  LocalDate  getDate() {
	return date;
}
public void setDate( LocalDate  date) {
	this.date = date;
}


public Produit(int id, String designation, double prix, int qte, Categorie c, LocalDate date) {
	super();
	this.id = id;
	this.designation = designation;
	this.prix = prix;
	this.qte = qte;
	this.c = c;
	this.date = date;
}
public Produit(int id, String designation, double prix, int qte,  LocalDate  date) {
	this.id = id;
	this.designation = designation;
	this.prix = prix;
	this.qte = qte;
	this.date = date;
}
public Produit(int id,String designation, double prix, int qte) {
	this.id = id;
	this.designation = designation;
	this.prix = prix;
	this.qte = qte;
}
public Produit(String designation, double prix,int qte,Categorie c ,  LocalDate  date) {
	
	this.designation = designation;
	this.prix = prix;
	this.qte = qte;
	this.c = c;

	this.date = date;
}
   public double CalcTotal() {
	   return prix*qte;
   }

   public Produit(String designation, double prix,Categorie c) {
	   this.designation = designation;
		this.prix = prix;
		this.c = c; 
   }
   
   public Produit(int id,String designation, double prix,Categorie c) {
		this.id = id;

	   this.designation = designation;
		this.prix = prix;
		this.c = c; 
   }
public Produit(String designation, double prix) {
	   this.designation = designation;
		this.prix = prix;
   }
public Produit(int id,String designation, double prix) {
	   this.designation = designation;
		this.prix = prix;
		this.id=id;
}
@Override
public String toString() {
		return designation;
}
   
}
