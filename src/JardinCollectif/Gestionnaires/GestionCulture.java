package JardinCollectif.Gestionnaires;

import java.util.Date;

import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Attributions;
import JardinCollectif.Collections.Cultures;
import JardinCollectif.Collections.Lots;
import JardinCollectif.Collections.Membres;
import JardinCollectif.Collections.Plantes;

public class GestionCulture {

	private Lots lots;
	private Plantes plantes;
	private Membres membres;
	private Cultures cultures;
	private Attributions attributions;

	public GestionCulture(Lots lot, Plantes plante, Membres membre, Cultures culture, Attributions attribution)
			throws Exception {

		if (lot.getConnexion() != plante.getconnexion() || lot.getConnexion() != attribution.getconnexion()
				|| membre.getconnexion() != culture.getconnexion() || membre.getconnexion() != lot.getConnexion()) {
			throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
		}
		this.lots = lot;
		this.plantes = plante;
		this.cultures = culture;
		this.membres = membre;
		this.attributions = attribution;

	}

	public void planterplante(String nomPlante, String nomLot, String noMembre, int nombreExemplaire, Date date)
			throws Exception {
		try {
			if (!plantes.existe(nomPlante)) {
				throw new IFT287Exception("Plante inexistante:" + nomPlante);
			}
			if (!lots.existe(nomLot)) {
				throw new IFT287Exception("Lots Inexistant:" + nomLot);
			}
			if (!membres.existe(noMembre)) {
				throw new IFT287Exception("Membre inexistant" + noMembre);
			}
			if (!attributions.existe(nomLot, noMembre)) {
				throw new IFT287Exception("Membre:" + noMembre + "ne fait pas partie de" + nomLot);
			}
			cultures.ajouterculture(nomLot, nomPlante, nomLot, nombreExemplaire, date);
		} catch (Exception e) {
			throw e;
		}
	}

	public void recolterplante(String nomPlante, String nomLot, String id) throws Exception {
		try {
			if (!plantes.existe(nomPlante)) {
				throw new IFT287Exception("Plante inexistante:" + nomPlante);
			}
			if (!lots.existe(nomLot)) {
				throw new IFT287Exception("Lots Inexistant:" + nomLot);
			}
			if (!membres.existe(id)) {
				throw new IFT287Exception("Membre inexistant" + id);
			}
			if (!attributions.existe(nomLot, id)) {
				throw new IFT287Exception("Membre:" + id + "ne fait pas partie de" + nomLot);

			}
		} catch (Exception e) {
			throw e;
		}
	}

}