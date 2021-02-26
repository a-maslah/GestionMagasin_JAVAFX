package Entites;

public class LigneCommande {
private int id;
private int qteVendu;
private Produit produit;
private double stotal;
Vente v;
public LigneCommande() {
	// TODO Auto-generated constructor stub
}
public LigneCommande(int id, int qteVendu, Produit produit) {
	this.id = id;
	this.qteVendu = qteVendu;
	this.produit = produit;
}
public LigneCommande(int id, int qteVendu, Produit produit, Vente v) {
	super();
	this.id = id;
	this.qteVendu = qteVendu;
	this.produit = produit;
	this.v = v;
}

public LigneCommande(int id,Produit produit ,int qteVendu, double stotal, Vente v) {
	super();
	this.id = id;
	this.qteVendu = qteVendu;
	this.stotal = stotal;
	this.produit = produit;

	this.v = v;
}
public LigneCommande(int id, int qteVendu, double stotal) {
	super();
	this.id = id;
	this.qteVendu = qteVendu;
	this.stotal = stotal;
}
public LigneCommande(int qteVendu, Produit produit) {
	super();
	this.qteVendu = qteVendu;
	this.produit = produit;
}
public double getStotal() {
	CalcStotal();
	return stotal;
}
public void setStotal(double stotal) {
	this.stotal = stotal;
}
public Vente getV() {
	return v;
}
public void setV(Vente v) {
	this.v = v;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getQteVendu() {
	return qteVendu;
}

public void setQteVendu(int qteVendu) {
	this.qteVendu = qteVendu;
}
public Produit getProduit() {
	return produit;
}
public void setProduit(Produit produit) {
	this.produit = produit;
}
public void CalcStotal() {
	stotal=this.qteVendu*this.produit.getPrix();
}
@Override
public String toString() {
	return id +"" + qteVendu + ""  + "" + stotal;
}

}
