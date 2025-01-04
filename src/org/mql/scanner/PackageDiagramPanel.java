package org.mql.scanner;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PackageDiagramPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public PackageDiagramPanel(List<PackageModel> packageModels) {
        setLayout(new GridLayout(0, 2, 80, 40)); // Deux colonnes, espacement
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (PackageModel packageModel : packageModels) {
            add(new PackagePanel(packageModel)); // Ajouter chaque package
        }
    }
}
