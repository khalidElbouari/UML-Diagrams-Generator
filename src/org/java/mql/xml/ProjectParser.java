package org.java.mql.xml;
import java.io.File;


import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.java.mql.umlGenerator.enumeration.ClassType;
import org.java.mql.umlGenerator.enumeration.RelationType;
import org.java.mql.umlGenerator.model.ClassModel;
import org.java.mql.umlGenerator.model.PackageModel;
import org.java.mql.umlGenerator.model.ProjetModel;
import org.java.mql.umlGenerator.model.RelationModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class ProjectParser {

	public static ProjetModel parseXML(String filePath) {
	    ProjetModel projetModel = null;
	    try {
	        File file = new File(filePath);
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(file);

	        Element projectElement = (Element) document.getElementsByTagName("Project").item(0);
	        String projectName = projectElement.getAttribute("name");
	        projetModel = new ProjetModel(projectName, new ArrayList<>());

	        NodeList packageNodes = document.getElementsByTagName("Package");
	        for (int i = 0; i < packageNodes.getLength(); i++) {
	            Element packageElement = (Element) packageNodes.item(i);
	            String packageName = packageElement.getAttribute("name");
	            PackageModel packageModel = new PackageModel(packageName, new ArrayList<>());

	            NodeList classNodes = packageElement.getElementsByTagName("Class");
	            for (int j = 0; j < classNodes.getLength(); j++) {
	                Element classElement = (Element) classNodes.item(j);
	                String className = classElement.getAttribute("name");
	                String classTypeString = classElement.getAttribute("type");

	                ClassType classType = classTypeString != null && !classTypeString.isEmpty()
	                    ? ClassType.valueOf(classTypeString)
	                    : null;

	                List<String> methods = new ArrayList<>();
	                List<String> attributes = new ArrayList<>();
	                List<RelationModel> relations = new ArrayList<>();

	                // Extraction des méthodes
	                NodeList methodNodes = classElement.getElementsByTagName("Method");
	                for (int k = 0; k < methodNodes.getLength(); k++) {
	                    Element methodElement = (Element) methodNodes.item(k);
	                    methods.add(methodElement.getTextContent());
	                }

	                // Extraction des attributs
	                NodeList attributeNodes = classElement.getElementsByTagName("Attribute");
	                for (int l = 0; l < attributeNodes.getLength(); l++) {
	                    Element attributeElement = (Element) attributeNodes.item(l);
	                    attributes.add(attributeElement.getTextContent());
	                }

	                // Extraction des relations
	                NodeList relationNodes = classElement.getElementsByTagName("Relation");
	                for (int m = 0; m < relationNodes.getLength(); m++) {
	                    Element relationElement = (Element) relationNodes.item(m);
	                    String source = relationElement.getAttribute("source");
	                    String target = relationElement.getAttribute("target");
	                    RelationType type = RelationType.valueOf(relationElement.getAttribute("type"));
	                    relations.add(new RelationModel(source, target, type));
	                }

	                ClassModel classModel = new ClassModel(className, classType, methods, attributes, relations);
	                packageModel.getClasses().add(classModel);
	            }

	            projetModel.getPackages().add(packageModel);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return projetModel;
	}

	
}
