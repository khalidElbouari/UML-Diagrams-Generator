package org.java.mql.umlGenerator.model;
import java.util.List;

public class ProjetModel {
    private String name;
    private List<PackageModel> packages;

    public ProjetModel(String name, List<PackageModel> packages) {
        this.name = name;
        this.packages = packages;
    }

    public ProjetModel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PackageModel> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageModel> packages) {
        this.packages = packages;
    }
    
       
    @Override
    public String toString() {
        return "ProjetModel{name='" + name + "', packages=" + packages + '}';
    }
}
