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
	
	public List<Track> getTracks() {
		return tracks;
	}
	
	public void addTrack(Track track) {
		tracks.add(track);
		fireTableDataChanged();
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
			case COLID_NAME: return track.getName();
			case COLID_ARTIST: return track.getArtist();
			case COLID_DURATION: return track.getFormattedDuration();
			case COLID_ALBUM: return track.getAlbum();
			default: return "Undefined column";
		}
	}
	
}
