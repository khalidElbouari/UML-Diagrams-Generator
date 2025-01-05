package org.mql.scanner;

import javax.swing.*;
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
        classHeader.setBackground(new Color(200, 200, 255)); 
        add(classHeader);

        addSection( classModel.getAttributes());
        if (!classModel.getMethods().isEmpty()) {
            // Ligne de séparation entre les attributs et les méthodes
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            add(separator);

            addSection(classModel.getMethods());
        }
       

        // Ajuster les marges pour un meilleur design
      //  setBorder(BorderFactory.createEmptyBorder(60, 200, 20, 10));
    }

    private void addSection(List<String> items) {
        if (!items.isEmpty()) {
            for (String item : items) {
                JLabel label = new JLabel(" " + item);
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                add(label);
            }
        }
    }
}
