package JardinCollectif.Objects;

import javax.persistence.Entity;
import org.bson.Document;

public class Attribution {
	private String idMembre;
	private String nomLot;

	public Attribution() {
	}

	public Attribution(Document doc) {
		idMembre = doc.getString("idMembre");
		nomLot = doc.getString("nomLot");
	}

	public Attribution(String noMembre, String nomLot) {
		this.idMembre = noMembre;
		this.nomLot = nomLot;
	}

	public String getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(String idMembre) {
		this.idMembre = idMembre;
	}

	public String getNomLot() {
		return nomLot;
	}

	public void setNomLot(String nomLot) {
		this.nomLot = nomLot;
	}

	public String toString() {
		return "Membre " + this.idMembre.toString() + " collabore sur le lot " + this.nomLot;
	}

	public Document toDocument() {

		return new Document().append("idMembre", idMembre).append("nomLot", nomLot);
	}
}
