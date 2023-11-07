/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kdesp73.musicplayer.gui;

import java.awt.Font;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import kdesp73.musicplayer.db.Queries;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.ThemeCollection;
import kdesp73.themeLib.YamlFile;

/**
 *
 * @author konstantinos
 */
public class HelpFrame extends javax.swing.JFrame {

	String themes_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/";

	public HelpFrame() {
		initComponents();

		this.setResizable(false);
//		this.setSize(600, 800);
		this.rootPane.requestFocus();
		this.setLocationRelativeTo(null);
		this.setTitle("Help");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GUIMethods.setFontFamilyRecursively(this, "sans-serif", Font.PLAIN);
		String mode = Queries.selectTheme();
		ThemeCollection.applyTheme(this, new Theme(new YamlFile(themes_path + ((mode.equals("Dark") ? "dark.yml" : "light.yml")))));
		
	
		
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg"); // NOI18N

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1122, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    // End of variables declaration//GEN-END:variables
}
