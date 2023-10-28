/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kdesp73.musicplayer.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.musicplayer.player.AudioPlayer;
import kdesp73.musicplayer.Mp3File;
import kdesp73.musicplayer.player.Mp3Player;
import kdesp73.musicplayer.SongsList;
import kdesp73.musicplayer.api.API;
import kdesp73.musicplayer.api.Album;
import kdesp73.musicplayer.api.Artist;
import kdesp73.musicplayer.api.LastFmMethods;
import kdesp73.musicplayer.api.Pair;
import kdesp73.musicplayer.api.Search;
import kdesp73.musicplayer.api.SearchTrack;
import kdesp73.musicplayer.api.Track;
import kdesp73.musicplayer.db.Database;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.files.FileOperations;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.YamlFile;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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

	public AudioPlayer player = new Mp3Player(null);

	/**
	 * Creates new form MainFrame
	 */
	public MainFrame() {
		initComponents();

		setLocationRelativeTo(null);
		this.rootPane.requestFocus();

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Queries.updateLastPlayed(currentIndex);
			}

		});

		updateSongs();

		if (!list.getSongs().isEmpty()) {
			currentIndex = Queries.getLastPlayed();
			currentSong = list.getSongs().get(currentIndex);
			selectSong(currentIndex);
			player = new Mp3Player(currentSong);
		}

		selectSong(currentIndex);
		

		songsList.setFixedCellHeight(35);

		GUIMethods.loadImage(optionsLabel, project_path + "/assets/ellipsis-vertical-solid-small.png");

		sortComboBox.setSelectedItem(Queries.getSortBy());
	}

	private void addExtensions() {
		FileOperations.acceptedExtensions.add("mp3");
		FileOperations.acceptedExtensions.add("wav");
		FileOperations.acceptedExtensions.add("mpeg");
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
        controlsParentPanel = new javax.swing.JPanel();
        sliderPanel = new javax.swing.JPanel();
        slider = new javax.swing.JScrollBar();
        timeLabel = new javax.swing.JLabel();
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
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        addFileMenuItem = new javax.swing.JMenuItem();
        addDirectoryMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        editDirectoriesMenuItem = new javax.swing.JMenuItem();
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

        slider.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        slider.setVisibleAmount(1);
        slider.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        slider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sliderMouseDragged(evt);
            }
        });

        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("00:00");

        javax.swing.GroupLayout sliderPanelLayout = new javax.swing.GroupLayout(sliderPanel);
        sliderPanel.setLayout(sliderPanelLayout);
        sliderPanelLayout.setHorizontalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        sliderPanelLayout.setVerticalGroup(
            sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sliderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sliderPanelLayout.createSequentialGroup()
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        playButton.setText("Play");
        playButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playButtonMouseClicked(evt);
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
                        .addComponent(artistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(controlsParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sliderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(basicInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(controlsParentPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(optionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout playerBackgroundLayout = new javax.swing.GroupLayout(playerBackground);
        playerBackground.setLayout(playerBackgroundLayout);
        playerBackgroundLayout.setHorizontalGroup(
            playerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerBackgroundLayout.createSequentialGroup()
                .addContainerGap(252, Short.MAX_VALUE)
                .addGroup(playerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(controlsParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(albumImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        playerBackgroundLayout.setVerticalGroup(
            playerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerBackgroundLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(albumImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(controlsParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        tabbedPane.addTab("Player", playerBackground);

        javax.swing.GroupLayout infoBackgroundLayout = new javax.swing.GroupLayout(infoBackground);
        infoBackground.setLayout(infoBackgroundLayout);
        infoBackgroundLayout.setHorizontalGroup(
            infoBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 895, Short.MAX_VALUE)
        );
        infoBackgroundLayout.setVerticalGroup(
            infoBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Info", infoBackground);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
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
                            .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem1.setText("Edit Files");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem1);

        editDirectoriesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        editDirectoriesMenuItem.setText("Edit Directories");
        editDirectoriesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDirectoriesMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editDirectoriesMenuItem);

        menuBar.add(editMenu);

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

	public void updateSongs() {
		addExtensions();
		initList();

		switch (Queries.getSortBy()) {
			case "Name":
				list.sortByName();
				break;
			case "Artist":
				list.sortByArtist();
				break;
			case "Album":
				list.sortByAlbum();
				break;
			default:
				throw new AssertionError();
		}
		refreshList();
	}

	private void initList() {
		try {
			list = new SongsList(Queries.getDirectories(), Queries.getFiles());
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void refreshList() {

		DefaultListModel listModel = new DefaultListModel();
		for (Mp3File song : list.getSongs()) {
			listModel.addElement(song.getTrack().getName());
		}
		songsList.setModel(listModel);
	}

    private void addFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileMenuItemActionPerformed
		String dir = "";
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Sound Files", "mp3");

		fc.setFileFilter(filter);

		int choide = fc.showOpenDialog(this);

		if (choide == JFileChooser.APPROVE_OPTION) {
			File SelectedFile = fc.getSelectedFile();
			dir = SelectedFile.getPath();
		}

		if (choide == JFileChooser.CANCEL_OPTION) {
			return;
		}

		DatabaseConnection db = Database.connection();
		db.executeUpdate(new QueryBuilder().insertInto("Files").columns("filepath").values(dir).build());

		db.close();

		updateSongs();
		refreshList();
    }//GEN-LAST:event_addFileMenuItemActionPerformed

    private void addDirectoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDirectoryMenuItemActionPerformed
		String dir = "";
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int choice = fc.showOpenDialog(this);

		if (choice == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			dir = selectedFile.getPath();
		}

		if (choice == JFileChooser.CANCEL_OPTION) {
			return;
		}

		DatabaseConnection db = Database.connection();
		db.executeUpdate(new QueryBuilder().insertInto("Directories").columns("directory").values(dir).build());

		db.close();

		updateSongs();
    }//GEN-LAST:event_addDirectoryMenuItemActionPerformed

	private String sort() {
		int selected = sortComboBox.getSelectedIndex();

		switch (selected) {
			case 0 -> {
				list.sortByName();
				refreshList();
				return "Name";
			}
			case 1 -> {
				list.sortByArtist();
				refreshList();
				return "Artist";
			}
			case 2 -> {
				list.sortByAlbum();
				refreshList();
				return "Album";
			}
			default ->
				throw new AssertionError();
		}
	}

    private void sortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortComboBoxActionPerformed
		String sort_by;

		sort_by = sort();

		Queries.updateSortBy(sort_by);

    }//GEN-LAST:event_sortComboBoxActionPerformed

    private void editDirectoriesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDirectoriesMenuItemActionPerformed
		new EditDirectories(this).setVisible(true);
    }//GEN-LAST:event_editDirectoriesMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
		new EditFiles(this).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
		int index = songsList.getSelectedIndex();

		if (index == list.getSongs().size() - 1) {
			return;
		}

		songsList.setSelectedIndex(index + 1);

		player.stop();

		selectSong(index + 1);

		player.play();

		playButton.setText("Pause");
    }//GEN-LAST:event_nextButtonActionPerformed

    private void songsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songsListMouseClicked
		if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
			player.stop();

			selectSong();

			player.play();
			playButton.setText("Pause");
		}
    }//GEN-LAST:event_songsListMouseClicked

    private void playButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseClicked
		currentSong = list.getSongs().get(currentIndex);
		if (player.isPlaying()) {
			player.pause();
			playButton.setText("Play");
		} else {
			player.play();
			playButton.setText("Pause");
		}
    }//GEN-LAST:event_playButtonMouseClicked

    private void songsListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_songsListKeyPressed
		if (evt.getKeyChar() == '\n') {
			player.stop();

			selectSong();

			player.play();
			playButton.setText("Pause");
		}
    }//GEN-LAST:event_songsListKeyPressed

    private void prevButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevButtonMouseClicked
		int index = songsList.getSelectedIndex();

		if (index == 0) {
			return;
		}

		songsList.setSelectedIndex(index - 1);

		player.stop();

		selectSong(index - 1);

		player.play();

		playButton.setText("Pause");

    }//GEN-LAST:event_prevButtonMouseClicked

    private void sliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMouseDragged
		int input_start = 0, input_end = 99;
		int output_start = 0, output_end = (currentSong == null) ? 0 : (int) currentSong.getDurationInSeconds();

		int output = output_start + ((output_end - output_start) / (input_end - input_start)) * (slider.getValue() - input_start);

		timeLabel.setText(secondsToMinutes(output));

    }//GEN-LAST:event_sliderMouseDragged

	private void editAction() {
		new EditSongInfo(this).setVisible(true);

		selectSong(currentIndex);
		refreshList();
	}

	private void scrapeAction() {
		API api = new API();
		String artist = currentSong.getTrack().getArtist();
		String title = currentSong.getTrack().getName();

		Pair<String, Integer> response = null;

		// If artist is not specified select one
		if (artist == null || "Unknown Artist".equals(artist)) {
			JOptionPane.showMessageDialog(this, "Artist not specified. Searching for song...");

			try {
				response = api.GET(LastFmMethods.Track.search, LastFmMethods.Track.tags(title));
			} catch (IOException | InterruptedException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				return;
			}

			if (response.second != 200) {
				JOptionPane.showMessageDialog(this, "API Reponse Code: " + response.second, "API Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			System.out.println(response.first);
			System.out.println(response.second);

			Search search = new Search(response.first);

			HashSet<Object> propableArtists = new HashSet<>();
			for (SearchTrack track : search.getTracks()) {
				propableArtists.add(track.getArtist());
			}

			artist = (String) JOptionPane.showInputDialog(this, "Select Artist", "", JOptionPane.QUESTION_MESSAGE, null, propableArtists.toArray(), 0);

			currentSong.getTrack().setArtist(artist);
		}

		// Make an API call using title and artist (LastFmMethods.Track.getInfo)
		try {
			response = api.GET(LastFmMethods.Track.getInfo, LastFmMethods.Track.tags(artist, title));
		} catch (IOException | InterruptedException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			return;
		}

		if (response.second != 200) {
			JOptionPane.showMessageDialog(this, "API Reponse Code: " + response.second, "API Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		System.out.println(response.first);

		String error = (String) ((JSONObject) JSONValue.parse(response.first)).get("error");
		if (error != null) {
			JOptionPane.showMessageDialog(this, "Error No" + error, "API Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Update the Song
		currentSong.setTrack(new Track(response.first));
		Queries.updateSong(currentSong);

		// Scrape for the Album
		try {
			response = api.GET(LastFmMethods.Album.getInfo, LastFmMethods.Album.tags(artist, currentSong.getTrack().getAlbum()));
		} catch (IOException | InterruptedException ex) {
			System.err.println("Album scrape fail");
		}

		Album album = new Album(response.first);
		Queries.insertAlbum(album);

		// Scrape for the Artist
		try {
			response = api.GET(LastFmMethods.Artist.getInfo, LastFmMethods.Artist.tags(artist));
		} catch (IOException | InterruptedException ex) {
			System.err.println("Album scrape fail");
		}

		Artist artistO = new Artist(response.first);
		Queries.insertArtist(artistO);

		JOptionPane.showMessageDialog(this, "Scrape Completed", "Success", JOptionPane.INFORMATION_MESSAGE);

		updateSongInfo(currentIndex);
		sort();
		refreshList();
	}

    private void optionsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsLabelMouseClicked
		if (evt.getButton() == MouseEvent.BUTTON1) {
			JPopupMenu options = new JPopupMenu();
			JMenuItem edit = new JMenuItem("Edit");
			JMenuItem scrape = new JMenuItem("Scrape");

			options.setCursor(new Cursor(Cursor.HAND_CURSOR));

			ActionListener menuListener = new ActionListener() {
				String selectedItem;

				@Override
				public void actionPerformed(ActionEvent event) {
					selectedItem = event.getActionCommand();
					if (null != selectedItem) {
						switch (selectedItem) {
							case "Edit" ->
								editAction();
							case "Scrape" ->
								scrapeAction();
							default -> {
							}
						}
					}
				}
			};

			edit.addActionListener(menuListener);
			options.add(edit);

			scrape.addActionListener(menuListener);
			options.add(scrape);

			if (currentSong != null) {
				options.show(evt.getComponent(), evt.getX(), evt.getY());
			}
		}
    }//GEN-LAST:event_optionsLabelMouseClicked

	private String secondsToMinutes(int seconds) {
		int minutes = seconds / 60;
		seconds %= 60;

		return "" + ((minutes < 10) ? "0" + minutes : minutes) + ":" + ((seconds < 10) ? "0" + seconds : seconds);
	}

	private void selectSong(int index) {
		songsList.ensureIndexIsVisible(index);
		
		updateSongInfo(index);

		currentSong = list.getSongs().get(index);
		currentIndex = index;

		player.setFile(currentSong);
	}

	public void updateSongInfo(int index) {
		String path = list.getSongs().get(index).getAbsolutePath();
		String title = list.getSongs().get(index).getTrack().getName();
		String artist = list.getSongs().get(index).getTrack().getArtist();
		String album = list.getSongs().get(index).getTrack().getAlbum();
		String cover = list.getSongs().get(index).getCoverPath();

		trackLabel.setText(title);
		artistLabel.setText((artist == null) ? "Unknown Artist" : artist);
		albumLabel.setText((album == null) ? "Unknown Album" : album);

		Queries.updateSong(list.getSongs().get(index));

		// If album has been scraped load album cover
		if (album != null &&  !album.isBlank() && !album.equals("Unknown Album") ) {
			String coverURL = Queries.selectAlbumCover(album);

			if (coverURL == null) {
				return;
			}

			BufferedImage image = GUIMethods.imageFromURL(coverURL);

			GUIMethods.loadImage(albumImageLabel, image);

			return;
		}

		// if not load imported image or placeholder
		if (cover == null) {
			System.out.println("Cover is null");
			GUIMethods.loadImage(albumImageLabel, project_path + "/assets/album-image-placeholder.png");
			return;
		}

		File albumCover = new File(cover);

		if (albumCover.isFile()) {
			BufferedImage img = null;

			try {
				img = ImageIO.read(new File(albumCover.getAbsolutePath()));
			} catch (IOException e) {
			}

			if (img == null) {
				return;
			}

			if (img.getWidth() > 392 || img.getHeight() > 324) {
				BufferedImage resized = GUIMethods.resizeImage(img, 392, 324);
				GUIMethods.loadImage(albumImageLabel, resized);
			} else {
				GUIMethods.loadImage(albumImageLabel, img);
			}

		}
	}

	private void selectSong() {
		selectSong(songsList.getSelectedIndex());
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}


		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel infoBackground;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel optionsLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JPanel playerBackground;
    private javax.swing.JButton prevButton;
    private javax.swing.JScrollBar slider;
    private javax.swing.JPanel sliderPanel;
    private javax.swing.JList<String> songsList;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JLabel sortbyLabel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel trackLabel;
    // End of variables declaration//GEN-END:variables
}
