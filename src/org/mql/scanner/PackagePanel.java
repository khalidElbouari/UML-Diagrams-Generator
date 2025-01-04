package org.mql.scanner;

import javax.swing.*;
import java.awt.*;

public class PackagePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public PackagePanel(PackageModel packageModel) {
        setLayout(new BorderLayout()); 
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        setBackground(Color.WHITE); 

        // Titre du package
        JLabel packageHeader = new JLabel(packageModel.getName(), SwingConstants.CENTER);
        packageHeader.setOpaque(true);
        packageHeader.setBackground(Color.LIGHT_GRAY);
        packageHeader.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(packageHeader, BorderLayout.NORTH);

        // Conteneur des classes
        JPanel classContainer = new JPanel(new GridBagLayout());
        classContainer.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 20, 30); // Espacement entre les panneaux
        gbc.fill = GridBagConstraints.NONE; // Taille des composants basée sur leur contenu
        gbc.anchor = GridBagConstraints.CENTER; // Centrer chaque panneau
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Ajouter chaque classe au conteneur
        for (int i = 0; i < packageModel.getClasses().size(); i++) {
            ClassModel classModel = packageModel.getClasses().get(i);

            // Panneau pour chaque classe
            ClassPanel classPanel = new ClassPanel(classModel);
            classPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            gbc.gridx = i % 3; 
            gbc.gridy = i / 3; // Ligne (augmente après 3 colonnes)
            classContainer.add(classPanel, gbc);
        }

        add(new JScrollPane(classContainer), BorderLayout.CENTER);
    }
}
