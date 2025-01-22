package resources.org.mql.model.bibliotheque;


public class Livre{
	 private Auteur auteur;
	 private Bibliotheque bibliotheque;
	
	 // Constructeur, getters, setters, etc.
	 public Livre(Auteur auteur, Bibliotheque bibliotheque) {
	     this.auteur = auteur;
	     this.bibliotheque = bibliotheque;
	 }
	
	 public Auteur getAuteur() {
	     return auteur;
	 }
	
	 public Bibliotheque getBibliotheque() {
	     return bibliotheque;
	 }

}

