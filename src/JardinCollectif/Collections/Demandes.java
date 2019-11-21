package JardinCollectif.Collections;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import JardinCollectif.Objects.Connexion;
import JardinCollectif.Objects.Demande;

public class Demandes {

	private MongoCollection<Document> demandesCollection;
	private Connexion cx;

	public Demandes(Connexion connect) {
		this.cx = connect;
		demandesCollection = cx.getDatabase().getCollection("Demande");
	}

	/**
	 * Retourner la connexion associée.
	 */
	public Connexion getconnexion() {
		return cx;
	}

	public boolean existe(String nomlot) {
		return demandesCollection.find(eq("nomLot", nomlot)).first() != null;
	}

	public boolean supprimerDemande(String nomlot) {
		return demandesCollection.deleteOne(eq("nomLot", nomlot)).getDeletedCount() > 0;
	}

	public void ajouterDemande(String id, String nomlot) {
		Demande d = new Demande(id, nomlot);
		demandesCollection.insertOne(d.toDocument());
	}

	public void updateStatus(String nomlot, String noMembre, int status) {
		demandesCollection.updateOne(and(eq("nomLot", nomlot), eq("idMembre", noMembre)), set("status", status));
	}

	public int getStatus(String nomlot) {
		Demande d = new Demande(demandesCollection.find(eq("nomLot", nomlot)).first());
		return d.getStatus();
	}

}
