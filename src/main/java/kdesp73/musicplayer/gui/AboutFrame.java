/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kdesp73.musicplayer.gui;

import dorkbox.desktop.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import kdesp73.musicplayer.backend.Backend;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.files.FileOperations;
import kdesp73.musicplayer.files.Images;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.ThemeCollection;
import kdesp73.themeLib.YamlFile;

/**
 *
 * @author konstantinos
 */
public class AboutFrame extends javax.swing.JFrame {

	String themes_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/";

	LogoFrame logoFrame;
	
	public AboutFrame() {
		initComponents();

		this.setResizable(false);
		this.rootPane.requestFocus();
		this.setLocationRelativeTo(null);
		this.setTitle("About");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GUIMethods.setFontFamilyRecursively(this, "sans-serif", Font.PLAIN);
		String mode = Queries.selectTheme();
		ThemeCollection.applyTheme(this, new Theme(new YamlFile(themes_path + ((mode.equals("Dark") ? "dark.yml" : "light.yml")))));

		descriptionTextArea.setText("An Mp3 Player with information scraping capabilities and a slick UI");
		descriptionTextArea.setCaretPosition(0);

		licenseTextArea.setText(FileOperations.readFile(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/LICENSE"));
//		String os = System.getProperty("os.name").toLowerCase();
//		if (os.contains("linux")) {
//			licenseTextArea.setText(FileOperations.readFile(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/LICENSE"));
//		} else if (os.contains("windows")) {
//			licenseTextArea.setText(FileOperations.readFile(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/LICENSE.txt"));
//		}

		licenseTextArea.setCaretPosition(0);

		nameLabel.setText("Konstantinos Despoinidis");
		emailLabel.setText("despoinidisk@gmail.com");
		githubLabel.setText("https://github.com/KDesp73");

		int size = 20;
		Backend.loadIcon(nameLabel, (mode.equals("Dark") ? Images.developerWhite : Images.developer), new Dimension(size, size));
		Backend.loadIcon(emailLabel, (mode.equals("Dark") ? Images.mailWhite : Images.mail), new Dimension(size, size));
		Backend.loadIcon(githubLabel, (mode.equals("Dark") ? Images.githubWhite : Images.github), new Dimension(size, size));
		
		try {
			GUIMethods.loadImage(this.logoLabel, GUIMethods.resizeImageHeight(ImageIO.read(new File(Images.kittifyLogo)), 123));
		} catch (IOException ex) {
			Logger.getLogger(AboutFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		logoFrame = new LogoFrame();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        licenseTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        githubLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLabel1.setText("Kittify");
        jLabel1.setName("fg"); // NOI18N

        jLabel2.setText("v1.0.0");
        jLabel2.setName("fg"); // NOI18N

        descriptionTextArea.setEditable(false);
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setName("textbox"); // NOI18N
        jScrollPane1.setViewportView(descriptionTextArea);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setText("License");
        jLabel3.setName("fg"); // NOI18N

        licenseTextArea.setEditable(false);
        licenseTextArea.setColumns(20);
        licenseTextArea.setLineWrap(true);
        licenseTextArea.setRows(5);
        licenseTextArea.setWrapStyleWord(true);
        licenseTextArea.setName("textbox"); // NOI18N
        jScrollPane2.setViewportView(licenseTextArea);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Contact Info");
        jLabel4.setName("fg"); // NOI18N

        nameLabel.setText("Name");
        nameLabel.setName("fg"); // NOI18N

        emailLabel.setText("Email");
        emailLabel.setName("fg"); // NOI18N

        githubLabel.setText("Github");
        githubLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        githubLabel.setName("fg"); // NOI18N
        githubLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                githubLabelMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Thank you for using my application!");
        jLabel5.setName("fg"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Description");
        jLabel6.setName("fg"); // NOI18N

        logoLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(githubLabel)
                    .addComponent(emailLabel)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(backgroundLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)))
                    .addComponent(nameLabel)
                    .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(githubLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void githubLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_githubLabelMouseClicked
		try {
			Desktop.browseURL(githubLabel.getText());
		} catch (IOException ex) {
			Logger.getLogger(AboutFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
    }//GEN-LAST:event_githubLabelMouseClicked

    private void logoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoLabelMouseClicked
		if(!logoFrame.isShowing()){
			logoFrame.setVisible(true);
		}
    }//GEN-LAST:event_logoLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel githubLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea licenseTextArea;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
