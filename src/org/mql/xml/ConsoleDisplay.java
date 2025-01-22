package org.mql.xml;


import org.java.mql.umlGenerator.model.ClassModel;
import org.java.mql.umlGenerator.model.PackageModel;
import org.java.mql.umlGenerator.model.ProjetModel;
import org.java.mql.umlGenerator.model.RelationModel;

public class ConsoleDisplay {

	  public void displayModel(ProjetModel projetModel) {
	        if (projetModel != null) {
	            System.out.println("Projet : " + projetModel.getName());
	            
	            for (PackageModel pkg : projetModel.getPackages()) {
	                System.out.println("  Package : " + pkg.getName());
	                for (ClassModel cls : pkg.getClasses()) {
	                    System.out.println("\tClass : " + cls.getName());
	                    
	                    System.out.println("\t\tMéthodes : ");
	                    for (String method : cls.getMethods()) {
	                        System.out.println("\t\t\t "+ method);
	                    }

	                    System.out.println("\t\tAttributs : ");
	                    for (String attribute : cls.getAttributes()) {
	                        System.out.println("\t\t\t" + attribute);
	                    }

	                    System.out.println("\t\tRelations : ");
	                    for (RelationModel relation : cls.getRelations()) {
	                        System.out.println("\t\t\tSource : " + relation.getClassSourceName() +
	                                           ", Cible : " + relation.getClassTargetName() +
	                                           ", Type : " + relation.getRelationType());
	                    }
	                }
	            }
	        } else {
	            System.out.println("Le modèle est vide ou n’a pas pu être chargé.");
	        }
	    }

}
