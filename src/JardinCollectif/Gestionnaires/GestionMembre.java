package JardinCollectif.Gestionnaires;

import java.util.ArrayList;
import java.util.List;

import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Membres;
import JardinCollectif.Objects.Connexion;
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
				throw new IFT287Exception("Membre existe déjà: " + nomemb);

			// Ajout du membre.
			membre.inscrire(nomemb, fName, name, pw);

		} catch (Exception e) {
			throw e;
		}
	}

	public void supprimerMembre(String nomemb) throws Exception {
		try {

			// Vérifie si le membre existe et son nombre de pret en cours
			if (!membre.existe(nomemb))
				throw new IFT287Exception("Membre inexistant: " + nomemb);

			// Suppression du membre
			membre.desinscrire(nomemb);
			System.out.println("Le Membre:" + nomemb + "est Supprim�");

		} catch (Exception e) {

			throw e;
		}
	}

	public void Promouvoir(String nomemb) throws Exception {
		try {

			if (membre.existe(nomemb)) {
				throw new IFT287Exception("Membre inexistant: " + nomemb);
			}

			if (!membre.estAdmin(nomemb)) {
				throw new IFT287Exception(nomemb + "Est un admin");
			}

		} catch (Exception e) {

			throw e;
		}
	}

	public void AfficheMembres() throws Exception {
		try {
			if (membre.listerMembre() == null) {
				throw new IFT287Exception("Aucun Membre trouver");
			}
			membre.afficherMembre(); // v�rification ?

		} catch (Exception e) {

			throw e;
		}
	}

}
