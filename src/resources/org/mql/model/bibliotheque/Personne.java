package resources.org.mql.model.bibliotheque;
public abstract class Personne {
    protected String nom;
    protected String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}
