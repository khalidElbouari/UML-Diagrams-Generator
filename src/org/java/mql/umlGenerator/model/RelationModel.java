package org.java.mql.umlGenerator.model;


import org.java.mql.umlGenerator.enumeration.RelationType;

public class RelationModel {
    private String classSourceName;
    private String classTargetName;
    private RelationType relationType; 

    public RelationModel(String classSourceName, String classTargetName, RelationType relationType) {
        this.classSourceName = classSourceName;
        this.classTargetName = classTargetName;
        this.relationType = relationType;
    }

    public String getClassSourceName() {
        return classSourceName;
    }

    public String getClassTargetName() {
        return classTargetName;
    }

    public RelationType getRelationType() {
        return relationType;
    }

	@Override
	public String toString() {
		return "RelationModel [classSourceName=" + classSourceName + ", classTargetName=" + classTargetName
				+ ", relationType=" + relationType + "]";
	}

  
}
