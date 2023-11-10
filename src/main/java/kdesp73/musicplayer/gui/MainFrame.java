/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kdesp73.musicplayer.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import kdesp73.musicplayer.player.AudioPlayer;
import kdesp73.musicplayer.songs.Mp3File;
import kdesp73.musicplayer.songs.SongsList;
import kdesp73.musicplayer.backend.Backend;
import kdesp73.musicplayer.backend.UIFunctionality;
import static kdesp73.musicplayer.backend.UIFunctionality.playSong;
import static kdesp73.musicplayer.backend.UIFunctionality.showOptionsPopup;
import kdesp73.musicplayer.player.Mp3Player;
import javax.swing.JSplitPane;

/**
 *
 * @author konstantinos
 */
public final class MainFrame extends javax.swing.JFrame {

	private String accentColor = "2979ff";
	private String project_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/");
	private String themes_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/";
	private String assets_path = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/";

	public int currentIndex = 0;
	public Mp3File currentSong = null;
	public SongsList list;

	public boolean scrapeAtStart = false;
	public boolean shuffleOn = false;
	public boolean repeatOn = false;
	public boolean volumeOn = true;
	
	public int volume = 100;

	public Mp3Player player;

	public static ThemesFrame themesFrame;
	public static AboutFrame aboutFrame;
	public static EditDirectoriesFrame editDirectoriesFrame;
	public static EditSongInfoFrame editSongFrame;
	public static EditFilesFrame editFilesFrame;
	public static HelpFrame helpFrame;

	public static MainFrame create() {
		MainFrame frame = new MainFrame();
		Backend.setMainFrame(frame);
		Backend.setup(frame);

		themesFrame = new ThemesFrame(frame);
		aboutFrame = new AboutFrame();
		editDirectoriesFrame = new EditDirectoriesFrame(frame);
		editSongFrame = new EditSongInfoFrame(frame);
		editFilesFrame = new EditFilesFrame(frame);
		helpFrame = new HelpFrame();

		return frame;
	}

