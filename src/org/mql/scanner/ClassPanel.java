package org.mql.scanner;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClassPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public ClassPanel(ClassModel classModel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Organisation verticale
        setBackground(Color.WHITE);

        // Titre de la classe
        JLabel classHeader = new JLabel("<html><b>" + classModel.getName() + "</b></html>", SwingConstants.CENTER);
        classHeader.setOpaque(true);
        classHeader.setBackground(new Color(200, 200, 255)); // Couleur pour différencier les en-têtes
        add(classHeader);

        //  les attributs
        addSection("Attributs", classModel.getAttributes());

        //  les méthodes
        addSection("Méthodes", classModel.getMethods());

       //TO DO ???????: les relations entres les classes

        // Ajuster les marges pour un meilleur design
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // Méthode pour ajouter une section
    private void addSection(String title, List<String> items) {
        if (!items.isEmpty()) {
            add(new JLabel("<html><b>" + title + ":</b></html>"));
            for (String item : items) {
                JLabel label = new JLabel("  - " + item);
                label.setFont(new Font("Arial", Font.PLAIN, 12));
                add(label);
            }
        }
    }
}
