package org.java.mql.umlGenerator;

import org.java.mql.umlGenerator.IHM.ProjetPanel;
import org.java.mql.umlGenerator.model.ProjetModel;
import org.mql.xml.ConsoleDisplay;
import org.mql.xml.ProjectParser;
import org.mql.xml.XMLGenerator;

public class Exemples {

	public Exemples() {
		displayPackageDiagram();
		GenererXMLFile();
		consoleVerification();
	}

	
	void displayPackageDiagram() {
	    Scanner scanner = new Scanner("resources.org.mql.model", "MyProject");
	    ProjetModel projetModel = scanner.getProjetModel();
	    ProjetPanel projetPanel= new ProjetPanel(projetModel);
	    projetPanel.displayPackageDiagram();  
	
	  
	}
	
	void GenererXMLFile() {
	    Scanner scanner = new Scanner("resources.org.mql.model", "MyProject");
	    ProjetModel projetModel = scanner.getProjetModel();
	    // Génération du fichier XML
	    XMLGenerator generator = new XMLGenerator();
	    generator.generateXML(projetModel, "resources/data.xml");
	}

	void parserXMLFile() {
	    ProjetModel parsedProject = ProjectParser.parseXML("resources/data.xml");
	    ProjetPanel projetPanel= new ProjetPanel(parsedProject);
	    projetPanel.displayPackageDiagram(); 	
	    }
	
	void consoleVerification() {
        ProjetModel projetModel = ProjectParser.parseXML("resources/data.xml");
        ConsoleDisplay display = new ConsoleDisplay();
        display.displayModel(projetModel);
	}
	
	public static void main(String[] args) {
		new Exemples();
	}
}
