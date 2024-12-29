package org.mql.scanner;

import java.util.List;

public class PackageModel {
    private final String name;
    private final List<ClassModel> classes;

    public PackageModel(String name, List<ClassModel> classes) {
        this.name = name;
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public List<ClassModel> getClasses() {
        return classes;
    }
}