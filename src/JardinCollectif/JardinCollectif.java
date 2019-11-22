// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package JardinCollectif;

import java.io.*;
import java.util.StringTokenizer;

import JardinCollectif.Gestionnaires.GestionJardinCollectif;

import java.sql.*;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class JardinCollectif {
	public static GestionJardinCollectif gestionJardin;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.out.println(
					"Usage: java JardinCollectif.JardinCollectif <serveur> <bd> <user> <password> [<fichier-transactions>]");
			return;
		}

		try {
			// Il est possible que vous ayez à déplacer la connexion ailleurs.
			// N'hésitez pas à le faire!
			gestionJardin = new GestionJardinCollectif(args[0], args[1], args[2], args[3]);

			BufferedReader reader = ouvrirFichier(args);
			String transaction = lireTransaction(reader);
			while (!finTransaction(transaction)) {
				executerTransaction(transaction);
				transaction = lireTransaction(reader);
			}
		} finally {
			gestionJardin.fermer();
		}
	}

	/**
	 * Decodage et traitement d'une transaction
	 */
	static void executerTransaction(String transaction) throws Exception, IFT287Exception {
		try {
			System.out.println(transaction);
			// Decoupage de la transaction en mots
			StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
			if (tokenizer.hasMoreTokens()) {
				String command = tokenizer.nextToken();
				// Vous devez remplacer la chaine "commande1" et "commande2" par
				// les commandes de votre programme. Vous pouvez ajouter autant
				// de else if que necessaire. Vous n'avez pas a traiter la
				// commande "quitter".
				if (command.equals("inscrireMembre")) {
					// Lire les parametres ici et appeler la bonne methode
					// de traitement pour la transaction
					String prenom = readString(tokenizer);
					String nom = readString(tokenizer);
					String password = readString(tokenizer);
					String nomembre = readString(tokenizer);

					gestionJardin.getGestionMembre().InscrireMembre(prenom, nom, password, nomembre);
				} else if (command.equals("supprimerMembre")) {
					String nomembre = readString(tokenizer);

					gestionJardin.getGestionMembre().supprimerMembre(nomembre);
				} else if (command.equals("afficherMembres")) {
					gestionJardin.getGestionMembre().AfficheMembres();
				} else if (command.equals("promouvoirAdministrateur")) {
					String nomembre = readString(tokenizer);

					gestionJardin.getGestionMembre().Promouvoir(nomembre);
				}
				// lots
				else if (command.equals("accepterDemande")) {
					String nomlot = readString(tokenizer);
					String nomembre = readString(tokenizer);

					gestionJardin.getGestionDemande().accepterDemande(nomlot, nomembre);
					gestionJardin.getGestionAttribution().rejoindreLot(nomlot, nomembre);
				} else if (command.equals("refuserDemande")) {
					String nomlot = readString(tokenizer);
					String nomembre = readString(tokenizer);

					gestionJardin.getGestionDemande().refuserDemande(nomlot, nomembre);
				} else if (command.equals("ajouterLot")) {
					String nomlot = readString(tokenizer);
					Integer maxcollabs = readInt(tokenizer);

					gestionJardin.getGestionLot().AjoutLot(nomlot, maxcollabs);
				} else if (command.equals("rejoindreLot")) {
					String nomlot = readString(tokenizer);
					String nomembre = readString(tokenizer);

					gestionJardin.getGestionDemande().AjouterDemande(nomlot, nomembre);
				} else if (command.equals("supprimerLot")) {
					String nomlot = readString(tokenizer);

					gestionJardin.getGestionLot().SupprimerLot(nomlot);
				} else if (command.equals("afficherLots")) {
					gestionJardin.getGestionLot().AfficheLots();
				} else if (command.equals("ajouterPlante")) {
					String nomlot = readString(tokenizer);
					Integer nbjours = readInt(tokenizer);

					gestionJardin.getGestionPlante().AjouterPlante(nomlot, nbjours);
				}

				else if (command.equals("planterPlante")) {
					String nomplante = readString(tokenizer);
					String nomlot = readString(tokenizer);
					String nomembre = readString(tokenizer);
					Integer nbexemplaires = readInt(tokenizer);
					Date dateplantation = readDate(tokenizer);

					gestionJardin.getGestionCulture().planterplante(nomplante, nomlot, nomembre, nbexemplaires,
							dateplantation);
				} else if (command.equals("recolterPlante")) {
					String nomplante = readString(tokenizer);
					String nomlot = readString(tokenizer);
					String nomembre = readString(tokenizer);

					gestionJardin.getGestionCulture().recolterplante(nomplante, nomlot, nomembre);
				} else if (command.equals("afficherPlantesLot")) {
					String nomlot = readString(tokenizer);

					gestionJardin.getGestionCulture().affichePlanteLot(nomlot);
				} else if (command.equals("retirerPlante")) {
					String nomplante = readString(tokenizer);

					gestionJardin.getGestionPlante().RetirerPlante(nomplante);
				} else if (command.equals("afficherPlantes")) {
					gestionJardin.getGestionPlante().Afficheplantes();
				} else {
					System.out.println(" : Transaction non reconnue");
				}
			}
		} catch (Exception e) {
			System.out.println(" " + e.toString());
			// Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de
			// la correction
			// automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici
			// dans un programme
			// fini et fonctionnel sans bogues.
			// cx.rollback();
		}
	}

	// ****************************************************************
	// * Les methodes suivantes n'ont pas besoin d'etre modifiees *
	// ****************************************************************

	/**
	 * Ouvre le fichier de transaction, ou lit à partir de System.in
	 */
	public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException {
		if (args.length < 5)
			// Lecture au clavier
			return new BufferedReader(new InputStreamReader(System.in));
		else
			// Lecture dans le fichier passe en parametre
			return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
	}

	/**
	 * Lecture d'une transaction
	 */
	static String lireTransaction(BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	/**
	 * Verifie si la fin du traitement des transactions est atteinte.
	 */
	static boolean finTransaction(String transaction) {
		// fin de fichier atteinte
		return (transaction == null || transaction.equals("quitter"));
	}

	/** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
	static String readString(StringTokenizer tokenizer) throws Exception {
		if (tokenizer.hasMoreElements())
			return tokenizer.nextToken();
		else
			throw new Exception("Autre parametre attendu");
	}

	/**
	 * Lecture d'un int java de la transaction entree a l'ecran
	 */
	static int readInt(StringTokenizer tokenizer) throws Exception {
		if (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			try {
				return Integer.valueOf(token).intValue();
			} catch (NumberFormatException e) {
				throw new Exception("Nombre attendu a la place de \"" + token + "\"");
			}
		} else
			throw new Exception("Autre parametre attendu");
	}

	static Date readDate(StringTokenizer tokenizer) throws Exception {
		if (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			try {
				return Date.valueOf(token);
			} catch (IllegalArgumentException e) {
				throw new Exception("Date dans un format invalide - \"" + token + "\"");
			}
		} else
			throw new Exception("Autre parametre attendu");
	}

}
