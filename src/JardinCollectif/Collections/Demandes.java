package JardinCollectif.Collections;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import JardinCollectif.Connexion;
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

	public boolean existe(String nomlot, String idmembre) {
		return demandesCollection.find(and(eq("nomLot", nomlot), eq("idMembre", idmembre))).first() != null;
	}

	public boolean supprimerDemande(String nomlot, String idmembre) {
		return demandesCollection.deleteOne(and(eq("nomLot", nomlot), eq("idMembre", idmembre))).getDeletedCount() > 0;
	}

	public void ajouterDemande(String id, String nomlot) {
		Demande d = new Demande(nomlot, id);
		demandesCollection.insertOne(d.toDocument());
	}

	public void updateStatus(String nomlot, String noMembre, int status) {
		demandesCollection.updateOne(and(eq("nomLot", nomlot), eq("idMembre", noMembre)), set("status", status));
	}

	public int getStatus(String nomlot, String noMembre) {
		BasicDBObject query = new BasicDBObject();
		query.append("nomLot", nomlot);
		query.append("idMembre",noMembre);
		
		Demande d = new Demande(demandesCollection.find(query).first());
		return d.getStatus();
	}

}
