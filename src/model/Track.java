package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Track {
	
	private String name;
	private String artist;
	private Date duration;
	private String album;
	
	/**
	 * @param name
	 * @param artist
	 * @param duration
	 * @param album
	 */
	public Track(String name, String artist, Date duration, String album) {
		super();
		this.name = name;
		this.artist = artist;
		this.duration = duration;
		this.album = album;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @return the duration
	 */
	public Date getDuration() {
		return duration;
	}
	/**
	 * @return the duration as a formatted string
	 */
	public String getFormattedDuration() {
		return new SimpleDateFormat("mm:ss").format(duration);
	}
	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Track [getName()=" + getName() + ", getArtist()=" + getArtist()
				+ ", getFormattedDuration()=" + getFormattedDuration()
				+ ", getAlbum()=" + getAlbum() + "]";
	}
}
