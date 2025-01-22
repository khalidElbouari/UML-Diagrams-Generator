package org.java.mql.umlGenerator.IHM;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.java.mql.umlGenerator.model.ClassModel;
import org.java.mql.umlGenerator.model.PackageModel;
import org.java.mql.umlGenerator.model.RelationModel;

public class PackagePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private List<RelationModel> relations = new ArrayList<>();
    private List<ClassPanel> classPanels = new ArrayList<>();

    public PackagePanel(PackageModel packageModel) {
        setLayout(new GridBagLayout()); 
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        addPackageHeader(packageModel);
        addClasses(packageModel);
        addRelations(packageModel);

        revalidate();
        repaint();
    }

    private void addPackageHeader(PackageModel packageModel) {
        JLabel packageHeader = new JLabel(packageModel.getName(), SwingConstants.LEFT);
        packageHeader.setOpaque(true);
        packageHeader.setBackground(Color.LIGHT_GRAY);
        packageHeader.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Utiliser toute la largeur disponible
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour du titre
        add(packageHeader, gbc);
    }

    private void addClasses(PackageModel packageModel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 20, 30); // Espacement entre les panneaux
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        for (int i = 0; i < packageModel.getClasses().size(); i++) {
            ClassModel classModel = packageModel.getClasses().get(i);
            ClassPanel classPanel = new ClassPanel(classModel);
            classPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Ajouter le panneau de la classe au PackagePanel
            classPanels.add(classPanel);
            
            gbc.gridx = i % 3;
            gbc.gridy = (i / 3) + 1; // Commencer après la ligne du titre pour les classes

            add(classPanel, gbc);
        }
    }

    private void addRelations(PackageModel packageModel) {
        for (int i = 0; i < packageModel.getClasses().size(); i++) {
            ClassModel classModel = packageModel.getClasses().get(i);
            List<RelationModel> classRelations = classModel.getRelations(); 
            // Récupérer les relations de la classe
            for (RelationModel relation : classRelations) {
                ClassPanel relatedClassPanel = findClassPanelByName(relation.getClassTargetName(), classPanels);
                if (relatedClassPanel != null) {
                    // Ajouter la relation à la liste des relations de PackagePanel
                    relations.add(new RelationModel(classPanels.get(i).getClassModel().getName(),
                            relation.getClassTargetName(), relation.getRelationType()));
                }
            }
        }
    }

    private ClassPanel findClassPanelByName(String className, List<ClassPanel> classPanels) {
        for (ClassPanel panel : classPanels) {
            String classModelName = panel.getClassModel().getName();
            if (classModelName.equals(className) || classModelName.endsWith("." + className)) {
                return panel;
            }
        }
        return null;
    }

    // Méthode pour dessiner les relations sur le PackagePanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        int margin = 25;  // Marge pour éloigner les relations des bords
        for (RelationModel relation : relations) {
            ClassPanel classPanel1 = findClassPanelByName(relation.getClassSourceName(), classPanels);
            ClassPanel classPanel2 = findClassPanelByName(relation.getClassTargetName(), classPanels);

            if (classPanel1 != null && classPanel2 != null) {
                // Calcul des coordonnées des bords des classes
                Point point1 = SwingUtilities.convertPoint(classPanel1.getParent(), classPanel1.getLocation(), this);
                Point point2 = SwingUtilities.convertPoint(classPanel2.getParent(), classPanel2.getLocation(), this);

                // Définir les bords de la classe source et cible pour la relation
                int x1 = point1.x + classPanel1.getWidth(); // Bord droit de la classe source
                int y1 = point1.y + classPanel1.getHeight() / 2; // Centre vertical de la classe source

                int x2 = point2.x;  // Bord gauche de la classe cible
                int y2 = point2.y + classPanel2.getHeight(); 

                // Dessiner la ligne de la relation
                g.setColor(Color.BLACK);
                
            	}   
           }
        
        }


    
    
    
}
