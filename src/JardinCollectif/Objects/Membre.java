package JardinCollectif.Objects;
import org.bson.Document;

public class Membre {
	private String nomemb;

	private String fName;
	private String name;
	private String pw;
	private boolean isAdmin;

	public Membre() {
	}

	public Membre(Document d) {
		nomemb = d.getString("id");
		name = d.getString("nom");
		fName = d.getString("prenom");
		pw = d.getString("password");
		isAdmin = d.getBoolean("isadmin");
	}

	public Membre(String noMembre, String prenom, String nom, String mdp) {
		nomemb = noMembre;
		fName = prenom;
		name = nom;
		pw = mdp;
		isAdmin = false;
	}

	public String getnumMembre() {
		return nomemb;
	}

	public String getPrenom() {
		return fName;
	}

	public String getNom() {
		return name;
	}

	public String getdMDP() {
		return pw;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String toString() {
		String s = "";
		if (this.isAdmin)
			s += "[ADMIN] ";
		else
			s += "        ";

		s += "(" + this.nomemb.toString() + ") " + this.name + ", " + this.fName + " pw:" + this.pw;

		return s;
	}

	public Document toDocument() {
		return new Document().append("id", nomemb).append("nom", name).append("prenom", fName).append("password", pw)
				.append("isadmin", isAdmin);
	}
}
