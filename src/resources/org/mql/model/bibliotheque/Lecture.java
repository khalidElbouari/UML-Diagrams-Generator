package resources.org.mql.model.bibliotheque;

public class Lecture {
	 private Livre livre;
	 public Lecture(Livre livre) {
	     this.livre = livre;
	 }
	
	 public Livre getLivre() {
	     return livre;
	 }
}
