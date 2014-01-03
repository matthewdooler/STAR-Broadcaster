package model;

import java.io.*;
import java.util.concurrent.Semaphore;

import util.PausablePlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Channel implements IChannel {
	
	private Track track;
	private boolean playing = false;
	private PausablePlayer player;
	private Semaphore lock = new Semaphore(1);
	
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
		lock.acquireUninterruptibly();
		this.track = track;
		
		// Load new player into memory without blocking
        final Runnable r = new Runnable() {
            public void run() {
        		String filename = Tracks.SHARED_MEDIA_DIR + getCurrentTrack().getFilename();
                try {
                    FileInputStream fis = new FileInputStream(filename);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    player = new PausablePlayer(bis);
				} catch(FileNotFoundException e) {
					System.err.println("Could not find audio file at " + filename);
					e.printStackTrace();
                } catch(JavaLayerException e) {
                	System.err.println("Could not initialise audio file at " + filename);
                	e.printStackTrace();
				} finally {
                	lock.release();
                }
            }
        };
        new Thread(r).start();
	}
	
	/**
	 * Start or unpause playback
	 */
	@Override
	public void play() {
		lock.acquireUninterruptibly();
		if(track != null && player != null) {
			try {
				// Start/resume playback
				player.play();
				playing = true;
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
		lock.release();
	}
	
	/**
	 * Pause playback until play is called
	 */
	@Override
	public void pause() {
		lock.acquireUninterruptibly();
		if(player != null) {
			player.pause();
		}
		playing = false;
		lock.release();
	}
	
	/**
	 * Seek to start of track
	 */
	@Override
	public void stop() {
		insertTrack(track);
	}
	
	/**
	 * Eject current track
	 */
	@Override
	public void eject() {
		lock.acquireUninterruptibly();
		if(player != null) {
			player.stop();
		}
		player = null;
		playing = false;
		track = null;
		lock.release();
	}
	
	/**
	 * Get current track in channel
	 * @return Current track
	 */
	@Override
	public Track getCurrentTrack() {
		return track;
	}
	
	/**
	 * @return true if a track is currently playing
	 */
	@Override
	public boolean isPlaying() {
		return playing;
	}
}
