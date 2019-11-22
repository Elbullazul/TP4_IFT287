package JardinCollectif.Objects;

import org.bson.Document;

public class Lot {

	private String name;
	private int maxMemb;

	public Lot() {
	}

	public Lot(Document d) {
		name = d.getString("nom");
		maxMemb = d.getInteger("max_collab");
	}

	public Lot(String nomlot, int nbmaxmembre) {
		name = nomlot;
		maxMemb = nbmaxmembre;
	}

	public String getnomlot() {
		return name;
	}

	public int getnbMembre() {
		return maxMemb;
	}
	
	public String toString() {
		return "Lot " + this.name + ", Nb max. membres: " + Integer.toString(this.maxMemb); 
	}

	public Document toDocument() {
		return new Document().append("nom", name).append("max_collab", maxMemb);
	}
	
	
}
