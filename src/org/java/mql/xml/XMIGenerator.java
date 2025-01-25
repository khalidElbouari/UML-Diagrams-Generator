package org.java.mql.xml;



import org.java.mql.umlGenerator.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileOutputStream;

public class XMIGenerator {

    public static void generateXMI(ProjetModel project, String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Élément racine XMI
            Element xmiElement = document.createElement("XMI");
            xmiElement.setAttribute("xmi.version", "2.1");
            xmiElement.setAttribute("xmlns:xmi", "http://www.omg.org/spec/XMI");
            xmiElement.setAttribute("xmlns:uml", "http://www.omg.org/spec/UML");
            document.appendChild(xmiElement);

            // Modèle UML (racine)
            Element modelElement = document.createElement("uml:Model");
            modelElement.setAttribute("name", project.getName());
            xmiElement.appendChild(modelElement);

            // Parcourir les packages
            for (PackageModel pkg : project.getPackages()) {
                Element packageElement = document.createElement("packagedElement");
                packageElement.setAttribute("xmi:type", "uml:Package");
                packageElement.setAttribute("name", pkg.getName());
                modelElement.appendChild(packageElement);

                // Parcourir les classes
                for (ClassModel cls : pkg.getClasses()) {
                    Element classElement = document.createElement("packagedElement");
                    classElement.setAttribute("xmi:type", "uml:Class");
                    classElement.setAttribute("name", cls.getName());
                    packageElement.appendChild(classElement);

                    // Attributs
                    for (String attribute : cls.getAttributes()) {
                        Element attributeElement = document.createElement("ownedAttribute");
                        attributeElement.setAttribute("name", attribute);
                        classElement.appendChild(attributeElement);
                    }

                    // Méthodes
                    for (String method : cls.getMethods()) {
                        Element operationElement = document.createElement("ownedOperation");
                        operationElement.setAttribute("name", method);
                        classElement.appendChild(operationElement);
                    }

                    // Relations
                    for (RelationModel relation : cls.getRelations()) {
                        Element relationElement = document.createElement("ownedRelation");
                        relationElement.setAttribute("source", relation.getClassSourceName());
                        relationElement.setAttribute("target", relation.getClassTargetName());
                        relationElement.setAttribute("type", relation.getRelationType().name());
                        classElement.appendChild(relationElement);
                    }
                }
            }
            

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream output = new FileOutputStream(filePath);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);

            System.out.println("XMI file generated successfully at " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}