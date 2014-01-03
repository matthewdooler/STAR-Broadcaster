package gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Track;

public class TracksTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Name", "Artist", "Duration", "Album" };
	private static final int COLID_NAME = 0;
	private static final int COLID_ARTIST = 1;
	private static final int COLID_DURATION = 2;
	private static final int COLID_ALBUM = 3;
	private List<Track> tracks = new ArrayList<Track>();
	
	public TracksTableModel() {
	}
	
	/**
	 * Replace the current tracks with a new list of tracks
	 * This tries to fire view events efficiently
	 * @param tracks New list of tracks
	 */
	public void updateTracks(List<Track> tracks) {
		List<Track> oldTracks = this.tracks;
		this.tracks = tracks;
		
		// Update modified rows
		for(int i = 0; i < oldTracks.size(); i++) {
			Track oldTrack = oldTracks.get(i);
			Track newTrack = tracks.get(i);
			if(!oldTrack.equals(newTrack)) {
				// TODO: fire updates in groups for adjacent rows
				fireTableRowsUpdated(i, i);
			}
		}
		
		if(tracks.size() > oldTracks.size()) {
			// More rows than before, so display the new ones
			fireTableRowsInserted(oldTracks.size()-1, tracks.size()-1);
		} else if(tracks.size() < oldTracks.size()) {
			// Fewer rows than before, so delete the old ones
			fireTableRowsDeleted(tracks.size()-1, oldTracks.size()-1);
		}
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() {
		return tracks.size();
	}

	public Object getValueAt(int row, int col) {
		Track track = tracks.get(row);
		switch(col) {
			case COLID_NAME: return track.getTitle();
			case COLID_ARTIST: return track.getArtist();
			case COLID_DURATION: return track.getFormattedDuration();
			case COLID_ALBUM: return track.getAlbum();
			default: return "Undefined column";
		}
	}
	
	public Track getRow(int row) {
		return tracks.get(row);
	}
	
}
