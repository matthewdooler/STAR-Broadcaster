package model;

public interface IChannel {
	public void playTrack();
	
	public void stop();
	
	public void eject();
	
	public void insertTrack(Track track);
}
