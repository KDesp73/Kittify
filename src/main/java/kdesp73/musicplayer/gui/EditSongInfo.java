/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kdesp73.musicplayer.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import kdesp73.musicplayer.backend.Backend;
import kdesp73.musicplayer.songs.Mp3File;
import kdesp73.musicplayer.db.Queries;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.ThemeCollection;
import kdesp73.themeLib.YamlFile;

/**
 *
 * @author konstantinos
 */
public class EditSongInfo extends javax.swing.JFrame {

	Mp3File song;
	MainFrame frame;
	String themes_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/";

	public EditSongInfo(MainFrame frame) {
		initComponents();

		this.setResizable(false);
		this.rootPane.requestFocus();
		this.setLocationRelativeTo(null);
		this.setTitle("Edit Song Information");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.song = frame.list.getSongs().get(frame.getSongsList().getSelectedIndex());
		this.frame = frame;

		titleTextField.setText(song.getTrack().getName());
		artistTextField.setText(song.getTrack().getArtist());
		albumTextField.setText(song.getTrack().getAlbum());
		
		String albumCover = Queries.selectLocalCoverPath(song.getTrack().getAlbum(), song.getTrack().getArtist());
		albumCoverTextField.setText((albumCover == null) ? "" : albumCover);
		
		if(albumCover == null || albumCover.isBlank()){
			this.removeAlbumCoverButton.setEnabled(false);
			this.removeAlbumCoverButton.setToolTipText("No local album cover is set");
		} 
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Backend.sort(frame);
//				Backend.selectSong(frame, frame.list.searchSongName(frame.currentSong.getTrack().getName()));
				
				
			}

		});
		
		ThemeCollection.applyTheme(this, new Theme(new YamlFile(themes_path + ((Queries.selectTheme().equals("Dark") ? "dark.yml" : "light.yml")))));
	}

	/**
	 * This method is called from within the
	 * constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The
	 * content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        artistTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        albumTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        albumCoverTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        removeAlbumCoverButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg"); // NOI18N

        jLabel1.setText("Title:");

        titleTextField.setText("jTextField1");
        titleTextField.setName("textbox"); // NOI18N

        jLabel2.setText("Artist:");

        artistTextField.setText("jTextField1");
        artistTextField.setName("textbox"); // NOI18N

        jLabel3.setText("Album:");

        albumTextField.setText("jTextField1");
        albumTextField.setName("textbox"); // NOI18N

        jLabel4.setText("Album Cover:");

        albumCoverTextField.setName("textbox"); // NOI18N

        browseButton.setText("Browse");
        browseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });

        applyButton.setText("Apply");
        applyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        removeAlbumCoverButton.setText("Remove");
        removeAlbumCoverButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeAlbumCoverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAlbumCoverButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton))
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(backgroundLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(albumCoverTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(browseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                        .addGroup(backgroundLayout.createSequentialGroup()
                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(61, 61, 61)
                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(titleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                .addComponent(artistTextField)
                                .addComponent(albumTextField))))
                    .addComponent(removeAlbumCoverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(artistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(albumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(albumCoverTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(browseButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeAlbumCoverButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(cancelButton))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	public Mp3File getUpdatedFile() {
		return song;
	}

    private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButtonMouseClicked
		String dir = "";
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");

		// TODO Scale if too big or small
		
		fc.setFileFilter(filter);

		int choice = fc.showOpenDialog(this);

		if (choice == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			dir = selectedFile.getPath();
		}

		if (choice == JFileChooser.CANCEL_OPTION) {
			return;
		}

		albumCoverTextField.setText(dir);
    }//GEN-LAST:event_browseButtonMouseClicked

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
		String title = titleTextField.getText();
		String artist = artistTextField.getText();
		String album = albumTextField.getText();
		String cover = albumCoverTextField.getText();
		
		
		if(title.isBlank() || title.isEmpty()){
			JOptionPane.showMessageDialog(this, "Title can't be empty!", "Empty Title", JOptionPane.WARNING_MESSAGE, null);
			return;
		}
		
		if(artist.isBlank() || artist.isEmpty()){
			artist = "Unknown Artist";
		}
		
		if(album.isBlank() || album.isEmpty()){
			album = "Unknown Album";
		}
		
		song.getTrack().setName(title);
		song.getTrack().setArtist(artist);
		song.getTrack().setAlbum(album);
		song.setCoverPath(cover);

		this.frame.list.getSongs().set(frame.currentIndex, song);
		this.frame.currentSong = song;

//		Backend.updateSongInfo(frame, this.frame.currentIndex);
		Backend.refreshList(frame);

		this.dispose();
    }//GEN-LAST:event_applyButtonActionPerformed

    private void removeAlbumCoverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAlbumCoverButtonActionPerformed
		Queries.updateLocalCoverPath(song.getTrack().getAlbum(), song.getTrack().getArtist(), "");
    }//GEN-LAST:event_removeAlbumCoverButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField albumCoverTextField;
    private javax.swing.JTextField albumTextField;
    private javax.swing.JButton applyButton;
    private javax.swing.JTextField artistTextField;
    private javax.swing.JPanel background;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton removeAlbumCoverButton;
    private javax.swing.JTextField titleTextField;
    // End of variables declaration//GEN-END:variables
}
