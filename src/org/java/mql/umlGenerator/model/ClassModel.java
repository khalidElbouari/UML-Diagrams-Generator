package org.java.mql.umlGenerator.model;


import java.util.List;

public class ClassModel {
    private final String name;
    private final List<String> methods;
    private final List<String> attributes;
    private final List<RelationModel> relations;


    public ClassModel(String name, List<String> methods, List<String> attributes,List<RelationModel> relations) {
		super();
		this.name = name;
		this.methods = methods;
		this.attributes = attributes;
		this.relations = relations;
	}

	public List<RelationModel> getRelations() {
		return relations;
	}

	public String getName() {
        return name;
    }

    public List<String> getMethods() {
        return methods;
    }

    public List<String> getAttributes() {
        return attributes;
    }

	@Override
	public String toString() {
		return "ClassModel [name=" + name + ", methods=" + methods + ", attributes=" + attributes + ", relations="
				+ relations + "]";
	}

   
}
