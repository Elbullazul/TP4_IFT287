package JardinCollectif.Gestionnaires;

import JardinCollectif.Connexion;
import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Membres;
import JardinCollectif.Objects.Membre;

public class GestionMembre {

	Connexion cx;
	Membres membre;

	public GestionMembre(Membres membre) throws Exception {
		this.cx = membre.getconnexion();
		this.membre = membre;
	}

	public void InscrireMembre(String fName, String name, String pw, String nomemb) throws Exception {
		try {
			Membre m = new Membre(nomemb, fName, name, pw);

			// Vérifie si le membre existe déja
			if (membre.existe(nomemb))
				throw new IFT287Exception("Membre existe déjà");
			
			if (pw.length() < 8)
				throw new IFT287Exception("Mot de passe trop court. Longueur minimum de 8 caractères");

			// Ajout du membre.
			membre.inscrire(nomemb, fName, name, pw);
			System.out.println("Membre inscrit");

		} catch (Exception e) {
			throw e;
		}
	}

	public void supprimerMembre(String nomemb) throws Exception {
		try {
			// Vérifie si le membre existe et son nombre de pret en cours
			if (!membre.existe(nomemb))
				throw new IFT287Exception("Membre inexistant");

			// Suppression du membre
			membre.desinscrire(nomemb);
			System.out.println("Membre retiré");

		} catch (Exception e) {
			throw e;
		}
	}

	public void Promouvoir(String nomemb) throws Exception {
		try {
			if (!membre.existe(nomemb)) {
				throw new IFT287Exception("Membre inexistant");
			}

			if (membre.estAdmin(nomemb)) {
				throw new IFT287Exception("Le membre est déjà un admin");
			}

			membre.updateAdmin(nomemb, true);
			System.out.println(nomemb + " promu admin");

		} catch (Exception e) {
			throw e;
		}
	}

	public void AfficheMembres() throws Exception {
		try {
			if (membre.listerMembre().isEmpty()) {
				throw new IFT287Exception("Aucun membre trouvé");
			}
			membre.afficherMembre();

		} catch (Exception e) {
			throw e;
		}
	}

}
