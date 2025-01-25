package resources.org.mql.model.bibliotheque;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
    }

    public List<Livre> getLivres() {
        return livres;
    }
}
