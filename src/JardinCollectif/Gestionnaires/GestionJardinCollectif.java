package JardinCollectif.Gestionnaires;

import JardinCollectif.Collections.Attributions;
import JardinCollectif.Collections.Cultures;
import JardinCollectif.Collections.Demandes;
import JardinCollectif.Collections.Lots;
import JardinCollectif.Collections.Membres;
import JardinCollectif.Collections.Plantes;
import JardinCollectif.Objects.Connexion;

public class GestionJardinCollectif {

	public Connexion cx;

	public GestionMembre gestionMembre;
	public GestionLot gestionLot;
	public GestionPlante gestionPlante;
	public GestionDemande gestionDemande;
	public GestionAttribution gestionAttribution;
	public GestionCulture gestionCulture;

	private Attributions attributions;
	private Cultures cultures;
	private Demandes demandes;
	private Lots lots;
	private Membres membres;
	private Plantes plantes;

	public GestionJardinCollectif(String serveur, String bd, String user, String password) throws Exception {
		cx = new Connexion(serveur, bd, user, password);

		lots = new Lots(cx);
		plantes = new Plantes(cx);
		membres = new Membres(cx);
		cultures = new Cultures(cx);
		demandes = new Demandes(cx);
		attributions = new Attributions(cx);

		setGestionLot(new GestionLot(lots));
		setGestionPlante(new GestionPlante(plantes));
		setGestionMembre(new GestionMembre(membres));
		setGestionCulture(new GestionCulture(lots, plantes, membres, cultures, attributions));
		setGestionDemande(new GestionDemande(lots, membres, demandes));
		setGestionAttribution(new GestionAttribution(lots, membres, attributions));		
	}

	public void fermer() {
		// fermer la connexion
		if (cx != null)
			  cx.fermer();
	}

	public GestionMembre getGestionMembre() {
		return gestionMembre;
	}

	public void setGestionMembre(GestionMembre gestionMembre) {
		this.gestionMembre = gestionMembre;
	}

	public GestionLot getGestionLot() {
		return gestionLot;
	}

	public void setGestionLot(GestionLot gestionLot) {
		this.gestionLot = gestionLot;
	}

	public GestionPlante getGestionPlante() {
		return gestionPlante;
	}

	public void setGestionPlante(GestionPlante gestionPlante) {
		this.gestionPlante = gestionPlante;
	}

	public GestionDemande getGestionDemande() {
		return gestionDemande;
	}

	public void setGestionDemande(GestionDemande gestionDemande) {
		this.gestionDemande = gestionDemande;
	}

	public GestionAttribution getGestionAttribution() {
		return gestionAttribution;
	}

	public void setGestionAttribution(GestionAttribution gestionAttribution) {
		this.gestionAttribution = gestionAttribution;
	}

	public GestionCulture getGestionCulture() {
		return gestionCulture;
	}

	public void setGestionCulture(GestionCulture gestionCulture) {
		this.gestionCulture = gestionCulture;
	}
}
