package resources.org.mql.model.bibliotheque;


public class Livre{
	 private Auteur auteur;
	
	 // Constructeur, getters, setters, etc.
	 public Livre(Auteur auteur, Bibliotheque bibliotheque) {
	     this.auteur = auteur;
	 }
	
	 public Auteur getAuteur() {
	     return auteur;
	 }
	
	

}

