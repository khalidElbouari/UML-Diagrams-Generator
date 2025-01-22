package org.mql.xml;

import org.java.mql.umlGenerator.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import java.io.File;

public class XMLGenerator {

	public void generateXML(ProjetModel project, String filePath) {
	    try {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.newDocument();

	        Element projectElement = document.createElement("Project");
	        projectElement.setAttribute("name", project.getName());
	        document.appendChild(projectElement);

	        for (PackageModel pkg : project.getPackages()) {
	            Element packageElement = document.createElement("Package");
	            packageElement.setAttribute("name", pkg.getName());
	            projectElement.appendChild(packageElement);

	            for (ClassModel cls : pkg.getClasses()) {
	                Element classElement = document.createElement("Class");
	                classElement.setAttribute("name", cls.getName());

	                Element methodsElement = document.createElement("Methods");
	                for (String method : cls.getMethods()) {
	                    Element methodElement = document.createElement("Method");
	                    methodElement.setTextContent(method);
	                    methodsElement.appendChild(methodElement);
	                }
	                classElement.appendChild(methodsElement);

	                Element attributesElement = document.createElement("Attributes");
	                for (String attribute : cls.getAttributes()) {
	                    Element attributeElement = document.createElement("Attribute");
	                    attributeElement.setTextContent(attribute);
	                    attributesElement.appendChild(attributeElement);
	                }
	                classElement.appendChild(attributesElement);

	                Element relationsElement = document.createElement("Relations");
	                for (RelationModel relation : cls.getRelations()) {
	                    Element relationElement = document.createElement("Relation");
	                    relationElement.setAttribute("source", relation.getClassSourceName());
	                    relationElement.setAttribute("target", relation.getClassTargetName());
	                    relationElement.setAttribute("type", relation.getRelationType().name());
	                    relationsElement.appendChild(relationElement);
	                }
	                classElement.appendChild(relationsElement);

	                packageElement.appendChild(classElement);
	            }
	        }

	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);
	        StreamResult result = new StreamResult(new File(filePath));
	        transformer.transform(source, result);

	        System.out.println("Fichier XML généré avec succès : " + filePath);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}