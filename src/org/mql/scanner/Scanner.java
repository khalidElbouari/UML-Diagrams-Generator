package org.mql.scanner;



import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Scanner {
    private List<PackageModel> packageModels = new ArrayList<>();

    public void scan(String packageName) {
        try {
            String classPath = System.getProperty("java.class.path");
            String packagePath = packageName.replace(".", "\\");
            String fullPath = classPath + "\\" + packagePath;

            File dir = new File(fullPath);
            if (dir.exists() && dir.isDirectory()) {
                scanDirectory(dir, packageName);
            } else {
                System.err.println("Package path does not exist: " + fullPath);
            }
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
            for (Method method : cls.getDeclaredMethods()) {
                methodNames.add(method.getName());
            }

            List<String> fieldNames = new ArrayList<>();
            List<String> compositions = new ArrayList<>();
            List<String> dependencies = new ArrayList<>();

            for (Field field : cls.getDeclaredFields()) {
                fieldNames.add(field.getName());

               
            }

            ClassModel classModel = new ClassModel(name, methodNames, fieldNames);
           
            return classModel;

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
            return null;
        }
    }

    public void displayPackageDiagram() {
        JFrame frame = new JFrame("Package Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        PackageDiagramPanel panel = new PackageDiagramPanel(packageModels);
        frame.add(new JScrollPane(panel));
        frame.setVisible(true);
    }

    public List<PackageModel> getPackages() {
        return packageModels;
    }
}
