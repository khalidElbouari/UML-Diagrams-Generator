package resources.org.mql.model.school;

public class Adresse {
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;

    public Adresse(String rue, String ville, String codePostal, String pays) {
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getPays() {
        return pays;
    }

    @Override
    public String toString() {
        return rue + ", " + ville + ", " + codePostal + ", " + pays;
    }
}
