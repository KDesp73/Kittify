/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

import javax.swing.JOptionPane;
import static kdesp73.musicplayer.api.API.getKey;
import kdesp73.musicplayer.backend.Backend;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.gui.ProgressDialog;
import kdesp73.musicplayer.songs.Mp3File;
import kdesp73.musicplayer.songs.SongsList;

/**
 *
 * @author konstantinos
 */
public class ScrapeThread extends Thread {

	private SongsList list;
	private int count = 0;

	public ScrapeThread(SongsList list) {
		super();

		this.list = list;
	}

	@Override
	public void run() {
		int listSize = list.getSongs().size();

		// FIXME: temporary solution to check whether there is an internet connection
		if (!API.checkInternetConnection()) {
			JOptionPane.showMessageDialog(null, "It seems you are not connected to the internet. Scraping aborted", "No Internet Connection", JOptionPane.WARNING_MESSAGE);

			return;
		}

		ProgressDialog progressDialog = new ProgressDialog();
		progressDialog.progressBar.setMaximum(listSize - 1);
		progressDialog.setVisible(true);

		for (Mp3File file : list.getSongs()) {
			file.selfScrape();

			if (Queries.selectDownloadCoverByDefault()) {
				Backend.downloadAlbumCover(file);
			}

			progressDialog.progressBar.setValue(count);
			String percentage = String.format("%.2f", count * 1.0 / listSize * 100) + "%";
			progressDialog.percentageLabel.setText(percentage);

			count++;
		}

		progressDialog.dispose();
		JOptionPane.showMessageDialog(null, "Scrape Completed!");
	}
}
