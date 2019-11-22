package JardinCollectif.Objects;

import org.bson.Document;

public class Plante {

	private String name;
	private int timeG;

	public Plante() {
	}

	public Plante(Document d) {
		name = d.getString("nom");
		timeG = d.getInteger("duree");
	}

	public Plante(String nom, int tcult) {
		name = nom;
		timeG = tcult;
	}

	public String getnom() {
		return name;
	}

	public int gettcult() {
		return timeG;
	}
	
	public String toString() {
		return "Nom: " + this.name + "     Temps pour mûrir: " + Integer.toString(this.timeG) + " jours"; 
	}

	public Document toDocument() {
		return new Document().append("nom", name).append("duree", timeG);
	}
}
