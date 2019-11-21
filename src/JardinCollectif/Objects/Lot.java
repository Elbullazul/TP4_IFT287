package JardinCollectif.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.bson.Document;

@Entity
public class Lot {
	 
	 @Id
	private String name;

	 private int maxMemb;
	

	public Lot() {
	
	}
	public Lot(Document d) {
		name = d.getString("nom");
		maxMemb= d.getInteger("max_collab");

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
	
	public Document toDocument() {
		return new Document().append("nom", name)
				.append("max_collab",maxMemb);
	}
	
}
