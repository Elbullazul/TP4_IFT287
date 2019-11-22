package JardinCollectif.Gestionnaires;

import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Attributions;
import JardinCollectif.Collections.Lots;
import JardinCollectif.Collections.Membres;

;

public class GestionAttribution {

	private Lots lots;
	private Membres membres;
	private Attributions attributions;

	public GestionAttribution(Lots lot, Membres membre, Attributions attribution) throws Exception {
		if (lot.getConnexion() != membre.getconnexion() || attribution.getconnexion() != membre.getconnexion()) {
			throw new IFT287Exception("Les collections d'objets n'utilisent pas la meme connexion au serveur");
		}
		this.lots = lot;
		this.membres = membre;
		this.attributions = attribution;
	}

	public void rejoindreLot(String nomlot, String id) throws Exception {
		try {
			if (!lots.existe(nomlot)) {
				throw new IFT287Exception("Lot inexistant");
			}
			if (!membres.existe(id)) {
				throw new IFT287Exception("Le Membre " + id + " est inexistant");
			}
			if (attributions.existe(nomlot, id)) {
				throw new IFT287Exception("Le Membre " + id + " fait deja partie du lot");
			}
			attributions.ajouterAttribution(id, nomlot);
			System.out.println(id +" à rejoint le lot "+ nomlot);
		} catch (Exception e) {
			throw e;
		}

	}
}