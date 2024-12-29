package org.mql.scanner;

import java.util.List;
// TO DO comment modiliser les dependences entres les classes?
public class ClassModel {
	
    private final String name;
    private final List<String> methods;
    private final List<String> attributes;
    

    public ClassModel(String name, List<String> methods, List<String> attributes) {
        this.name = name;
        this.methods = methods;
        this.attributes = attributes;
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

  
}
