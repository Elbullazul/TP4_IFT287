package JardinCollectif.Gestionnaires;

import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Lots;
import JardinCollectif.Objects.Connexion;

public class GestionLot {
	
	Connexion cx;
	Lots lots;

	public GestionLot(Lots lot) throws Exception {
	
		this.cx = lot.getConnexion();
		this.lots = lot;
		
	}
	
	public void AjoutLot(String nomlot, int nbmaxmembre)throws Exception {
		try {
			if(nbmaxmembre <= 0) {
				throw new IFT287Exception("Nombre de Membre trop petit!");
			}
			if(lots.existe(nomlot)) {
				throw new IFT287Exception("Lot deja existant.");
			}
		}
		 catch (Exception e) {
			throw e;
		}
	}
	
	public void SupprimerLot(String nomlot)throws Exception {
		try {
		if(!lots.existe(nomlot)) {
			throw new IFT287Exception(" lot inexistant."+nomlot);	
		}
		lots.supprimerLot(nomlot);
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
	public void AfficheLots()throws Exception {
		try {
		if(lots.listerLot() == null) {
			throw new IFT287Exception("Liste de lot null.");	
			
		}
		lots.afficherLot();
	}
		catch(Exception e) {
		throw e;}
		}
}
