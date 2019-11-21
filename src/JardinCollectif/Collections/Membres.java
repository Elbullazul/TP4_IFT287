package JardinCollectif.Collections;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import JardinCollectif.Objects.Connexion;
import JardinCollectif.Objects.Membre;

public class Membres {

	private MongoCollection<Document> membresCollection;
	private Connexion cx;

	public Membres(Connexion connect) {
		this.cx = connect;
		membresCollection = cx.getDatabase().getCollection("Membre");
	}

	/**
	 * Retourner la connexion associée.
	 */
	public Connexion getconnexion() {
		return cx;
	}

	/**
	 * Verifie si un membre existe.
	 */
	public boolean existe(String nomemb) {
		return membresCollection.find(eq("id", nomemb)).first() != null;
	}

	/**
	 * Lecture d'un membre.
	 */
	public Membre getMembre(String nomemb) {
		Document d = membresCollection.find(eq("id", nomemb)).first();
		if (d != null) {
			return new Membre(d);
		}
		
		return null;
	}

	/**
	 * Ajout d'un nouveau membre.
	 */
	public void inscrire(String noMembre, String prenom, String nom, String mdp) {
		Membre p = new Membre(noMembre, prenom, nom, mdp);
		membresCollection.insertOne(p.toDocument());
	}

	/**
	 * Suppression d'un membre.
	 */
	public boolean desinscrire(String nuMemb) {
		return membresCollection.deleteOne(eq("id", nuMemb)).getDeletedCount() > 0;
	}

	public boolean estAdmin(String nuMemb) {
		Membre m = new Membre(membresCollection.find(eq("id", nuMemb)).first());

		if (m.getIsAdmin() == true) {
			return true;
		}
		return false;
	}

	public void updateAdmin(String nuMemb, boolean isAdmin) {
		membresCollection.updateOne(eq("id", nuMemb), set("isAdmin", isAdmin));
	}

	public List<Membre> afficherMembre() {
		List<Membre> lmembre = new LinkedList<Membre>();
		MongoCursor<Document> membre = membresCollection.find().iterator();
		try {
			while (membre.hasNext()) {
				System.out.println(new Membre(membre.next()).toString());
			}

		} finally {
			membre.close();
		}

		return lmembre;
	}

	public List<Membre> listerMembre() {
		List<Membre> lmembre = new LinkedList<Membre>();
		MongoCursor<Document> membre = membresCollection.find().iterator();
		try {
			while (membre.hasNext()) {
				lmembre.add(new Membre(membre.next()));
			}
		} finally {
			membre.close();
		}

		return lmembre;
	}
}
