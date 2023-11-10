/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kdesp73.musicplayer.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import kdesp73.musicplayer.backend.Backend;
import static kdesp73.musicplayer.backend.Backend.loadIcon;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.files.Images;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.ThemeCollection;
import kdesp73.themeLib.YamlFile;

/**
 *
 * @author konstantinos
 */
public class ThemesFrame extends javax.swing.JFrame {

	private String themes_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/";
	private JFrame frame;
	boolean onInit = true;

	/**
	 * Creates new form themesFrame
	 *
	 * @param frame
	 */
	public ThemesFrame(JFrame frame) {
		initComponents();

		this.frame = frame;

		this.setLocationRelativeTo(null);
		this.setTitle("Themes");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String mode = Queries.selectMode();
		this.modeComboBox.setSelectedItem(mode);

		Backend.setMode(this, mode);

		GUIMethods.setFontFamilyRecursively(this, "sans-serif", Font.PLAIN);
		ThemeCollection.applyTheme(this, new Theme(new YamlFile(themes_path + ((Queries.selectTheme().equals("Dark") ? "dark.yml" : "light.yml")))));

	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        modeComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg"); // NOI18N

        modeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Light", "Dark" }));
        modeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Mode");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeComboBoxActionPerformed
		if (onInit) {
			onInit = false;
			return;
		}
		
		String mode = (String) modeComboBox.getSelectedItem();

		Queries.updateMode(mode);
		Backend.setMode(this, mode);
		Backend.setMode(frame, mode);
		
		loadIcon(((MainFrame) frame).getRepeatLabel(), (mode.equals("Dark") ? Images.repeatWhite : Images.repeat), new Dimension(20, 20));
		loadIcon(((MainFrame) frame).getShuffleLabel(), (mode.equals("Dark") ? Images.shuffleWhite : Images.shuffle), new Dimension(20, 20));
    }//GEN-LAST:event_modeComboBoxActionPerformed

	public JComboBox<String> getModeComboBox() {
		return modeComboBox;
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> modeComboBox;
    // End of variables declaration//GEN-END:variables
}
