package resources.org.mql.model.school;


public abstract class Location {
    private String id;
    private String name;
    private String type;  
    private Adresse adresse;   

    public Location(String id, String name, String type2, Adresse adresse) {
        this.id = id;
        this.name = name;
        this.type = type2;
        this.adresse = adresse;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public abstract String additionalDetails();  

    @Override
    public String toString() {
        return "Location [id=" + id + ", name=" + name + ", type=" + type + ", adresse=" + adresse + "]";
    }
}
