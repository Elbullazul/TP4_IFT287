package JardinCollectif.Collections;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import JardinCollectif.Connexion;
import JardinCollectif.Objects.Culture;
import JardinCollectif.Objects.Membre;
import JardinCollectif.Objects.Plante;

public class Cultures {

	private MongoCollection<Document> culturesCollection;
	private Connexion cx;

	public Cultures(Connexion connect) {
		this.cx = connect;
		culturesCollection = cx.getDatabase().getCollection("Culture");
	}

	/**
	 * Retourner la connexion associée.
	 */
	public Connexion getconnexion() {
		return cx;
	}

	public boolean existe(String nomlot) {
		return culturesCollection.find(eq("nomLot", nomlot)).first() != null;
	}

	public void ajouterculture(String nomLot, String nomPlante, String idMembre, Integer nbExemplaires,
			Date datePlantation) {
		Culture c = new Culture(nomLot, nomPlante, idMembre, nbExemplaires, datePlantation);
		culturesCollection.insertOne(c.toDocument());
	}

	public boolean supprimer(String nomlot) {
		return culturesCollection.find(eq("nomLot", nomlot)).first() != null;
	}

	public void AfficherPlanteLot(String nomlot) {
		MongoCursor<Document> lot = culturesCollection.find(eq("nomLot", nomlot)).iterator();
		try {
			while (lot.hasNext()) {
				Culture c = new Culture(lot.next());
				System.out.println(c.toString());
			}
		} finally {
			lot.close();
		}
	}

	public List<Plante> listerPlante(String nomlot) {
		List<Plante> lplante = new LinkedList<Plante>();
		MongoCursor<Document> lot = culturesCollection.find(eq("nomLot", nomlot)).iterator();
		try {
			while (lot.hasNext()) {
				lplante.add(new Plante(lot.next()));
			}
		} finally {
			lot.close();
		}

		return lplante;
	}

	public List<Membre> listerMembre(String nomlot) {
		List<Membre> lmembre = new LinkedList<Membre>();
		MongoCursor<Document> membre = culturesCollection.find(eq("nomLot", nomlot)).iterator();
		try {
			while (membre.hasNext()) {
				lmembre.add(new Membre(membre.next()));
			}
		} finally {
			membre.close();
		}

		return lmembre;
	}

	public Date getdate(String nomLot, String nomplante) {
		Culture d = new Culture(culturesCollection.find(and(eq("nomLot", nomLot), eq("nomPlante", nomplante))).first());
		return d.getPlantee();
	}

	public int NbExemplaire(String nomLot, String nomplante) {
		Culture d = new Culture(culturesCollection.find(and(eq("nomLot", nomLot), eq("nomPlante", nomplante))).first());
		return d.getNbExemplaires();
	}

	public void updateNbExemplaire(String nomLot, String nomplante, int nbExmp) {
		culturesCollection.updateOne(and(eq("nomLot", nomLot), eq("nomPlante", nomplante)),
				set("ndExemplaires", nbExmp));
	}
	
}
