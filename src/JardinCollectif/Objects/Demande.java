package JardinCollectif.Objects;

import org.bson.Document;

public class Demande {
	private String idMembre;
	private String nomLot;
	private Integer status;
	
	public static Integer STATUS_PENDING = -1;
	public static Integer STATUS_DENIED = 0;
	public static Integer STATUS_APPROVED = 1;
	
	public Demande() {
	}
	
	public Demande(Document d) {
		idMembre = d.getString("idMembre");
		nomLot = d.getString("nomLot");
		status = d.getInteger("status");
	}	
	public Demande(String nomLot, String idMembre) {
		this.nomLot = nomLot;
		this.idMembre = idMembre;
		this.status = STATUS_PENDING;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String toString() {
		String s = "Demande soumise par: " + this.idMembre.toString() + " pour le lot " + this.nomLot + " Status: ";
		
		switch (this.status) {
		case -1:
			s += "Pending";
			break;
		case 0:
			s += "denied";
			break;
		case 1:
			s += "approved";
			break;
		}
		
		return s;
	}
	public Document toDocument() {
		return new Document().append("idMembre", idMembre)
				.append("nomLot", nomLot)
				.append("status",status);
	}
}

