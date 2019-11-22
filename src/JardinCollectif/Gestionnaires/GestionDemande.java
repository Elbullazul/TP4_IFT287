package JardinCollectif.Gestionnaires;

import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Demandes;
import JardinCollectif.Collections.Lots;
import JardinCollectif.Collections.Membres;
import JardinCollectif.Objects.Demande;

public class GestionDemande {

	private Lots lots;
	private Membres membres;
	private Demandes demandes;
	private static Demande status;

	public GestionDemande(Lots lot, Membres membre, Demandes demande) throws Exception {
		if (lot.getConnexion() != membre.getconnexion() || lot.getConnexion() != demande.getconnexion()) {
			throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
		}
		this.lots = lot;
		this.membres = membre;
		this.demandes = demande;
	}

	public void AjouterDemande(String nomlot, String noMembre) throws Exception {
		try {
			// vérifie l'existence
			if (!lots.existe(nomlot)) {
				throw new IFT287Exception("Lot inexistant");
			}
			if (!membres.existe(noMembre)) {
				throw new IFT287Exception("Membre inexistant");
			}

			if (demandes.existe(nomlot, noMembre)) {
				throw new IFT287Exception("Demande déjà ouverte");
			}
			demandes.ajouterDemande(noMembre, nomlot);

			System.out.println("Demande soumise");
		} catch (Exception e) {
			throw e;
		}
	}

	public void supprimerDemande(String nomlot, String noMembre) throws Exception {
		try {
			if (!demandes.existe(nomlot, noMembre)) {
				throw new IFT287Exception("Demande inexistante");
			}
			demandes.supprimerDemande(nomlot, noMembre);
			System.out.println("Demande supprimée");
		} catch (Exception e) {
			throw e;
		}
	}

	public void accepterDemande(String nomlot, String noMembre) throws Exception {
		try {
			if (!lots.existe(nomlot)) {
				throw new IFT287Exception("Lot inexistant");
			}
			if (!membres.existe(noMembre)) {
				throw new IFT287Exception("Membre inexistant");
			}
			if (!demandes.existe(nomlot, noMembre)) {
				throw new IFT287Exception("Demande pas ouverte");
			}
			if (demandes.getStatus(nomlot, noMembre) != status.STATUS_PENDING) {
				throw new IFT287Exception(nomlot + " est deja traitée");
			}

			demandes.updateStatus(nomlot, noMembre, status.STATUS_APPROVED);
			System.out.println("Demande approuvée");
		} catch (Exception e) {
			throw e;
		}
	}

	public void refuserDemande(String nomlot, String noMembre) throws Exception {
		try {
			if (!lots.existe(nomlot)) {
				throw new IFT287Exception("Lot inexistant");
			}
			if (!membres.existe(noMembre)) {
				throw new IFT287Exception("Membre inexistant");
			}
			if (demandes.getStatus(nomlot, noMembre) != status.STATUS_PENDING) {
				throw new IFT287Exception(nomlot + " est deja traitée");
			}

			demandes.updateStatus(nomlot, noMembre, status.STATUS_DENIED);
			System.out.println("Demande refusée");
		} catch (Exception e) {
			throw e;
		}
	}

}
