package Entites;

import java.time.LocalDate;

public class Payment {
	private int id;
    private Vente vente;
    private double montant;
    private LocalDate date;
   // private String type;
    PaymentType type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public PaymentType getType() {
		return type;
	}
	public void setType(PaymentType type) {
		this.type = type;
	}
	public Payment(int id,Vente vente, double montant, LocalDate date, PaymentType type) {
		this.vente = vente;
		this.id=id;
		this.montant = montant;
		this.date = date;
		this.type = type;
	}
	

}
