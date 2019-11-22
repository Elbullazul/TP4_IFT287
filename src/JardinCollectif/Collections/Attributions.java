package JardinCollectif.Collections;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import JardinCollectif.Connexion;
import JardinCollectif.Objects.Attribution;

public class Attributions {

	private MongoCollection<Document> attributionsCollection;
	private Connexion cx;

	public Attributions(Connexion connect) {
		this.cx = connect;
		attributionsCollection = cx.getDatabase().getCollection("Attributions");
	}

	/**
	 * Retourner la connexion associée.
	 */
	public Connexion getconnexion() {
		return cx;
	}

	public boolean existe(String nomlot, String id) {
		return attributionsCollection.find(and(eq("nomLot", nomlot), eq("idMembre", id))).first() != null;
	}

	public void ajouterAttribution(String nomemb, String nomlot) {

		Attribution a = new Attribution(nomemb, nomlot);
		attributionsCollection.insertOne(a.toDocument());
	}

	public int nbMembreLot(String nomlot) {
		int compteur = 0;
		MongoCursor<Document> membres = attributionsCollection.find(eq("nomLot", nomlot)).iterator();
		while (membres.hasNext()) {
			compteur++;
		}

		membres.close();
		return compteur;
	}

}
