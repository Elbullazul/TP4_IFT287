package JardinCollectif.Collections;

import static com.mongodb.client.model.Filters.eq;

import java.util.LinkedList;
import java.util.List;


import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import JardinCollectif.Connexion;
import JardinCollectif.Objects.Lot;

public class Lots {

	private MongoCollection<Document> lotsCollection;
	private Connexion cx;

	public Lots(Connexion connect) {
		this.cx = connect;
		lotsCollection = cx.getDatabase().getCollection("Lot");
	}

	/**
	 * Retourner la connexion associée.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si un Lot existe.
	 */
	public boolean existe(String name) {

		return lotsCollection.find(eq("nom", name)).first() != null;
	}

	/**
	 * Ajout d'un nouveau Lot.
	 */
	public void ajouterLot(String nom, int nbMax) {
		Lot l = new Lot(nom,nbMax);
		lotsCollection.insertOne(l.toDocument());
	}

	/**
	 * Suppression d'un lot.
	 */
	public boolean supprimerLot(String nom) {
		return lotsCollection.deleteOne(eq("nom", nom)).getDeletedCount() > 0;
	}

	/**
	 * Lister les lots
	 */
	public List<Lot> listerLot() {
		List<Lot> llots = new LinkedList<Lot>();
		MongoCursor<Document> lot = lotsCollection.find().iterator();
		try {
			while (lot.hasNext()) {
				llots.add(new Lot(lot.next()));
			}
		} finally {
			lot.close();
		}

		return llots;
	}

	public void afficherLot() {
		MongoCursor<Document> lot = lotsCollection.find().iterator();
		try {
			while (lot.hasNext()) {
				System.out.println(new Lot(lot.next()).toString());
			}
		} finally {
			lot.close();
		}
	}

}
