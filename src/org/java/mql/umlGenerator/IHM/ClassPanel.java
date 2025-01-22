package org.java.mql.umlGenerator.IHM;

import javax.swing.*;

import org.java.mql.umlGenerator.model.ClassModel;

import java.awt.*;
import java.util.List;

public class ClassPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private final ClassModel classModel;

    public ClassPanel(ClassModel classModel) {
        this.classModel = classModel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Organisation verticale
        setBackground(Color.WHITE);

        // Titre de la classe
        JLabel classHeader = new JLabel("<html><b>" + classModel.getName() + "</b></html>", SwingConstants.CENTER);
        classHeader.setOpaque(true);
        classHeader.setBackground(new Color(200, 200, 255)); // Couleur douce pour différencier les en-têtes
        add(classHeader);

        addSection( classModel.getAttributes());
        if (!classModel.getMethods().isEmpty()) {
            // Ligne de séparation entre les attributs et les méthodes
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            add(separator);

            addSection(classModel.getMethods());
        }
      
    }

    private void addSection(List<String> items) {
        if (!items.isEmpty()) {
            for (String item : items) {
                JLabel label = new JLabel(" " + item);
                label.setFont(new Font("Arial", Font.PLAIN, 16));
                add(label);
            }
        }
    }

	public ClassModel getClassModel() {
		return classModel;
	}
    
}
