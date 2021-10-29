package banque_projet;

import java.sql.Date;

public class Operations {
	
	private int numero;
	private Compte numCompte;
	private Date date;
	private String libelle;
	private float montant;
	private String typeOf;
	
	
	public Operations(int numero, Compte numCompte, Date date, String libelle, float montant, String typeOf) {
		
		super();
		this.numero = numero;
		this.numCompte = numCompte;
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
		this.typeOf = typeOf;
		
		
	}
	
	public Operations() {
		
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public Compte getNumCompte() {
		return numCompte;
	}


	public void setNumCompte(Compte numCompte) {
		this.numCompte = numCompte;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}


	public String getTypeOf() {
		return typeOf;
	}


	public void setTypeOf(String typeOf) {
		this.typeOf = typeOf;
	}


	@Override
	public String toString() {
		return "Operations numero : " + numero + ", numCompte : " + numCompte.getNumero() + ", date : " + date + ", libelle : " + libelle
				+ ", montant : " + montant + ", typeOf : " + typeOf + "\n";
	}
	

}
