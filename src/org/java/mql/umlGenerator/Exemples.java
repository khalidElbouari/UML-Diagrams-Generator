package org.java.mql.umlGenerator;

import org.java.mql.umlGenerator.IHM.ProjetPanel;
import org.java.mql.umlGenerator.model.ProjetModel;
import org.java.mql.xml.ConsoleDisplay;
import org.java.mql.xml.ProjectParser;
import org.java.mql.xml.XMLGenerator;
import org.java.mql.xml.XMIGenerator;
import org.java.mql.xml.XMIParser;

public class Exemples {

	public Exemples() {
	      //displayPackageDiagram();
		  GenererXML(); 
		  parserXML(); 
		  consoleVerification();
		  GenererXMI();
		//  parserXMI();
		 
	
	}
	void displayPackageDiagram() {
	    Scanner scanner = new Scanner("resources.org.mql.model", "MyProject");
	    ProjetModel projetModel = scanner.getProjetModel();
	    ProjetPanel projetPanel= new ProjetPanel(projetModel);
	    projetPanel.displayPackageDiagram();  
	}
	
	
	void GenererXML() {
	    Scanner scanner = new Scanner("resources.org.mql.model", "MyProject");
	    ProjetModel projetModel = scanner.getProjetModel();
	    XMLGenerator generator = new XMLGenerator();
	    generator.generateXML(projetModel, "resources/data.xml");
	}
	void parserXML() {
	    ProjetModel parsedProject = ProjectParser.parseXML("resources/data.xml");
	    ProjetPanel projetPanel= new ProjetPanel(parsedProject);
	    projetPanel.displayPackageDiagram(); 	
	    }
	
	void consoleVerification() {
        ProjetModel projetModel = ProjectParser.parseXML("resources/data.xml");
        ConsoleDisplay display = new ConsoleDisplay();
        display.displayModel(projetModel);
	}
	void GenererXMI() {
	    Scanner scanner = new Scanner("resources.org.mql.model", "MyProject");
	    ProjetModel projetModel = scanner.getProjetModel();

	    XMIGenerator.generateXMI(projetModel, "resources/dataXMI.xml");
	}
	void parserXMI() {
		ProjetModel parsedProject = XMIParser.parseXMI("resources/dataXMI.xml");
	    ProjetPanel projetPanel= new ProjetPanel(parsedProject);
	    projetPanel.displayPackageDiagram(); 	
	    }
	
	public static void main(String[] args) {
		new Exemples();
	}
}
