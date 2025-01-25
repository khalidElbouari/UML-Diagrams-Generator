package resources.org.mql.model.bibliotheque;

import java.util.ArrayList;
import java.util.List;

public class Adherent extends Personne {
    private List<Livre> livresEmpruntes;

    public Adherent(String nom, String prenom) {
        super(nom, prenom);
        this.livresEmpruntes = new ArrayList<>();
    }

    public void emprunterLivre(Livre livre) {
        livresEmpruntes.add(livre);
        livre.setEmprunteur(this); // Association
    }

    public List<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }
}
