/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kdesp73.musicplayer.gui;

import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import kdesp73.musicplayer.player.AudioPlayer;
import kdesp73.musicplayer.songs.Mp3File;
import kdesp73.musicplayer.songs.SongsList;
import kdesp73.musicplayer.backend.Backend;
import kdesp73.musicplayer.backend.UIFunctionality;
import kdesp73.musicplayer.player.Mp3Player;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.YamlFile;

/**
 *
 * @author konstantinos
 */
public final class MainFrame extends javax.swing.JFrame {

	private String project_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/");
	private String themes_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/";
	private Theme theme = new Theme(new YamlFile(themes_path + "kdesp.yml"));

	public int currentIndex = 0;
	public Mp3File currentSong = null;
	public SongsList list;
	public boolean scrapeAtStart = false;

	public Mp3Player player;

	public static MainFrame create(){
		MainFrame frame = new MainFrame();
		Backend.setMainFrame(frame);
		Backend.setup(frame);
		return frame;
	}
	
	public MainFrame() {
		initComponents();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        songsList = new javax.swing.JList<>();
        sortComboBox = new javax.swing.JComboBox<>();
        sortbyLabel = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        playerBackground = new javax.swing.JPanel();
        controlsParentPanel = new RoundedPanel();
        sliderPanel = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        slider = new javax.swing.JSlider();
        controlsPanel = new javax.swing.JPanel();
        playButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        basicInfoPanel = new javax.swing.JPanel();
        trackLabel = new javax.swing.JLabel();
        artistLabel = new javax.swing.JLabel();
        albumLabel = new javax.swing.JLabel();
        optionsLabel = new javax.swing.JLabel();
        albumImageLabel = new javax.swing.JLabel();
        infoBackground = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        addFileMenuItem = new javax.swing.JMenuItem();
        addDirectoryMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editFilesMenuItem = new javax.swing.JMenuItem();
        editDirectoriesMenuItem = new javax.swing.JMenuItem();
        ApiMenu = new javax.swing.JMenu();
        scrapeAllMenuItem = new javax.swing.JMenuItem();
        scrapeAtStartMenuItem = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg"); // NOI18N

        songsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        songsList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        songsList.setName("list"); // NOI18N
        songsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                songsListMouseClicked(evt);
            }
        });
        songsList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                songsListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(songsList);

        jScrollPane2.setViewportView(jScrollPane1);

        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Artist", "Album" }));
        sortComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortComboBoxActionPerformed(evt);
            }
        });

        sortbyLabel.setText("Sort By:");
        sortbyLabel.setName("fg"); // NOI18N

        tabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        controlsParentPanel.setBackground(new java.awt.Color(153, 0, 51));

        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("00:00");

        slider.setValue(0);
        slider.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        slider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sliderMouseDragged(evt);
            }
        });
        slider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sliderMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout sliderPanelLayout = new javax.swing.GroupLayout(sliderPanel);
        sliderPanel.setLayout(sliderPanelLayout);
        sliderPanelLayout.setHorizontalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        sliderPanelLayout.setVerticalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        playButton.setText("Play");
        playButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playButtonMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playButtonMouseReleased(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        prevButton.setText("Prev");
        prevButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prevButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(prevButton)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(playButton)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(nextButton)
                .addGap(11, 11, 11))
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(nextButton)
                    .addComponent(prevButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        trackLabel.setText("Track");

        artistLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        artistLabel.setText("Artist");

        albumLabel.setText("Album");

        javax.swing.GroupLayout basicInfoPanelLayout = new javax.swing.GroupLayout(basicInfoPanel);
        basicInfoPanel.setLayout(basicInfoPanelLayout);
        basicInfoPanelLayout.setHorizontalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addComponent(albumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addComponent(trackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(artistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        basicInfoPanelLayout.setVerticalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trackLabel)
                    .addComponent(artistLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(albumLabel)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        optionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optionsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        optionsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optionsLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout controlsParentPanelLayout = new javax.swing.GroupLayout(controlsParentPanel);
        controlsParentPanel.setLayout(controlsParentPanelLayout);
        controlsParentPanelLayout.setHorizontalGroup(
            controlsParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsParentPanelLayout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(controlsParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sliderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(basicInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(controlsParentPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(optionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        controlsParentPanelLayout.setVerticalGroup(
            controlsParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsParentPanelLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(basicInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(controlsParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(controlsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        albumImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        albumImageLabel.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout playerBackgroundLayout = new javax.swing.GroupLayout(playerBackground);
        playerBackground.setLayout(playerBackgroundLayout);
        playerBackgroundLayout.setHorizontalGroup(
            playerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(albumImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(controlsParentPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        playerBackgroundLayout.setVerticalGroup(
            playerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerBackgroundLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(albumImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(controlsParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        tabbedPane.addTab("Player", playerBackground);

        javax.swing.GroupLayout infoBackgroundLayout = new javax.swing.GroupLayout(infoBackground);
        infoBackground.setLayout(infoBackgroundLayout);
        infoBackgroundLayout.setHorizontalGroup(
            infoBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        infoBackgroundLayout.setVerticalGroup(
            infoBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Info", infoBackground);

        searchButton.setText("Search");
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sortbyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sortbyLabel)
                            .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(tabbedPane)))
        );

        fileMenu.setText("File");

        addFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addFileMenuItem.setText("Add File");
        addFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(addFileMenuItem);

        addDirectoryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addDirectoryMenuItem.setText("Add Directory");
        addDirectoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDirectoryMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(addDirectoryMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        editFilesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        editFilesMenuItem.setText("Edit Files");
        editFilesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFilesMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editFilesMenuItem);

        editDirectoriesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        editDirectoriesMenuItem.setText("Edit Directories");
        editDirectoriesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDirectoriesMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editDirectoriesMenuItem);

        menuBar.add(editMenu);

        ApiMenu.setText("Api");

        scrapeAllMenuItem.setText("Scrape All");
        scrapeAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scrapeAllMenuItemActionPerformed(evt);
            }
        });
        ApiMenu.add(scrapeAllMenuItem);

        scrapeAtStartMenuItem.setSelected(true);
        scrapeAtStartMenuItem.setText("Scrape At Start");
        scrapeAtStartMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scrapeAtStartMenuItemActionPerformed(evt);
            }
        });
        ApiMenu.add(scrapeAtStartMenuItem);

        menuBar.add(ApiMenu);

        jMenu1.setText("About");
        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

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


    private void addFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileMenuItemActionPerformed
		UIFunctionality.addFileMenuItemActionPerformed(this);
    }//GEN-LAST:event_addFileMenuItemActionPerformed

    private void addDirectoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDirectoryMenuItemActionPerformed
		UIFunctionality.addDirectoryMenuItemActionPerformed(this);
    }//GEN-LAST:event_addDirectoryMenuItemActionPerformed

    private void sortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortComboBoxActionPerformed
		UIFunctionality.sortComboBoxActionPerformed(this);
    }//GEN-LAST:event_sortComboBoxActionPerformed

    private void editDirectoriesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDirectoriesMenuItemActionPerformed
		UIFunctionality.editDirectoriesMenuItemActionPerformed(this);
    }//GEN-LAST:event_editDirectoriesMenuItemActionPerformed

    private void editFilesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFilesMenuItemActionPerformed
		UIFunctionality.editFilesMenuItemActionPerformed(this);
    }//GEN-LAST:event_editFilesMenuItemActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
		UIFunctionality.nextButtonActionPerformed(this);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void songsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songsListMouseClicked
		UIFunctionality.songsListMouseClicked(this, evt);
    }//GEN-LAST:event_songsListMouseClicked

    private void playButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseClicked
//		UIFunctionality.playButtonMouseClicked(this);
    }//GEN-LAST:event_playButtonMouseClicked

    private void songsListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_songsListKeyPressed
		UIFunctionality.songsListKeyPressed(this, evt);
    }//GEN-LAST:event_songsListKeyPressed

    private void prevButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevButtonMouseClicked
		UIFunctionality.prevButtonMouseClicked(this);
    }//GEN-LAST:event_prevButtonMouseClicked

    private void optionsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsLabelMouseClicked
		UIFunctionality.optionsLabelMouseClicked(this, evt);
    }//GEN-LAST:event_optionsLabelMouseClicked

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
		UIFunctionality.searchButtonMouseClicked(this);
    }//GEN-LAST:event_searchButtonMouseClicked

    private void scrapeAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrapeAllMenuItemActionPerformed
		UIFunctionality.scrapeAllMenuItemActionPerformed(this);
    }//GEN-LAST:event_scrapeAllMenuItemActionPerformed

    private void scrapeAtStartMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrapeAtStartMenuItemActionPerformed
		UIFunctionality.scrapeAtStartMenuItemActionPerformed(this);
    }//GEN-LAST:event_scrapeAtStartMenuItemActionPerformed

    private void sliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMouseDragged
        UIFunctionality.sliderMouseDragged(this);
    }//GEN-LAST:event_sliderMouseDragged

    private void sliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMouseReleased
        UIFunctionality.sliderMouseReleased(this);
    }//GEN-LAST:event_sliderMouseReleased

    private void playButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseReleased
        UIFunctionality.playButtonMouseClicked(this);
    }//GEN-LAST:event_playButtonMouseReleased

	// Getters
	
	public String getProject_path() {
		return project_path;
	}

	public String getThemes_path() {
		return themes_path;
	}

	public Theme getTheme() {
		return theme;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public Mp3File getCurrentSong() {
		return currentSong;
	}

	public SongsList getList() {
		return list;
	}

	public boolean isScrapeAtStart() {
		return scrapeAtStart;
	}

	public AudioPlayer getPlayer() {
		return player;
	}

	public JMenu getApiMenu() {
		return ApiMenu;
	}

	public JMenuItem getAddDirectoryMenuItem() {
		return addDirectoryMenuItem;
	}

	public JMenuItem getAddFileMenuItem() {
		return addFileMenuItem;
	}

	public JLabel getAlbumImageLabel() {
		return albumImageLabel;
	}

	public JLabel getAlbumLabel() {
		return albumLabel;
	}

	public JLabel getArtistLabel() {
		return artistLabel;
	}

	public JPanel getBasicInfoPanel() {
		return basicInfoPanel;
	}

	public JPanel getControlsPanel() {
		return controlsPanel;
	}

	public JPanel getControlsParentPanel() {
		return controlsParentPanel;
	}

	public JMenuItem getEditDirectoriesMenuItem() {
		return editDirectoriesMenuItem;
	}

	public JMenu getEditMenu() {
		return editMenu;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JPanel getInfoBackground() {
		return infoBackground;
	}

	public JMenu getjMenu1() {
		return jMenu1;
	}

	public JMenuItem getjMenuItem1() {
		return editFilesMenuItem;
	}

	public JScrollPane getjScrollPane1() {
		return jScrollPane1;
	}

	public JScrollPane getjScrollPane2() {
		return jScrollPane2;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public JLabel getOptionsLabel() {
		return optionsLabel;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public JPanel getPlayerBackground() {
		return playerBackground;
	}

	public JButton getPrevButton() {
		return prevButton;
	}

	public JMenuItem getScrapeAllMenuItem() {
		return scrapeAllMenuItem;
	}

	public JCheckBoxMenuItem getScrapeAtStartMenuItem() {
		return scrapeAtStartMenuItem;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JSlider getSlider() {
		return slider;
	}

	public JPanel getSliderPanel() {
		return sliderPanel;
	}

	public JList<String> getSongsList() {
		return songsList;
	}

	public JComboBox<String> getSortComboBox() {
		return sortComboBox;
	}

	public JLabel getSortbyLabel() {
		return sortbyLabel;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JLabel getTimeLabel() {
		return timeLabel;
	}

	public JLabel getTrackLabel() {
		return trackLabel;
	}
	
	public JRootPane getRootPane(){
		return this.rootPane;
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ApiMenu;
    private javax.swing.JMenuItem addDirectoryMenuItem;
    private javax.swing.JMenuItem addFileMenuItem;
    private javax.swing.JLabel albumImageLabel;
    private javax.swing.JLabel albumLabel;
    private javax.swing.JLabel artistLabel;
    private javax.swing.JPanel background;
    private javax.swing.JPanel basicInfoPanel;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JPanel controlsParentPanel;
    private javax.swing.JMenuItem editDirectoriesMenuItem;
    private javax.swing.JMenuItem editFilesMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel infoBackground;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel optionsLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JPanel playerBackground;
    private javax.swing.JButton prevButton;
    private javax.swing.JMenuItem scrapeAllMenuItem;
    private javax.swing.JCheckBoxMenuItem scrapeAtStartMenuItem;
    private javax.swing.JButton searchButton;
    private javax.swing.JSlider slider;
    private javax.swing.JPanel sliderPanel;
    private javax.swing.JList<String> songsList;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JLabel sortbyLabel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel trackLabel;
    // End of variables declaration//GEN-END:variables
}
