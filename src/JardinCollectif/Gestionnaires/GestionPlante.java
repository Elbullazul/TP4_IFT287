package JardinCollectif.Gestionnaires;

import JardinCollectif.Connexion;
import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Plantes;

public class GestionPlante {
	Connexion cx;
	Plantes plantes;

	public GestionPlante(Plantes plante) {
		this.cx = plante.getconnexion();
		this.plantes = plante;
	}

	public void AjouterPlante(String nom, int jour) throws Exception {
		try {
			if (plantes.existe(nom)) {
				throw new IFT287Exception("Le plante existe déjà");
			}
			if (jour <= 0) {
				throw new IFT287Exception("Période de germination doit être supérieur à 0");
			}
			plantes.ajouterPlante(nom, jour);

		} catch (Exception e) {
			throw e;
		}
	}

	public void RetirerPlante(String nom) throws Exception {
		try {
			if (!plantes.existe(nom)) {
				throw new IFT287Exception("Plante inexistante");
			}
			plantes.supprimerPlante(nom);
		} catch (Exception e) {
			throw e;
		}
	}

	public void Afficheplantes() throws Exception {
		try {
			if (plantes.listerPlante().isEmpty()) {
				throw new IFT287Exception("Aucune plante trouvée");			}

			plantes.afficherPlante();
		} catch (Exception e) {
			throw e;
		}
	}

}
