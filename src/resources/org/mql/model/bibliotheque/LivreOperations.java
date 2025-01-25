package resources.org.mql.model.bibliotheque;

public interface LivreOperations {
    void ajouterLivre(Livre livre, Bibliotheque bibliotheque);
    void supprimerLivre(Livre livre, Bibliotheque bibliotheque);
}
