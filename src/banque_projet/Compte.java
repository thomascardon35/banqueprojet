package banque_projet;

public class Compte {

    private int numero;
    private Typedecompte codeTypeCompte;
    private Titulaire codeTitulaire;
    private float solde;
    
    
    public Compte(int numero, Typedecompte codeTypeCompte, Titulaire codeTitulaire, float solde) {
        this.numero = numero;
        this.codeTypeCompte = codeTypeCompte;
        this.codeTitulaire = codeTitulaire;
        this.solde = solde;
    }
    
    public Compte() {
    	
    }


    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public Typedecompte getCodeTypeCompte() {
        return codeTypeCompte;
    }


    public void setCodeTypeCompte(Typedecompte codeTypeCompte) {
        this.codeTypeCompte = codeTypeCompte;
    }


    public Titulaire getCodeTitulaire() {
        return codeTitulaire;
    }


    public void setCodeTitulaire(Titulaire codeTitulaire) {
        this.codeTitulaire = codeTitulaire;
    }


    public float getSolde() {
        return solde;
    }


    public void setSolde(float solde) {
        this.solde = solde;
    }


    @Override
    public String toString() {
        return "Compte codeTitulaire : " + codeTitulaire.getCode() + ", codeTypeCompte : " + codeTypeCompte.getId() + ", numero : " + numero
                + ", solde : " + solde + "";
    }

 
    
}
