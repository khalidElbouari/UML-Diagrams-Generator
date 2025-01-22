package org.java.mql.umlGenerator;



import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.java.mql.umlGenerator.enumeration.RelationType;
import org.java.mql.umlGenerator.model.ClassModel;
import org.java.mql.umlGenerator.model.PackageModel;
import org.java.mql.umlGenerator.model.ProjetModel;
import org.java.mql.umlGenerator.model.RelationModel;

public class Scanner {
    private List<PackageModel> packageModels ;
    private ProjetModel projetModel ;

    
    public Scanner(String packageName, String projectName) {
        try {
        	packageModels = new ArrayList<>();
        	String classPath = System.getProperty("java.class.path");
            String packagePath = packageName.replace(".", "\\");
            String fullPath = classPath + "\\" + packagePath;

            File dir = new File(fullPath);
            if (dir.exists() && dir.isDirectory()) {
                scanDirectory(dir, packageName);
            } else {
                System.err.println("Package path introuvable " + fullPath);
            }

            // Retourne un ProjetModel avec les packages scannés
            
            this.projetModel= new ProjetModel(projectName, packageModels);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }


    private void scanDirectory(File dir, String packageName) {
        if (dir == null || !dir.isDirectory()) return;

        List<ClassModel> classModels = new ArrayList<>();
        File[] content = dir.listFiles();

        if (content != null) {
            for (File file : content) {
                if (file.isDirectory()) {
                	
                    scanDirectory(file, packageName + "." + file.getName());
                    
                    
                } else if (file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().replace(".class", "");
                    ClassModel classModel = getClassModel(className);
                    if (classModel != null) {
                        classModels.add(classModel);
                    }
                }
            }
        }
        if (!classModels.isEmpty()) {
            packageModels.add(new PackageModel(packageName, classModels));
        }
    }

    private ClassModel getClassModel(String className) {
    	
        try {
            Class<?> cls = Class.forName(className);
            String name = cls.getSimpleName();

            List<String> methodNames = new ArrayList<>();
            List<String> fieldNames = new ArrayList<>();
            List<RelationModel> relations = new ArrayList<>();

            // Analyse des méthodes
            for (Method method : cls.getDeclaredMethods()) {
                String visibility = getVisibilitySymbol(method.getModifiers());
                String returntype = method.getReturnType().getSimpleName();
                methodNames.add(visibility + " " + method.getName() + "() : " + returntype);
            }

            // Analyse des champs
            for (Field field : cls.getDeclaredFields()) {
                String visibility = getVisibilitySymbol(field.getModifiers());
                String type = field.getType().getSimpleName();

                // Ignorer la relation avec Object
                    fieldNames.add(visibility + " " + field.getName() + " : " + type);

                    // Vérification des relations
                    if (field.getType().isAssignableFrom(cls)) {
                        relations.add(new RelationModel(cls.getSimpleName(), field.getType().getSimpleName(), RelationType.COMPOSITION));
                    } else {
                        relations.add(new RelationModel(cls.getSimpleName(), field.getType().getSimpleName(), RelationType.AGGREGATION));
                    }
                }
            

            // Relation d'héritage uniquement si la classe a un superclasse réelle (exclut Object)
            if (cls.getSuperclass() != null && !cls.getSuperclass().getSimpleName().equals("Object")) {
                relations.add(new RelationModel(cls.getSimpleName(), cls.getSuperclass().getSimpleName(), RelationType.EXTENSION));
            }

            // Relation d'implémentation d'interface
            for (Class<?> iface : cls.getInterfaces()) {
                relations.add(new RelationModel(cls.getSimpleName(), iface.getSimpleName(), RelationType.IMPLEMENTATION));
            }

            return new ClassModel(name, methodNames, fieldNames, relations);

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
            return null;
        }   
}

    private String getVisibilitySymbol(int modifiers) {
        String modifiersText = Modifier.toString(modifiers);
        if (modifiersText.contains("private")) {
            return "-";
        } else if (modifiersText.contains("protected")) {
            return "#";
        } else if (modifiersText.contains("public")) {
            return "+";
        } else {
            return "~"; 
        }
    }

    public List<PackageModel> getPackages() {
        return packageModels;
    }


	public List<PackageModel> getPackageModels() {
		return packageModels;
	}


	public void setPackageModels(List<PackageModel> packageModels) {
		this.packageModels = packageModels;
	}


	public ProjetModel getProjetModel() {
		return projetModel;
	}


	public void setProjetModel(ProjetModel projetModel) {
		this.projetModel = projetModel;
	}
    
}
