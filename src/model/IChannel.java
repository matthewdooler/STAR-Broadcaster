package model;

public interface IChannel {
	
	/**
	 * Replace or insert a new track into the channel
	 * @param track Track
	 */
	public void insertTrack(Track track);
	
	/**
	 * Start or unpause playback
	 */
	public void play();
	
	/**
	 * Pause playback until play is called
	 */
	public void pause();
	
	/**
	 * Seek to start of track
	 */
	public void stop();
	
	/**
	 * Eject current track
	 */
	public void eject();
	
	/**
	 * Get current track in channel
	 * @return Current track
	 */
	public Track getCurrentTrack();
	
	/**
	 * @return true if a track is currently playing
	 */
	public boolean isPlaying();
}