	public MainFrame() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        background = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, tabbedPane);
        tabbedPane = new javax.swing.JTabbedPane();
        playerBackground = new javax.swing.JPanel();
        controlsParentPanel = new javax.swing.JLayeredPane();
        centralPanel = new javax.swing.JPanel();
        controlsPanel = new RoundedPanel(30);
        playPauseLabel = new javax.swing.JLabel();
        prevLabel = new javax.swing.JLabel();
        nextLabel = new javax.swing.JLabel();
        repeatLabel = new javax.swing.JLabel();
        shuffleLabel = new javax.swing.JLabel();
        volumeToggleLabel = new javax.swing.JLabel();
        basicInfoPanel = new RoundedPanel();
        trackLabel = new javax.swing.JLabel();
        artistLabel = new javax.swing.JLabel();
        sliderPanel = new RoundedPanel();
        timeLabel = new javax.swing.JLabel();
        slider = new javax.swing.JSlider();
        durationLabel = new javax.swing.JLabel();
        volumePanel = new RoundedPanel();
        volumeSlider = new javax.swing.JSlider();
        albumImageLabel = new javax.swing.JLabel();
        infoBackground = new javax.swing.JPanel();
        infoTabbedPane = new javax.swing.JTabbedPane();
        artistInfoPanel = new javax.swing.JPanel();
        artistImageLabel = new javax.swing.JLabel();
        artistNameLabel = new javax.swing.JLabel();
        artistContentScrollPane = new javax.swing.JScrollPane();
        artistContentTextArea = new javax.swing.JTextArea();
        tagsContainer = new RoundedPanel();
        albumInfoPanel = new javax.swing.JPanel();
        albumCoverInfoLabel = new javax.swing.JLabel();
        albumNameLabel = new javax.swing.JLabel();
        albumContentScrollPane = new javax.swing.JScrollPane();
        albumContentTextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        albumTracksList = new javax.swing.JList<>();
        leftPanel = new javax.swing.JPanel();
        sortComboBox = new javax.swing.JComboBox<>();
        sortbyLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        songsList = new javax.swing.JList<>();
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
        themesMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg_2"); // NOI18N
        background.setPreferredSize(new java.awt.Dimension(500, 300));

        tabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        playerBackground.setName("bg"); // NOI18N

        controlsParentPanel.setBackground(new java.awt.Color(51, 51, 51));
        controlsParentPanel.setName("bg"); // NOI18N

        centralPanel.setName("bg"); // NOI18N

        controlsPanel.setName("extra_0"); // NOI18N

        playPauseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playPauseLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playPauseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playPauseLabelMouseClicked(evt);
            }
        });

        prevLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prevLabel.setToolTipText("");
        prevLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prevLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                prevLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                prevLabelMouseReleased(evt);
            }
        });

        nextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextLabel.setToolTipText("");
        nextLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nextLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nextLabelMouseReleased(evt);
            }
        });

        repeatLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        repeatLabel.setToolTipText("");
        repeatLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        repeatLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                repeatLabelMouseClicked(evt);
            }
        });

        shuffleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shuffleLabel.setToolTipText("");
        shuffleLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        shuffleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shuffleLabelMouseClicked(evt);
            }
        });

        volumeToggleLabel.setToolTipText("Mute");
        volumeToggleLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volumeToggleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeToggleLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                volumeToggleLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(shuffleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(prevLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playPauseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(repeatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(volumeToggleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(playPauseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(shuffleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(nextLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prevLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(repeatLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(volumeToggleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );

        basicInfoPanel.setName("extra_0"); // NOI18N

        trackLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        trackLabel.setText("Track");
        trackLabel.setName("fg_2"); // NOI18N

        artistLabel.setText("Artist");
        artistLabel.setName("fg_2"); // NOI18N

        javax.swing.GroupLayout basicInfoPanelLayout = new javax.swing.GroupLayout(basicInfoPanel);
        basicInfoPanel.setLayout(basicInfoPanelLayout);
        basicInfoPanelLayout.setHorizontalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addComponent(trackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        basicInfoPanelLayout.setVerticalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trackLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(artistLabel)
                .addContainerGap())
        );

        sliderPanel.setName("extra_0"); // NOI18N

        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("00:00");

        slider.setValue(0);
        slider.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        slider.setName("slider"); // NOI18N
        slider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sliderMouseDragged(evt);
            }
        });
        slider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sliderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sliderMouseReleased(evt);
            }
        });

        durationLabel.setText("00:00");

        javax.swing.GroupLayout sliderPanelLayout = new javax.swing.GroupLayout(sliderPanel);
        sliderPanel.setLayout(sliderPanelLayout);
        sliderPanelLayout.setHorizontalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(durationLabel)
                .addGap(12, 12, 12))
        );
        sliderPanelLayout.setVerticalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(durationLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout centralPanelLayout = new javax.swing.GroupLayout(centralPanel);
        centralPanel.setLayout(centralPanelLayout);
        centralPanelLayout.setHorizontalGroup(
            centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(basicInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sliderPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controlsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        centralPanelLayout.setVerticalGroup(
            centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(basicInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        volumePanel.setAutoscrolls(true);
        volumePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        volumePanel.setName("extra_0"); // NOI18N

        volumeSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        volumeSlider.setValue(100);
        volumeSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        volumeSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                volumeSliderMouseDragged(evt);
            }
        });
        volumeSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                volumeSliderMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout volumePanelLayout = new javax.swing.GroupLayout(volumePanel);
        volumePanel.setLayout(volumePanelLayout);
        volumePanelLayout.setHorizontalGroup(
            volumePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(volumePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        volumePanelLayout.setVerticalGroup(
            volumePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(volumePanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        controlsParentPanel.setLayer(centralPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        controlsParentPanel.setLayer(volumePanel, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout controlsParentPanelLayout = new javax.swing.GroupLayout(controlsParentPanel);
        controlsParentPanel.setLayout(controlsParentPanelLayout);
        controlsParentPanelLayout.setHorizontalGroup(
            controlsParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlsParentPanelLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addComponent(centralPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(volumePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );
        controlsParentPanelLayout.setVerticalGroup(
            controlsParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(centralPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(controlsParentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volumePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        albumImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        albumImageLabel.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout playerBackgroundLayout = new javax.swing.GroupLayout(playerBackground);
        playerBackground.setLayout(playerBackgroundLayout);
        playerBackgroundLayout.setHorizontalGroup(
            playerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlsParentPanel)
            .addGroup(playerBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(albumImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        playerBackgroundLayout.setVerticalGroup(
            playerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(albumImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(controlsParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane.addTab("Player", playerBackground);

        infoBackground.setName("bg"); // NOI18N

        artistInfoPanel.setName("bg"); // NOI18N

        artistImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        artistNameLabel.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        artistNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        artistNameLabel.setText("Artist Name");
        artistNameLabel.setName("fg"); // NOI18N

        artistContentScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        artistContentTextArea.setEditable(false);
        artistContentTextArea.setColumns(20);
        artistContentTextArea.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        artistContentTextArea.setLineWrap(true);
        artistContentTextArea.setRows(5);
        artistContentTextArea.setWrapStyleWord(true);
        artistContentTextArea.setName("textbox"); // NOI18N
        artistContentScrollPane.setViewportView(artistContentTextArea);

        tagsContainer.setName("bg_2"); // NOI18N
        tagsContainer.setPreferredSize(new java.awt.Dimension(55, 95));

        javax.swing.GroupLayout tagsContainerLayout = new javax.swing.GroupLayout(tagsContainer);
        tagsContainer.setLayout(tagsContainerLayout);
        tagsContainerLayout.setHorizontalGroup(
            tagsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tagsContainerLayout.setVerticalGroup(
            tagsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout artistInfoPanelLayout = new javax.swing.GroupLayout(artistInfoPanel);
        artistInfoPanel.setLayout(artistInfoPanelLayout);
        artistInfoPanelLayout.setHorizontalGroup(
            artistInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(artistInfoPanelLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(artistInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(artistNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addComponent(artistImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tagsContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(artistContentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        artistInfoPanelLayout.setVerticalGroup(
            artistInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(artistInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistContentScrollPane))
            .addGroup(artistInfoPanelLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(artistImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(artistNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tagsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        infoTabbedPane.addTab("Artist", artistInfoPanel);

        albumInfoPanel.setName("bg"); // NOI18N

        albumCoverInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        albumNameLabel.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        albumNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        albumNameLabel.setText("Album Name");
        albumNameLabel.setName("fg"); // NOI18N

        albumContentTextArea.setEditable(false);
        albumContentTextArea.setColumns(20);
        albumContentTextArea.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        albumContentTextArea.setLineWrap(true);
        albumContentTextArea.setRows(5);
        albumContentTextArea.setWrapStyleWord(true);
        albumContentTextArea.setName("textbox"); // NOI18N
        albumContentScrollPane.setViewportView(albumContentTextArea);

        albumTracksList.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        albumTracksList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        albumTracksList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        albumTracksList.setToolTipText("Tracks");
        albumTracksList.setName("list"); // NOI18N
        jScrollPane5.setViewportView(albumTracksList);

        javax.swing.GroupLayout albumInfoPanelLayout = new javax.swing.GroupLayout(albumInfoPanel);
        albumInfoPanel.setLayout(albumInfoPanelLayout);
        albumInfoPanelLayout.setHorizontalGroup(
            albumInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(albumInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(albumInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(albumNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                    .addComponent(albumCoverInfoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(albumContentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        albumInfoPanelLayout.setVerticalGroup(
            albumInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(albumInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(albumInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(albumContentScrollPane)
                    .addGroup(albumInfoPanelLayout.createSequentialGroup()
                        .addGap(0, 69, Short.MAX_VALUE)
                        .addComponent(albumCoverInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(albumNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addContainerGap())
        );

        infoTabbedPane.addTab("Album", albumInfoPanel);

        javax.swing.GroupLayout infoBackgroundLayout = new javax.swing.GroupLayout(infoBackground);
        infoBackground.setLayout(infoBackgroundLayout);
        infoBackgroundLayout.setHorizontalGroup(
            infoBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(infoTabbedPane)
        );
        infoBackgroundLayout.setVerticalGroup(
            infoBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoBackgroundLayout.createSequentialGroup()
                .addComponent(infoTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Info", infoBackground);

        leftPanel.setName("bg"); // NOI18N

        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Artist", "Album" }));
        sortComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortComboBoxActionPerformed(evt);
            }
        });

        sortbyLabel.setText("Sort By:");
        sortbyLabel.setName("fg"); // NOI18N

        searchButton.setText("Search");
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        songsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        songsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        songsList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        songsList.setMaximumSize(new java.awt.Dimension(1000, 1000));
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
        jScrollPane2.setViewportView(songsList);

        jScrollPane3.setViewportView(jScrollPane2);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(sortbyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortbyLabel)
                    .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3))
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        scrapeAtStartMenuItem.setText("Scrape At Startup");
        scrapeAtStartMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scrapeAtStartMenuItemActionPerformed(evt);
            }
        });
        ApiMenu.add(scrapeAtStartMenuItem);

        menuBar.add(ApiMenu);

        themesMenu.setText("Themes");
        themesMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                themesMenuMousePressed(evt);
            }
        });
        menuBar.add(themesMenu);

        helpMenu.setText("Help");

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText("About");
        aboutMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMenuItemMouseClicked(evt);
            }
        });
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 1290, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void addFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileMenuItemActionPerformed
		UIFunctionality.addFile(this);
    }//GEN-LAST:event_addFileMenuItemActionPerformed

    private void addDirectoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDirectoryMenuItemActionPerformed
		UIFunctionality.addDirectory(this);
    }//GEN-LAST:event_addDirectoryMenuItemActionPerformed

    private void sortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortComboBoxActionPerformed
		UIFunctionality.sortComboBoxActionPerformed(this);
    }//GEN-LAST:event_sortComboBoxActionPerformed

    private void editDirectoriesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDirectoriesMenuItemActionPerformed
		UIFunctionality.editDirectories(this);
    }//GEN-LAST:event_editDirectoriesMenuItemActionPerformed

    private void editFilesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFilesMenuItemActionPerformed
		UIFunctionality.editFiles(this);
    }//GEN-LAST:event_editFilesMenuItemActionPerformed

    private void songsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songsListMouseClicked
		if (evt.getButton() == MouseEvent.BUTTON3 && songsList.getSelectedIndex() >= 0) {
			showOptionsPopup(this, evt);
		} else if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
			playSong(this);
		}
    }//GEN-LAST:event_songsListMouseClicked

    private void songsListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_songsListKeyPressed
		if (evt.getKeyChar() == '\n') {
			playSong(this);
		}
    }//GEN-LAST:event_songsListKeyPressed

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}

		UIFunctionality.search(this);
    }//GEN-LAST:event_searchButtonMouseClicked

    private void scrapeAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrapeAllMenuItemActionPerformed
		UIFunctionality.scrapeAll(this);
    }//GEN-LAST:event_scrapeAllMenuItemActionPerformed

    private void scrapeAtStartMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrapeAtStartMenuItemActionPerformed
		UIFunctionality.scrapeAtStartToggle(this);
    }//GEN-LAST:event_scrapeAtStartMenuItemActionPerformed

    private void sliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMouseDragged
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.sliderMouseDragged(this);
    }//GEN-LAST:event_sliderMouseDragged

    private void sliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMouseReleased
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.sliderMouseReleased(this);
    }//GEN-LAST:event_sliderMouseReleased

    private void sliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMousePressed
		if (evt.getButton() == MouseEvent.BUTTON1) {
//			player.pause();
		}
    }//GEN-LAST:event_sliderMousePressed

    private void playPauseLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playPauseLabelMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.togglePlayPause(this);
    }//GEN-LAST:event_playPauseLabelMouseClicked

    private void nextLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabelMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.nextAction(this);
    }//GEN-LAST:event_nextLabelMouseClicked

    private void prevLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevLabelMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.prevAction(this);
    }//GEN-LAST:event_prevLabelMouseClicked

    private void shuffleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shuffleLabelMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.shuffle(this);
    }//GEN-LAST:event_shuffleLabelMouseClicked

    private void repeatLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repeatLabelMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.repeat(this);
    }//GEN-LAST:event_repeatLabelMouseClicked

    private void themesMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themesMenuMousePressed
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}

		if (themesFrame.isActive()) {
			return;
		}

		themesFrame.setVisible(true);
    }//GEN-LAST:event_themesMenuMousePressed

    private void prevLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevLabelMousePressed
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.prevLabelMousePressed(this);
    }//GEN-LAST:event_prevLabelMousePressed

    private void prevLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevLabelMouseReleased
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.prevLabelMouseReleased(this);

    }//GEN-LAST:event_prevLabelMouseReleased

    private void nextLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabelMousePressed
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}

		UIFunctionality.nextLabelMousePressed(this);
    }//GEN-LAST:event_nextLabelMousePressed

    private void nextLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabelMouseReleased
		if (evt.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		UIFunctionality.nextLabelMouseReleased(this);
    }//GEN-LAST:event_nextLabelMouseReleased

    private void volumeToggleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeToggleLabelMouseClicked
		UIFunctionality.volumeToggle(this);
    }//GEN-LAST:event_volumeToggleLabelMouseClicked

    private void volumeSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeSliderMouseReleased
    }//GEN-LAST:event_volumeSliderMouseReleased

    private void volumeSliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeSliderMouseDragged
		Backend.changeVolume(this);
    }//GEN-LAST:event_volumeSliderMouseDragged

    private void volumeToggleLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeToggleLabelMousePressed
		UIFunctionality.volumeToggleLabelMousePressed(this);
    }//GEN-LAST:event_volumeToggleLabelMousePressed

    private void aboutMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMenuItemMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON1 || aboutFrame.isActive()) {
			return;
		}

		aboutFrame.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemMouseClicked

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
		if (aboutFrame.isShowing()) {
			return;
		}
		MainFrame.aboutFrame = new AboutFrame();
		aboutFrame.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
		if (helpFrame.isShowing()) {
			return;
		}
		MainFrame.helpFrame = new HelpFrame();
		helpFrame.setVisible(true);
    }//GEN-LAST:event_helpMenuItemActionPerformed

	// Getters
	public String getProject_path() {
		return project_path;
	}

	public String getThemes_path() {
		return themes_path;
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

	public JLabel getArtistLabel() {
		return artistLabel;
	}

	public JPanel getBasicInfoPanel() {
		return basicInfoPanel;
	}

	public JLayeredPane getControlsParentPanel() {
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
		return helpMenu;
	}

	public JMenuItem getjMenuItem1() {
		return editFilesMenuItem;
	}

	public JScrollPane getjScrollPane2() {
		return jScrollPane2;
	}

	public JPanel getPlayerBackground() {
		return playerBackground;
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

	public JLabel getArtistImageLabel() {
		return artistImageLabel;
	}

	public JLabel getArtistNameLabel() {
		return artistNameLabel;
	}

	public JTextArea getArtistContentTextArea() {
		return artistContentTextArea;
	}

	public JTextArea getAlbumContentTextArea() {
		return albumContentTextArea;
	}

	public JLabel getAlbumCoverInfoLabel() {
		return albumCoverInfoLabel;
	}

	public JPanel getAlbumInfoPanel() {
		return albumInfoPanel;
	}

	public JLabel getAlbumNameLabel() {
		return albumNameLabel;
	}

	public JList<String> getAlbumTracksList() {
		return albumTracksList;
	}

	public JScrollPane getAlbumContentScrollPane() {
		return albumContentScrollPane;
	}

	public JScrollPane getArtistContentScrollPane() {
		return artistContentScrollPane;
	}

	public JLabel getDurationLabel() {
		return durationLabel;
	}

	public JLabel getPlayPauseLabel() {
		return playPauseLabel;
	}

	public JLabel getNextLabel() {
		return nextLabel;
	}

	public JLabel getPrevLabel() {
		return prevLabel;
	}

	public JLabel getRepeatLabel() {
		return repeatLabel;
	}

	public JLabel getShuffleLabel() {
		return shuffleLabel;
	}

	public JPanel getTagsContainer() {
		return tagsContainer;
	}

	public JSplitPane getBackgroundSplitPane() {
		return background;
	}

	public JLabel getVolumeToggleLabel() {
		return volumeToggleLabel;
	}

	public JPanel getCentralPanel() {
		return centralPanel;
	}

	public JPanel getVolumePanel() {
		return volumePanel;
	}

	public JSlider getVolumeSlider() {
		return volumeSlider;
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ApiMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addDirectoryMenuItem;
    private javax.swing.JMenuItem addFileMenuItem;
    private javax.swing.JScrollPane albumContentScrollPane;
    private javax.swing.JTextArea albumContentTextArea;
    private javax.swing.JLabel albumCoverInfoLabel;
    private javax.swing.JLabel albumImageLabel;
    private javax.swing.JPanel albumInfoPanel;
    private javax.swing.JLabel albumNameLabel;
    private javax.swing.JList<String> albumTracksList;
    private javax.swing.JScrollPane artistContentScrollPane;
    private javax.swing.JTextArea artistContentTextArea;
    private javax.swing.JLabel artistImageLabel;
    private javax.swing.JPanel artistInfoPanel;
    private javax.swing.JLabel artistLabel;
    private javax.swing.JLabel artistNameLabel;
    private javax.swing.JSplitPane background;
    private javax.swing.JPanel basicInfoPanel;
    private javax.swing.JPanel centralPanel;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JLayeredPane controlsParentPanel;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JMenuItem editDirectoriesMenuItem;
    private javax.swing.JMenuItem editFilesMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JPanel infoBackground;
    private javax.swing.JTabbedPane infoTabbedPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nextLabel;
    private javax.swing.JLabel playPauseLabel;
    private javax.swing.JPanel playerBackground;
    private javax.swing.JLabel prevLabel;
    private javax.swing.JLabel repeatLabel;
    private javax.swing.JMenuItem scrapeAllMenuItem;
    private javax.swing.JCheckBoxMenuItem scrapeAtStartMenuItem;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel shuffleLabel;
    private javax.swing.JSlider slider;
    private javax.swing.JPanel sliderPanel;
    private javax.swing.JList<String> songsList;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JLabel sortbyLabel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel tagsContainer;
    private javax.swing.JMenu themesMenu;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel trackLabel;
    private javax.swing.JPanel volumePanel;
    private javax.swing.JSlider volumeSlider;
    private javax.swing.JLabel volumeToggleLabel;
    // End of variables declaration//GEN-END:variables
}
