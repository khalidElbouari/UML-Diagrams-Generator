package org.java.mql.xml;


import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.java.mql.umlGenerator.enumeration.RelationType;
import org.java.mql.umlGenerator.model.ClassModel;
import org.java.mql.umlGenerator.model.PackageModel;
import org.java.mql.umlGenerator.model.ProjetModel;
import org.java.mql.umlGenerator.model.RelationModel;
import org.w3c.dom.*;

public class XMIParser {

    public static ProjetModel parseXMI(String filePath) {
        ProjetModel projetModel = null;
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            // Trouver l'élément racine (uml:Model)
            Element modelElement = (Element) document.getElementsByTagName("uml:Model").item(0);
            String projectName = modelElement.getAttribute("name");
            projetModel = new ProjetModel(projectName, new ArrayList<>());

            // Parcourir les packages
            NodeList packageNodes = document.getElementsByTagName("packagedElement");
            for (int i = 0; i < packageNodes.getLength(); i++) {
                Element packageElement = (Element) packageNodes.item(i);
                if ("uml:Package".equals(packageElement.getAttribute("xmi:type"))) {
                    String packageName = packageElement.getAttribute("name");
                    PackageModel packageModel = new PackageModel(packageName, new ArrayList<>());

                    // Parcourir les classes dans le package
                    NodeList classNodes = packageElement.getElementsByTagName("packagedElement");
                    for (int j = 0; j < classNodes.getLength(); j++) {
                        Element classElement = (Element) classNodes.item(j);
                        if ("uml:Class".equals(classElement.getAttribute("xmi:type"))) {
                            String className = classElement.getAttribute("name");

                            // Attributs
                            List<String> attributes = new ArrayList<>();
                            NodeList attributeNodes = classElement.getElementsByTagName("ownedAttribute");
                            for (int k = 0; k < attributeNodes.getLength(); k++) {
                                Element attributeElement = (Element) attributeNodes.item(k);
                                attributes.add(attributeElement.getAttribute("name"));
                            }

                            // Méthodes
                            List<String> methods = new ArrayList<>();
                            NodeList methodNodes = classElement.getElementsByTagName("ownedOperation");
                            for (int k = 0; k < methodNodes.getLength(); k++) {
                                Element methodElement = (Element) methodNodes.item(k);
                                methods.add(methodElement.getAttribute("name"));
                            }

                            // Relations
                            List<RelationModel> relations = new ArrayList<>();
                            NodeList relationNodes = classElement.getElementsByTagName("ownedRelation");
                            for (int k = 0; k < relationNodes.getLength(); k++) {
                                Element relationElement = (Element) relationNodes.item(k);
                                String source = relationElement.getAttribute("source");
                                String target = relationElement.getAttribute("target");
                                RelationType type = RelationType.valueOf(relationElement.getAttribute("type"));
                                relations.add(new RelationModel(source, target, type));
                            }

                            ClassModel classModel = new ClassModel(className, null, methods, attributes, relations);
                            packageModel.getClasses().add(classModel);
                        }
                    }

                    projetModel.getPackages().add(packageModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projetModel;
    }
}
