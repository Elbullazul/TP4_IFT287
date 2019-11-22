package JardinCollectif.Collections;

import static com.mongodb.client.model.Filters.eq;

import java.util.LinkedList;
import java.util.List;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import JardinCollectif.Connexion;
import JardinCollectif.Objects.Lot;
import JardinCollectif.Objects.Plante;

public class Plantes {

	private MongoCollection<Document> plantesCollection;
	private Connexion cx;

	public Plantes(Connexion connect) {
		this.cx = connect;
		plantesCollection = cx.getDatabase().getCollection("Plante");

	}

	public Connexion getconnexion() {
		return cx;
	}

	/**
	 * Verifie si un membre existe.
	 */
	public boolean existe(String name) {
		return plantesCollection.find(eq("nom", name)).first() != null;
	}

	/**
	 * 
	 */
	public Plante getPlante(String name) {
		Document d = plantesCollection.find(eq("nom", name)).first();
		if (d != null) {
			return new Plante(d);
		}
		return null;
	}

	public boolean supprimerPlante(String name) {
		return plantesCollection.deleteOne(eq("nom", name)).getDeletedCount() > 0;
	}

	public void ajouterPlante(String nom, int tempsG) {
		Plante p = new Plante(nom, tempsG);
		plantesCollection.insertOne(p.toDocument());
	}

	public List<Plante> listerPlante() {
		List<Plante> lplante = new LinkedList<Plante>();
		MongoCursor<Document> plante = plantesCollection.find().iterator();
		try {
			while (plante.hasNext()) {
				lplante.add(new Plante(plante.next()));
			}
		} finally {
			plante.close();
		}

		return lplante;
	}

	public void afficherPlante() {
		MongoCursor<Document> plante = plantesCollection.find().iterator();
		try {
			while (plante.hasNext()) {
				System.out.println(new Plante(plante.next()).toString());
			}
		} finally {
			plante.close();
		}
	}
}