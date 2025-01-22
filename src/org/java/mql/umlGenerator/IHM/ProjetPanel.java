package org.java.mql.umlGenerator.IHM;

import javax.swing.*;

import org.java.mql.umlGenerator.model.PackageModel;
import org.java.mql.umlGenerator.model.ProjetModel;

import java.awt.*;

public class ProjetPanel extends JPanel {

    private static final long serialVersionUID = 1L;
	private ProjetModel projetModel;

    public ProjetPanel(ProjetModel projetModel) {
    	this.projetModel=projetModel;
       //setLayout(new GridLayout(0, 2, 80, 40)); // Deux colonnes, espacement
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
        for (PackageModel packageModel : this.projetModel.getPackages()) {
        	add(new PackagePanel(packageModel)); // Ajouter chaque package 
		 }
		 
    }
    
    public void displayPackageDiagram() {
        JFrame frame = new JFrame(projetModel.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);

        ProjetPanel panel = new ProjetPanel(projetModel);
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
    
        frame.setVisible(true);
    }
    
}
