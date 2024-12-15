package org.mql.scanner;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    
    public void scan(String packageName) {
        try {
            // Récupère le classpath
            String classPath = System.getProperty("java.class.path");
            String packagePath = packageName.replace(".", "\\");
            String fullPath = classPath + "\\" + packagePath;

            File dir = new File(fullPath);
            if (dir.exists() && dir.isDirectory()) {
                scanDirectory(dir, packageName);
            } else {
                System.err.println("Verifie le path svp :" + fullPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Scanne un répertoire pour trouver les classes et sous-packages
    private void scanDirectory(File dir, String packageName) {
        if (dir == null || !dir.isDirectory()) return;

        File[] content = dir.listFiles();

        if (content != null) {
            for (File file : content) {
                if (file.isDirectory()) {
                    // Appel récursif pour les sous-dossiers
                    scanDirectory(file, packageName + "." + file.getName());
                } else if (file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().replace(".class", "");
                   // Ajouter dans une structure le package et ses classes.
                }
            }
        }
       
    }

    
    // Affiche le diagramme des packages dans une fenêtre
    public void displayPackageDiagram() {
    	//To Do 
    	System.out.println("test");
       
    }

   
}
