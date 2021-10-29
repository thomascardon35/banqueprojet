package banque_projet;

public class Titulaire {

    private int code;
    private String prenom;
    private String nom;
    private String adresse;
    private int codePostal;
    public Titulaire(int code, String prenom, String nom, String adresse, int codePostal) {
        super();
        this.code = code;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
    }

    public Titulaire() {
        // TODO
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Titulaire [code=" + code + ", prenom=" + prenom + ", nom=" + nom + ", adresse=" + adresse
                + ", codePostal=" + codePostal + "]";
    }


}