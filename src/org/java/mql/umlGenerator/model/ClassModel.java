package org.java.mql.umlGenerator.model;

import java.util.Collections;
import java.util.List;

import org.java.mql.umlGenerator.enumeration.ClassType;

public class ClassModel {
    private  String name;
    private  ClassType type;
    private  List<String> methods;
    private List<String> attributes;
    private  List<RelationModel> relations;

    public ClassModel(String name, ClassType type, List<String> methods, List<String> attributes, List<RelationModel> relations) {
        this.name = name;
        this.type = type;
        this.methods = Collections.unmodifiableList(methods);
        this.attributes = Collections.unmodifiableList(attributes);
        this.relations = Collections.unmodifiableList(relations);
    }


    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ClassType getType() {
		return type;
	}


	public void setType(ClassType type) {
		this.type = type;
	}


	public List<String> getMethods() {
		return methods;
	}


	public void setMethods(List<String> methods) {
		this.methods = methods;
	}


	public List<String> getAttributes() {
		return attributes;
	}


	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}


	public List<RelationModel> getRelations() {
		return relations;
	}


	public void setRelations(List<RelationModel> relations) {
		this.relations = relations;
	}


	@Override
    public String toString() {
        return "ClassModel [name=" + name + ", type=" + type + ", methods=" + methods +
               ", attributes=" + attributes + ", relations=" + relations + "]";
    }
}
