package org.java.mql.umlGenerator;

import java.io.File;
import java.lang.reflect.*;
import java.util.*;

import org.java.mql.umlGenerator.enumeration.ClassType;
import org.java.mql.umlGenerator.enumeration.RelationType;
import org.java.mql.umlGenerator.model.ClassModel;
import org.java.mql.umlGenerator.model.PackageModel;
import org.java.mql.umlGenerator.model.ProjetModel;
import org.java.mql.umlGenerator.model.RelationModel;

public class Scanner {
    private List<PackageModel> packageModels;
    private ProjetModel projetModel;

    public Scanner(String packageName, String projectName) {
        try {
            packageModels = new ArrayList<>();
            String classPath = System.getProperty("java.class.path");
            String packagePath = packageName.replace(".", "//");
            String fullPath = classPath + "//" + packagePath;

            File dir = new File(fullPath);
            if (dir.exists() && dir.isDirectory()) {
                scanDirectory(dir, packageName);
            } else {
                System.err.println("Package path introuvable " + fullPath);
            }

            this.projetModel = new ProjetModel(projectName, packageModels);

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
            ClassType type;

            if (cls.isInterface()) {
                type = ClassType.INTERFACE;
            } else if (Modifier.isAbstract(cls.getModifiers())) {
                type = ClassType.ABSTRACT;
            } else if (cls.isEnum()) {
                type = ClassType.ENUM;
            } else if (cls.isAnnotation()) {
                type = ClassType.ANNOTATION;
            } else {
                type = ClassType.CLASS; 
            }

            List<String> methodNames = new ArrayList<>();
            List<RelationModel> relations = new ArrayList<>();

            for (Method method : cls.getDeclaredMethods()) {
                String visibility = getVisibilitySymbol(method.getModifiers());
                String returnType = method.getReturnType().getSimpleName();
                methodNames.add(visibility + " " + method.getName() + "() : " + returnType);
            }

            if (cls.getSuperclass() != null && !cls.getSuperclass().equals(Object.class)) {
                relations.add(new RelationModel(cls.getSimpleName(), cls.getSuperclass().getSimpleName(), RelationType.EXTENSION));
            }

            for (Class<?> iface : cls.getInterfaces()) {
                relations.add(new RelationModel(cls.getSimpleName(), iface.getSimpleName(), RelationType.IMPLEMENTATION));
            }

            List<String> fieldNames = new ArrayList<>();
            for (Field field : cls.getDeclaredFields()) {
                String visibility = getVisibilitySymbol(field.getModifiers());
                String typeField = field.getType().getSimpleName();
                fieldNames.add(visibility + " " + field.getName() + " : " + typeField);

                // Détection des relations d'agrégation et association
                detectAggregationOrAsociation(cls, field, relations);
            }

            return new ClassModel(name, type, methodNames, fieldNames, relations);

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
            return null;
        }
    }

    private void detectAggregationOrAsociation(Class<?> cls, Field field, List<RelationModel> relations) {
         Class<?> fieldType = field.getType(); 
        // Récupérer le type générique du champ
        Type genericType = field.getGenericType();

        // Vérifier si le champ est une collection (List, Set ou Map)
        if (Collection.class.isAssignableFrom(fieldType)) {
            String typeName = genericType.getTypeName(); 
            int startIndex = typeName.indexOf('<'); 
            int endIndex = typeName.lastIndexOf('>'); 

            // Vérifier si les délimiteurs de type générique existent
            if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                // Extraire le type générique entre les crochets "<>"
                String genericTypeName = typeName.substring(startIndex + 1, endIndex);
                try {
                    // Charger la classe correspondant au type générique
                    Class<?> genericClass = Class.forName(genericTypeName.trim());
                    if (isCustomClass(genericClass)) {
                        relations.add(new RelationModel(cls.getSimpleName(), genericClass.getSimpleName(), RelationType.AGGREGATION));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        // Vérifier si le champ est un tableau
        else if (fieldType.isArray()) {
            Class<?> componentType = fieldType.getComponentType();
            //si le type des éléments est une classe personnalisée
            if (isCustomClass(componentType)) {
                relations.add(new RelationModel(cls.getSimpleName(), componentType.getSimpleName(), RelationType.AGGREGATION));
            }
        }
        // Vérifier les associations simples (types non collection ni tableau)
        else if (isCustomClass(fieldType)) {
            relations.add(new RelationModel(cls.getSimpleName(), fieldType.getSimpleName(), RelationType.UTILIZATION));
        }
    }


    private String getVisibilitySymbol(int modifiers) {
        if (Modifier.isPrivate(modifiers)) {
            return "-";
        } else if (Modifier.isProtected(modifiers)) {
            return "#";
        } else if (Modifier.isPublic(modifiers)) {
            return "+";
        } else {
            return "~";
        }
    }

    private boolean isCustomClass(Class<?> cls) {
        if (cls.isPrimitive() || 
            cls == String.class || 
            cls == Integer.class || 
            cls == Double.class || 
            cls == Float.class || 
            cls == Long.class || 
            cls == Short.class || 
            cls == Boolean.class || 
            cls == Byte.class || 
            cls == Character.class) {
            return false;
        }

       return true;
    }

    public List<PackageModel> getPackages() {
        return packageModels;
    }

    public ProjetModel getProjetModel() {
        return projetModel;
    }
}
