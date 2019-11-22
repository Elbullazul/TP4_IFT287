package JardinCollectif;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;



/**
 * Gestionnaire d'une connexion avec une BD relationnelle via JDBC.<br>
 * <br>
 * 
 * Cette classe ouvre une connexion avec une BD via JDBC.<br>
 * La méthode serveursSupportes() indique les serveurs supportés.<br>
 * 
 * <pre>
 * Pré-condition
 *   Le driver JDBC approprié doit étre accessible.
 * 
 * Post-condition
 *   La connexion est ouverte en mode autocommit false et sérialisable, 
 *   (s'il est supporté par le serveur).
 * </pre>
 * 
 * <br>
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * @author Marc Frappier - Université de Sherbrooke
 * @version Version 2.0 - 13 novembre 2004
 * 
 * 
 * @author Vincent Ducharme - Université de Sherbrooke
 * @version Version 3.0 - 21 mai 2016
 */
public class Connexion {

	private MongoClient client;
	private MongoDatabase database;

	/**
	 * Ouverture d'une connexion en mode autocommit false et sérialisable (si
	 * supporté)
	 * 
	 * @param serveur Le type de serveur SQL é utiliser (Valeur : local, dinf).
	 * @param bd      Le nom de la base de données sur le serveur.
	 * @param user    Le nom d'utilisateur é utiliser pour se connecter é la base de
	 *                données.
	 * @param pass    Le mot de passe associé é l'utilisateur.
	 */
	public Connexion(String serveur, String bd, String user, String pass) throws Exception {
		if (serveur.equals("local")) {
			client = new MongoClient();
		} else if (serveur.equals("dinf")) {
			MongoClientURI uriclient = new MongoClientURI(
					"mongodb://" + user + ":" + pass + "@bd-info2.dinf.usherbrooke.ca:27017/" + bd + "?ssl=false");
			client = new MongoClient(uriclient);
		} else {
			throw new Exception("Serveur inconnu");
		}

		database = client.getDatabase(bd);

		System.out.println(
				"Ouverture de la connexion :\n" + "Connecté sur la BD ObjectDB " + bd + " avec l'utilisateur " + user);
	}

	/**
	 * Fermeture d'une connexion
	 */
	public void fermer() {
		client.close();
		System.out.println("Connexion fermée ");
	}

	/**
	 * retourne la Connection ObjectDB
	 */
	public MongoClient getConnection() {
		return client;
	}

	/**
	 * retourne la DataBase MongoDB
	 */
	public MongoDatabase getDatabase() {
		return database;
	}

}