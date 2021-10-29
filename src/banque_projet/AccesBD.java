package banque_projet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


/** 	on pourrait appeler cette classe GestionJDBC
 * 		son rôle consiste à gérer la connection à la Base
 * 		de données bd-avion ou une autre base de données MySQL.
 */
public class AccesBD {


	private static String utilisateur="root";
	private static String motDePasse="root";
	private static String pilote = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3308/banque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static Connection connexion=null;

	/**
	 * M�thode qui retourne un objet de type Connection
	 * @return Connection
	 */
	public synchronized static Connection getConnection()
	{
		try
		{
			Class.forName(pilote);
			if (connexion==null) connexion = DriverManager.getConnection(url, utilisateur, motDePasse);


		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
			JOptionPane.showMessageDialog(null,"Pilote non valide ou introuvable !","AccesBD",JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null,e.getMessage(),"Connexion � la Base MySQL Impossible !",JOptionPane.WARNING_MESSAGE);
			System.exit(-1);
		}

		return connexion;
	}


	/**
	 *	M�thode d'ex�cution d'une requ�te (SELECT) pour renvoyer un objet de type ResultSet
	 *  Cette m�thode existe sous le nom : executeQuery()
	 *	@param requete String
	 *	@return resultat ResultSet
	 */
	public static ResultSet executerQuery(String requete) throws SQLException

	{
		/*
		 * 	On d�clare un objet de type Statement que l'on nomme instruction. Cet
		 * 	objet soumet la requ�te � la base de donn�es dans MySQL.
		 * 	On d�clare un objet de type ResultSet que l'on nomme resultat. cet objet
		 * 	est retourn� pour encapsuler les r�sultats de la requ�te. Il va nous permettre
		 * 	de manipuler les r�sultats de la requ�te. Les afficher, les stocker dans des objets,...
		 *
		 */

		// D�claration de nos variables
		Statement statement = null;
		ResultSet resultat = null;

		try {
			/*
			 * Param�tres ajout� pour la gestion des curseurs pour la navigation dans un ResultSet
			 * TYPE_SCROLL_INSENSITIVE :
			 * Cette valeur indique que le curseur peut �tre d�plac� dans les deux sens,
			 * mais aussi arbitrairement (de mani�re absolue ou relative).
			 * Le terme insensitive indique que le ResultSet est insensible aux modifications
			 * des valeurs dans la base de donn�es. Cela d�finit en fait une vue statique des donn�es
			 * contenues dans le ResultSet.
			 *
			 * CONCUR_UPDATABLE :
			 * Cette valeur indique que l'on peut modifier les donn�es de la base via le ResultSet.
			 */

			int type = ResultSet.TYPE_SCROLL_SENSITIVE;
			int mode = ResultSet.CONCUR_UPDATABLE;

			/* 	On peut traduire Statement par ordre ou instruction.
			 * 	La m�thode createStatement() nous retourne un objet de type Statement.
			 * 	Nous l'avons appel� avec la m�thode getConnection() qui nous renvoie
			 * 	un objet de type Connection nomm� connexion.
			 * 	D�s lors, nous pouvons utiliser l'objet instruction pour interroger
			 * 	la base de donn�es.
			 *
			 */
			statement = getConnection().createStatement(type,mode);
			/*	Pour cela, il nous suffit d'appeler la m�thode executeQuery() en lui passant
			 * 	comme param�tre, la requete que nous voulons ex�cuter.
			 * 	L'objet resultat contient le r�sultat de l'ex�cution de la requ�te.
			 */
			resultat = statement.executeQuery(requete);

		}
		catch(SQLException sqle)
		{
			System.out.println("Probl�me dans la requ�te SQL !");
			sqle.printStackTrace();
		}
//		finally {
//			statement.close();
//			resultat.close();
//			connexion.close();
//		}
		return resultat; // retourne un ResultSet


	}

	/**
	 *	M�thode d'ex�cution d'une requete Update (UPDATE, INSERT, DELETE ou CREATE ou DROP).
	 *  Elle ne renvoie rien
	 *	@param requete String
	 */
	public static void executerUpdate(String requete) throws SQLException

	{
		Statement statement = null;
		try {

			statement = getConnection().createStatement();

			int i = statement.executeUpdate(requete);


			if (i==1) // on affiche un message d'information sur l'op�ration pour le plaisir !

			{
				JOptionPane.showMessageDialog(null, "L'op�ration a r�ussie !");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "L'op�ration a �chou� !");
			}

		}

		catch(SQLException sqle)
		{
			System.out.println("Probl�me dans la requ�te SQL !");
			sqle.printStackTrace();
		}
//		finally {
//
//			statement.close();
//			connexion.close();
//
//		}

	}

	/**
	 *
	 * @param unUtilisateur String
	 */
	public static void setUtilisateur(String unUtilisateur)
	{
		utilisateur=unUtilisateur;
	}
	/**
	 *
	 * @param unMotDePasse String
	 */
	public static void setMotDePasse(String unMotDePasse)
	{
		motDePasse=unMotDePasse;
	}

	/**
	 *
	 * @return
	 */
	public static String getMotDePasse() {
		return motDePasse;
	}
	/**
	 *
	 * @return
	 */
	public static String getPilote() {
		return pilote;
	}
	/**
	 *
	 * @return
	 */
	public static String getUrl() {
		return url;
	}
	/**
	 *
	 * @return
	 */
	public static String getUtilisateur() {
		return utilisateur;
	}

	/**
	 *  test de la connection avec m�thode main()
	 */

	public static void main(String[] args) throws SQLException
	{
		System.out.println("objet connexion = "+AccesBD.getConnection());
		if (AccesBD.connexion!=null)
		{
			JOptionPane.showMessageDialog(null, "�a marche");
			// on teste une requ�te :
				Statement st = AccesBD.connexion.createStatement();
				ResultSet resultat = st.executeQuery("SELECT * FROM titulaire ORDER BY nom");

				while(resultat.next())
				{
					System.out.print(resultat.getInt("code")+"  ");
					System.out.print(resultat.getString("prenom")+ "  ");
					System.out.print(resultat.getString("adresse")+ " ");
					System.out.println(resultat.getInt("codePostal"));
				}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "ça marche PAS !");
		}
	}

}