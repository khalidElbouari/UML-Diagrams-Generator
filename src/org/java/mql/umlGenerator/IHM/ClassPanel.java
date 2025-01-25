package org.java.mql.umlGenerator.IHM;

import javax.swing.*;

import org.java.mql.umlGenerator.enumeration.ClassType;
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

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(200, 200, 255)); 

        ClassType stereotype = classModel.getType();
        JLabel stereotypeLabel = new JLabel("<< " + stereotype + " >>", SwingConstants.CENTER);
        stereotypeLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        stereotypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrage horizontal

        JLabel classLabel = new JLabel("<html><b>" + classModel.getName() + "</html>", SwingConstants.CENTER);
        classLabel.setFont(new Font("Arial", Font.BOLD, 16));
        classLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if(ClassType.CLASS!=stereotype) {
            headerPanel.add(stereotypeLabel);
        }
        headerPanel.add(classLabel);
        add(headerPanel);

        // Ajouter les sections pour les attributs et les méthodes
        addSection(classModel.getAttributes());
        if (!classModel.getMethods().isEmpty()) {
            add(new JSeparator(SwingConstants.HORIZONTAL));
            addSection(classModel.getMethods());
        }
    }

    private void addSection(List<String> items) {
        if (!items.isEmpty()) {
            for (String item : items) {
                JLabel label = new JLabel(" " + item);
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                add(label);
            }
        }
    }

    public ClassModel getClassModel() {
        return classModel;
    }
}
