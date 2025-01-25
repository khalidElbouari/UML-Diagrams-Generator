package org.java.mql.umlGenerator.model;

import java.util.List;
public class PackageModel {
    private  String name;
    private  List<ClassModel> classes;

    public PackageModel(String name, List<ClassModel> classes) {
        this.name = name;
        this.classes = classes;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClassModel> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "PackageModel [name=" + name + ", classes=" + classes + "]";
	}
    
}