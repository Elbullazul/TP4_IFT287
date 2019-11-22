package JardinCollectif.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.bson.Document;

@Entity
public class Plante {
	
	  @Id
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

public int gettcult () {
	return timeG;
}

public String toString() {
	String s = "";
	s = "( " + this.name + " )  " + this.timeG;
	return s;
	
}

public Document toDocument() {
	return new Document().append("nom",name)
			.append("duree",timeG);
	
}

	
}


