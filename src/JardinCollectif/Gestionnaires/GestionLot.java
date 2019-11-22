package JardinCollectif.Gestionnaires;

import JardinCollectif.Connexion;
import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Lots;

public class GestionLot {
	Connexion cx;
	Lots lots;

	public GestionLot(Lots lot) throws Exception {

		this.cx = lot.getConnexion();
		this.lots = lot;
	}

	public void AjoutLot(String nomlot, int nbmaxmembre) throws Exception {
		try {
			if (nbmaxmembre <= 0) {
				throw new IFT287Exception("Nombre de Membre trop petit!");
			}
			if (lots.existe(nomlot)) {
				throw new IFT287Exception("Lot existe déjà");
			}

			lots.ajouterLot(nomlot, nbmaxmembre);
			System.out.println("Lot ajouté");

		} catch (Exception e) {
			throw e;
		}
	}

	public void SupprimerLot(String nomlot) throws Exception {
		try {
			if (!lots.existe(nomlot)) {
				throw new IFT287Exception("Lot inexistant");
			}
			lots.supprimerLot(nomlot);
			System.out.println("Lot supprimé");
		} catch (Exception e) {
			throw e;
		}

	}

	public void AfficheLots() throws Exception {
		try {
			if (lots.listerLot().isEmpty()) {
				throw new IFT287Exception("Aucun lot trouvé");
			}
			lots.afficherLot();
		} catch (Exception e) {
			throw e;
		}
	}
}
