package resources.org.mql.model.bibliotheque;

public class Bibliothecaire extends Personne implements LivreOperations {

    public Bibliothecaire(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public void ajouterLivre(Livre livre, Bibliotheque bibliotheque) {
        bibliotheque.getLivres().add(livre);
        System.out.println("Livre ajouté : " + livre.getTitre());
    }

    @Override
    public void supprimerLivre(Livre livre, Bibliotheque bibliotheque) {
        bibliotheque.getLivres().remove(livre);
        System.out.println("Livre supprimé : " + livre.getTitre());
    }
}
