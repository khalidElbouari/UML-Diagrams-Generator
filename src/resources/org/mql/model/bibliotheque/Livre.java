package resources.org.mql.model.bibliotheque;

public class Livre {
    private String titre;
    private String auteur;
    private Adherent emprunteur; 

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setEmprunteur(Adherent emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Adherent getEmprunteur() {
        return emprunteur;
    }
    public String getAuteur() {
		return auteur;
	}
    public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
}
