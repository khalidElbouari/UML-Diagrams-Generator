package org.java.mql.umlGenerator.IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

import org.java.mql.umlGenerator.enumeration.RelationType;
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
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(packageHeader, gbc);
    }

    private void addClasses(PackageModel packageModel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 20, 30);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        for (int i = 0; i < packageModel.getClasses().size(); i++) {
            ClassModel classModel = packageModel.getClasses().get(i);
            ClassPanel classPanel = new ClassPanel(classModel);
            classPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            classPanels.add(classPanel);
            gbc.gridx = i % 3;
            gbc.gridy = (i / 3) + 1;

            add(classPanel, gbc);
        }
    }

    private void addRelations(PackageModel packageModel) {
        for (ClassModel classModel : packageModel.getClasses()) {
            List<RelationModel> classRelations = classModel.getRelations();
            for (RelationModel relation : classRelations) {
                ClassPanel relatedClassPanel = findClassPanelByName(relation.getClassTargetName());
                if (relatedClassPanel != null) {
                    relations.add(new RelationModel(
                            classModel.getName(),
                            relation.getClassTargetName(),
                            relation.getRelationType()
                    ));
                }
            }
        }
    }

    private ClassPanel findClassPanelByName(String className) {
        for (ClassPanel panel : classPanels) {
            if (panel.getClassModel().getName().equals(className)) {
                return panel;
            }
        }
        return null;
    }

    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawRelations(g2d);
    }

    private void drawRelations(Graphics2D g) {
        for (RelationModel relation : relations) {
            ClassPanel sourcePanel = findClassPanelByName(relation.getClassSourceName());
            ClassPanel targetPanel = findClassPanelByName(relation.getClassTargetName());

            if (sourcePanel != null && targetPanel != null) {
                Point sourcePoint = getEdgePoint(sourcePanel, targetPanel);
                Point targetPoint = getEdgePoint(targetPanel, sourcePanel);

                // Dessiner une ligne droite avec une flèche
                drawStraightArrow(g, sourcePoint, targetPoint, relation.getRelationType());
            }
        }
    }

    private void drawStraightArrow(Graphics2D g, Point source, Point target, RelationType type) {
        Stroke defaultStroke = g.getStroke();
        
        // Définir une couleur et un style différents selon le type de relation
        switch (type) {
            case COMPOSITION, AGGREGATION, EXTENSION -> g.setColor(Color.BLACK);
            case IMPLEMENTATION -> {
                g.setColor(Color.BLUE);
                g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{5, 5}, 0)); // Ligne pointillée
            }
            case UTILIZATION -> g.setColor(Color.DARK_GRAY);
        }

        // Dessiner la ligne droite
        g.drawLine(source.x, source.y, target.x, target.y);

        // Restaurer le style normal après l'implémentation
        g.setStroke(defaultStroke);

        // Dessiner un indicateur de relation à la fin de la ligne
        drawRelationIndicator(g, target, type);
    }

    private Point getEdgePoint(ClassPanel panel, ClassPanel otherPanel) {
        Rectangle bounds = panel.getBounds();
        Point center = new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);

        Rectangle otherBounds = otherPanel.getBounds();
        Point otherCenter = new Point(otherBounds.x + otherBounds.width / 2, otherBounds.y + otherBounds.height / 2);

        double dx = otherCenter.x - center.x;
        double dy = otherCenter.y - center.y;

        if (Math.abs(dx) > Math.abs(dy)) {
            // Ligne horizontale
            return new Point(dx > 0 ? bounds.x + bounds.width : bounds.x, center.y);
        } else {
            // Ligne verticale
            return new Point(center.x, dy > 0 ? bounds.y + bounds.height : bounds.y);
        }
    }

    private void drawRelationIndicator(Graphics2D g, Point target, RelationType type) {
        int x = target.x;
        int y = target.y;

        switch (type) {
            case COMPOSITION -> {
                int[] compX = {x, x - 10, x, x + 10};
                int[] compY = {y, y - 10, y - 20, y - 10};
                g.fillPolygon(compX, compY, 4); 
            }
            case AGGREGATION -> {
                int[] aggX = {x, x - 10, x, x + 10};
                int[] aggY = {y, y - 10, y - 20, y - 10};
                g.drawPolygon(aggX, aggY, 4); 
            }
            case EXTENSION -> {
                int[] extX = {x, x - 10, x + 10};
                int[] extY = {y, y - 10, y - 10};
                g.fillPolygon(extX, extY, 3); 
            }
            case IMPLEMENTATION -> {
                int[] implX = {x, x - 10, x + 10};
                int[] implY = {y, y - 10, y - 10};
                g.drawPolygon(implX, implY, 3);
            }
            case UTILIZATION -> {
                g.drawLine(x, y, x - 5, y - 10); 
            }
        }
    }

}
