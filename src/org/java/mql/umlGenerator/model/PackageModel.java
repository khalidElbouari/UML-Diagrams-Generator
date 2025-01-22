package org.java.mql.umlGenerator.model;

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

	@Override
	public String toString() {
		return "PackageModel [name=" + name + ", classes=" + classes + "]";
	}
    
}