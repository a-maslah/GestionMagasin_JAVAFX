package Entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vente {
private int id;
private LocalDate date;
private double total;
List<LigneCommande> listCommande = new ArrayList<>();
Client client;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
public List<LigneCommande> getListCommande() {
	return listCommande;
}
public void setListCommande(List<LigneCommande> listCommande) {
	this.listCommande = listCommande;
}
public Vente(int id, LocalDate date, List<LigneCommande> listCommande) {
	super();
	this.id = id;
	this.date = date;
	this.listCommande = listCommande;
}
public Vente(int id, LocalDate date) {
	this.id = id;
	this.date = date;
}
public Client getClient() {
	return client;
}

public void setClient(Client client) {
	this.client = client;
}
public Vente() {
	
}
public Vente(int id, double total, LocalDate date) {
	super();
	this.id = id;
	this.date = date;
	this.total = total;
}
public Vente(int id, double total, Client client,LocalDate date) {
	super();
	this.id = id;
	this.date = date;
	this.total = total;
	this.client = client;
}
@Override
public String toString() {
	return   client.getNom()+" "+client.getPrenom();
}


}
