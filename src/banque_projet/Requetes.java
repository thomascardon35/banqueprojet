package banque_projet;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Requetes {
	
	/**
     * TYPE DE COMPTE
     * @throws SQLException
     * 
     */
	
	/* récupération d'un type de compte en BDD */

	public static Typedecompte getTypedecompteById(int id) throws SQLException {
		String req = "SELECT * FROM typecompte WHERE code =" + id;
		ResultSet resultat = AccesBD.executerQuery(req);

		Typedecompte typedecompte = new Typedecompte();
		
		while(resultat.next()) {
			typedecompte.setId(resultat.getInt("code"));
			typedecompte.setType(resultat.getString("intitule"));
		}
		
		return typedecompte;
		
	}
	
	/* Ajout d'un type de compte en BDD */
	/* Pour l'auto-incrémenter préciser 0 dans values */
	public static void  ajouterTypedecompte(String typeDeCompte) throws SQLException{
        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO typecompte VALUES (0 , ?)");
        

        PreparedStatement.setString(1, typeDeCompte);
        PreparedStatement.executeUpdate();
		
		
	}
	
	/* suppression d'un type de compte avec son id */
	public static void  supprimerTypedecompteById(int id) throws SQLException{
		
		PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("DELETE FROM typecompte WHERE code = ?");
        
        PreparedStatement.setInt(1, id);
        PreparedStatement.executeUpdate();
	}
	
	/* modification d'un type de compte */
	public static void  modifierTypedecompte(Typedecompte typedecompte) throws SQLException{
		

		PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("UPDATE typecompte SET intitule = ? WHERE code = ?");

        PreparedStatement.setString(1, typedecompte.getType());
        PreparedStatement.setInt(2, typedecompte.getId());
        PreparedStatement.executeUpdate();
		
	}
	
	
	/**
     * OPERATIONS
     * @throws SQLException
     * 
     */
		
	public static ArrayList<Operations> getOperationsByNumCompte(int numCompte) throws SQLException {

		ArrayList<Operations> operations = new ArrayList<>();
		
		String req = "SELECT * FROM operations WHERE numeroCompte =" + numCompte;
		ResultSet resultat = AccesBD.executerQuery(req);
		
		while(resultat.next()) {

			Operations operation = new Operations();
			operation.setNumero(resultat.getInt("numero"));
			operation.setNumCompte(Requetes.getCompteById(resultat.getInt("numeroCompte")));
			operation.setDate(resultat.getDate("date"));
			operation.setLibelle(resultat.getString("libelle"));
			operation.setMontant(resultat.getFloat("montant"));
			operation.setTypeOf(resultat.getString("typeop"));
			operations.add(operation);
		}
		
		return operations;
		
		
	}
	
	public static void operationSurCompte( int idOp) throws SQLException {
        String requete = "select typeop from operations where numero =" + idOp;

        ResultSet typeop = AccesBD.executerQuery(requete);
        typeop.next();
        String symbol = typeop.getString("typeop");

        String req = "update compte lol join operations, compte set compte.solde = compte.solde"  + symbol + " operations.montant where operations.numeroCompte = compte.numero and operations.numero = ?" ;

        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement(req);

        PreparedStatement.setInt(1, idOp);
        PreparedStatement.executeUpdate();

    }
	
	/**
     * Titulaire
     * @throws SQLException
     * 
     */

     public static Titulaire getTitulaireById(int id) throws SQLException{
         String requete = "select * from titulaire where code = " + id;
         ResultSet resultat = AccesBD.executerQuery(requete);

         Titulaire titulaire = new Titulaire();

         resultat.next();
         titulaire.setCode(resultat.getInt("code"));
         titulaire.setPrenom(resultat.getString("prenom"));
         titulaire.setNom(resultat.getString("nom"));
         titulaire.setAdresse(resultat.getString("adresse"));
         titulaire.setCodePostal(resultat.getInt("codePostal"));

         return titulaire;
     }
     
     /*
     * 
     * * Créer un Titulaire
     * @param titulaire
     * @throws SQLException
     */

   public static void creerTitulaire(Titulaire titulaire) throws SQLException {
       String requete = "INSERT INTO TITULAIRE VALUES (?, ?, ?, ?, ?)";
       PreparedStatement PS = AccesBD.getConnection().prepareStatement(requete);
       PS.setInt(1, titulaire.getCode());
       PS.setString(2, titulaire.getPrenom());
       PS.setString(3, titulaire.getNom());
       PS.setString(4, titulaire.getAdresse());
       PS.setInt(5, titulaire.getCodePostal());
       PS.executeUpdate();
   }

   /*
    * *Update un titulaire
    * @param titulaire
    * @throws SQLException
    */

   public static void modifierTutilaire (Titulaire titulaire) throws SQLException {
       String requete = "UPDATE titulaire SET prenom = ?,nom = ?,adresse = ?, codePostal = ? WHERE code = ?";
       PreparedStatement PS = AccesBD.getConnection().prepareStatement(requete);
       PS.setString(1, titulaire.getPrenom());
       PS.setString(2, titulaire.getNom());
       PS.setString(3, titulaire.getAdresse());
       PS.setInt(4, titulaire.getCodePostal());
       PS.setInt(5, titulaire.getCode());
       PS.executeUpdate();
   }

   /**
    * * Delete un utilitaire
    * @param id
    * @throws SQLException
    */
   public static void deleteTitulaire(int id) throws SQLException {
       String requete = "DELETE FROM titulaire WHERE code = ?";
       PreparedStatement PS = AccesBD.getConnection().prepareStatement(requete);
       PS.setInt(1, id);
       PS.executeUpdate();
   }
   
   public static ArrayList<Compte> getCompteTitulaire(int id) throws SQLException {
       String requete =  "select * from compte where codeTitulaire = " + id ;
       ArrayList<Compte> comptes = new ArrayList<>();

       ResultSet resultat = AccesBD.executerQuery(requete);

       while (resultat.next()) {
           Compte compte = new Compte();
           compte.setNumero(resultat.getInt("numero"));
           compte.setCodeTypeCompte(Requetes.getTypedecompteById(resultat.getInt("codeTypeCompte")));
           compte.setCodeTitulaire(Requetes.getTitulaireById(resultat.getInt("codeTitulaire")));
           compte.setSolde(resultat.getFloat("solde"));
           comptes.add(compte);
       }

       return comptes;
   }
   
   /**
    * * COMPTE
    * 
    * @throws SQLException
    */
   
   
   public static ArrayList<Compte> getCompte() throws SQLException {
       ArrayList<Compte> comptes = new ArrayList<Compte>();
       ResultSet resultat = AccesBD.executerQuery("SELECT * FROM COMPTE");

       while(resultat.next()){
       Compte compte = new Compte();
       compte.setNumero(resultat.getInt("numero"));
       compte.setCodeTypeCompte(Requetes.getTypedecompteById(resultat.getInt("codeTypeCompte")));
       compte.setCodeTitulaire(Requetes.getTitulaireById(resultat.getInt("codeTitulaire")));
       compte.setSolde(resultat.getFloat("solde"));
       comptes.add(compte);
       }

       return comptes ;
      }
	
   public static Compte getCompteById(int id) throws SQLException {
	    String req = "SELECT * FROM COMPTE WHERE numero = " + id; 
	    ResultSet resultat = AccesBD.executerQuery(req);

	    Compte compte = new Compte();
	    
	    resultat.next();
	    compte.setNumero(resultat.getInt("numero"));
	    compte.setCodeTypeCompte(Requetes.getTypedecompteById(resultat.getInt("codeTypeCompte")));
	    compte.setCodeTitulaire(Requetes.getTitulaireById(resultat.getInt("codeTitulaire")));
	    compte.setSolde(resultat.getFloat("solde"));
	    
	    return compte;
	}
   
   public static void createCompte(Compte compte) throws SQLException {
       PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO compte VALUES (?,?,?,?)");
        PreparedStatement.setInt(1, compte.getNumero());
        PreparedStatement.setInt(2, compte.getCodeTypeCompte().getId());
        PreparedStatement.setInt(3, compte.getCodeTitulaire().getCode());
        PreparedStatement.setFloat(4, compte.getSolde());
        PreparedStatement.executeUpdate();
      }
   
	// DELETE COMPTE
	   public static void deleteCompte (int numero) throws SQLException {
	    PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("DELETE FROM compte WHERE numero= ?");
	
	    PreparedStatement.setInt(1, numero);
	    PreparedStatement.executeUpdate();
	   }
	   
	 //MODIFS COMPTE
	 public static void modifCompte (int numero, float solde) throws SQLException{
	
	    PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("UPDATE compte SET solde = ? WHERE numero = ?");
	
	        PreparedStatement.setFloat(1, solde);
	        PreparedStatement.setInt(2, numero);
	        PreparedStatement.executeUpdate();
	   }
	 
	 
	 
	 public static void ajouterOperations(Operations operation) throws SQLException {
	      PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO operations VALUES (?,?,?,?)");
	      PreparedStatement.setInt(1, operation.getNumero());
	      PreparedStatement.setInt(2, operation.getNumCompte().getNumero());
	      PreparedStatement.setDate(3, operation.getDate());
	      PreparedStatement.setString(4, operation.getLibelle());
	      PreparedStatement.setFloat(5, operation.getMontant() );
	      PreparedStatement.setString(6, operation.getTypeOf());
	      PreparedStatement.executeUpdate();
	 }

	 
	
//	 public static void virementComptes(Compte cpteADebiter, Compte cpteACrediter) {
//	       PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO operations VALUES (?,?,?,?)");
//	        PreparedStatement.setInt(1, operation.getNumero());
//	        PreparedStatement.setInt(2, operation.getCodeTypeCompte().getId());
//	        PreparedStatement.setInt(3, operation.getCodeTitulaire().getCode());
//	        PreparedStatement.setFloat(4, operation.getSolde());
//	        PreparedStatement.executeUpdate();
//	 }
	 
	public static void main(String[] args) throws SQLException{
		//TODO Dans compte modifier codeTitulaire en .getCode()
		// Récupérer l'id de getitulaire
		//Dans class Opérations changer attribut numcompte en Objet Compte + voir chgmt getOperationsByNumCompte
		// Voir le getCompteById , solution = resultat.next();
		
//		System.out.println( getTypedecompteById(3));
//		ajouterTypedecompte("ass vie");
		
//		supprimerTypedecompteById(9);
//		modifierTypedecompte(new Typedecompte(12, "ass vie 2"));
		
//		System.out.println(getOperationsByNumCompte(10001));
		
//		System.out.println(getCompteById(10001));
		
//		System.out.println(geTitulaire(1003));
//		creerTitulaire(new Titulaire(1006, "Thomas", "Cardon", "14 rue des croisettes", 35240));
//		modifierTutilaire(new Titulaire(1006, "Séverine", "Cardon", "14 rue des croisettes", 35240));
//		deleteTitulaire(1006);
//		System.out.println(getCompteById(10001));
//		createCompte(new Compte(10011 , getTypedecompteById(1), getTitulaireById(1001), 200));
//		operationSurCompte(6);
//		ajouterOperations(new Operations(0, getCompteById(1007),"2018-12-19", "Salaire", 1300, "+" ));
	
	}
}
