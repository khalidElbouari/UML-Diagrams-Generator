package resources.org.mql.model.bibliotheque;

import java.util.List;

public class Bibliotheque {
	private String name ;
	private List<Livre> livres;
	public Bibliotheque(String name, List<Livre> livres) {
		super();
		this.name = name;
		this.livres = livres;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Livre> getLivres() {
		return livres;
	}
	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}
}