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

	public GestionDemande(Lots lot, Membres membre, Demandes demande)throws Exception {
		if(lot.getConnexion() != membre.getconnexion() || lot.getConnexion() != demande.getconnexion())
		{
			throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
		}
		this.lots = lot;
		this.membres= membre;
		this.demandes = demande;
	}
	
	public void AjouterDemande(String nomlot,String noMembre )throws Exception {
		try {
	
	// verifie l'existence		
			if(!lots.existe(nomlot))
			{
			throw new IFT287Exception("Lot inexistant" + nomlot);	
			}
			if(!membres.existe(noMembre)) {
				throw new IFT287Exception("Membre inexistant" + noMembre );
			}
			
			if(demandes.existe(nomlot)) {
			throw new IFT287Exception("Demande existante");
			}		
			demandes.ajouterDemande(noMembre, nomlot);
				
			System.out.println("La demande de"+ noMembre + "pour le lot"+ nomlot+ "est soumise");
		}
		  catch (Exception e)
        {
            throw e;
        }
	}
	
	public void supprimerDemande(String nomlot,String noMembre )throws Exception {
		try {
			if(!demandes.existe(nomlot)) {
			throw new IFT287Exception("Demande inexistante");
			}		
			demandes.supprimerDemande(nomlot);
			System.out.println("Demande de lot" + nomlot + "supprimer par"+ noMembre);
		}
		  catch (Exception e)
        {
            throw e;
        }
	}
	
	
	public void accepterDemande(String nomlot, String noMembre)throws Exception {
		try {
			if(!lots.existe(nomlot)) {
				throw new IFT287Exception("Lot inexistant");
			}
			if(!membres.existe(noMembre)) {
				throw new IFT287Exception("Membre:"+ noMembre +" inexistant");
			}
			if(!membres.estAdmin(noMembre)) {
				throw new IFT287Exception(noMembre +"n'est pas Administrateur");
			}
			if(demandes.getStatus(nomlot) == status.STATUS_APPROVED)
			{throw new IFT287Exception(nomlot +"est deja approuvee");}
			
			if(demandes.getStatus(nomlot) == status.STATUS_DENIED)
			{throw new IFT287Exception(nomlot +"est deja Refusee");}
			
			demandes.updateStatus(nomlot,noMembre,status.STATUS_APPROVED);
			System.out.println("Demande approuvee");
	}
	catch(Exception e) {
		throw e;
		}
	}
	public void refuserDemande(String nomlot, String noMembre)throws Exception {
		try {
			if(!lots.existe(nomlot)) {
				throw new IFT287Exception("Lot inexistant");
			}
			if(!membres.existe(noMembre)) {
				throw new IFT287Exception("Membre:"+ noMembre +" inexistant");
			}
			if(!membres.estAdmin(noMembre)) {
				throw new IFT287Exception(noMembre +"n'est pas Administrateur");
			}
			if(demandes.getStatus(nomlot) == status.STATUS_APPROVED)
			{throw new IFT287Exception(nomlot +"est deja approuvee");}
			
			if(demandes.getStatus(nomlot) == status.STATUS_DENIED)
			{throw new IFT287Exception(nomlot +"est deja Refusee");}
			
			demandes.updateStatus(nomlot,noMembre,status.STATUS_DENIED);
			System.out.println("Demande refusee");
	}
	catch(Exception e) {
		throw e;
		}
	}
	
}
