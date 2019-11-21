package JardinCollectif.Objects;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Gestionnaire d'une connexion avec une BD relationnelle via JDBC.<br>
 * <br>
 * 
 * Cette classe ouvre une connexion avec une BD via JDBC.<br>
 * La m�thode serveursSupportes() indique les serveurs support�s.<br>
 * 
 * <pre>
 * Pr�-condition
 *   Le driver JDBC appropri� doit �tre accessible.
 * 
 * Post-condition
 *   La connexion est ouverte en mode autocommit false et s�rialisable, 
 *   (s'il est support� par le serveur).
 * </pre>
 * 
 * <br>
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * @author Marc Frappier - Universit� de Sherbrooke
 * @version Version 2.0 - 13 novembre 2004
 * 
 * 
 * @author Vincent Ducharme - Universit� de Sherbrooke
 * @version Version 3.0 - 21 mai 2016
 */
public class Connexion {

	private MongoClient client;
	private MongoDatabase database;

	/**
	 * Ouverture d'une connexion en mode autocommit false et s�rialisable (si
	 * support�)
	 * 
	 * @param serveur Le type de serveur SQL � utiliser (Valeur : local, dinf).
	 * @param bd      Le nom de la base de donn�es sur le serveur.
	 * @param user    Le nom d'utilisateur � utiliser pour se connecter � la base de
	 *                donn�es.
	 * @param pass    Le mot de passe associ� � l'utilisateur.
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
				"Ouverture de la connexion :\n" + "Connect� sur la BD ObjectDB " + bd + " avec l'utilisateur " + user);
	}

	/**
	 * Fermeture d'une connexion
	 */
	public void fermer() {
		client.close();
		System.out.println("Connexion ferm�e ");
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