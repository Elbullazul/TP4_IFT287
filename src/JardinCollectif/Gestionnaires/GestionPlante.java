package JardinCollectif.Gestionnaires;

import JardinCollectif.IFT287Exception;
import JardinCollectif.Collections.Plantes;
import JardinCollectif.Objects.Connexion;

public class GestionPlante {
	Connexion cx;
	Plantes plantes;


	public GestionPlante(Plantes plante) {
		this.cx = plante.getconnexion();
		this.plantes = plante;
	}

	public void AjouterPlante(String nom, int jour)throws Exception {
		try {
	

			if(plantes.existe(nom)) {
				throw new IFT287Exception("plante deja existante.");

			}
			if(jour<= 0) {
				throw new IFT287Exception("Periode de germination trop court.");
			}
			plantes.ajouterPlante(nom, jour);

		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public void RetirerPlante(String nom)throws Exception {
		try {
			if (!plantes.existe(nom))
			{
				throw new IFT287Exception("plante inexistante.");
			}
			plantes.supprimerPlante(nom);
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	public void Afficheplantes()throws Exception {
		try {

			if(plantes.listerPlante().isEmpty()) {

				throw new IFT287Exception("Aucune plante existe");
			}

			plantes.afficherPlante();
		}
		catch (Exception e)
		{

			throw e;
		}

	}

}
