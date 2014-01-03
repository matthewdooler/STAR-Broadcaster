package model;

public class Channel implements IChannel {
	
	private Track track;
	private boolean playing = false;
	
	public Channel() {
		track = null;
	}
	
	/**
	 * Replace or insert a new track into the channel
	 * @param track Track
	 */
	@Override
	public void insertTrack(Track track) {
		eject();
		this.track = track;
	}
	
	/**
	 * Start or unpause playback
	 */
	@Override
	public void play() {
		// TODO: start playback
		System.out.println("Starting playback (TODO)");
		if(track != null) {
			playing = true;
		}
	}
	
	/**
	 * Pause playback until play is called
	 */
	@Override
	public void pause() {
		// TODO: pause playback
		System.out.println("Pausing playback (TODO)");
		playing = false;
	}
	
	/**
	 * Seek to start of track
	 */
	@Override
	public void stop() {
		// TODO: seek to start of track
		System.out.println("Stopping playback (TODO)");
		playing = false;
	}
	
	/**
	 * Eject current track
	 */
	@Override
	public void eject() {
		// TODO: stop playback if required
		playing = false;
		this.track = null;
	}
	
	/**
	 * Get current track in channel
	 * @return Current track
	 */
	@Override
	public Track getCurrentTrack() {
		return this.track;
	}
	
	/**
	 * @return true if a track is currently playing
	 */
	@Override
	public boolean isPlaying() {
		return playing;
	}
}
