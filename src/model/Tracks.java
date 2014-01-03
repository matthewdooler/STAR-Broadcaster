package model;

import persistence.RepositoryManager;
import persistence.TrackRepository;
import gui.BaseView;

import java.util.*;

/**
 * This models the collection of tracks, and keeps the view updated with the most recent information
 * It also periodically checks the persistence layer for changes
 * @author Matthew Dooler
 */
public class Tracks {
	
	private RepositoryManager repositoryManager;
	private BaseView view;
	private long updateTracksDelay = 10000; // ms
	private Timer updateTracksTimer;
	
	public Tracks(RepositoryManager repositoryManager, BaseView view) {
		this.repositoryManager = repositoryManager;
		this.view = view;
		this.view.updateTracks(getTracks());
		this.updateTracksTimer = new Timer();
        this.updateTracksTimer.schedule(new UpdateTracksTask(), updateTracksDelay);
	}
	
	private BaseView getView() {
		return view;
	}
	
	class UpdateTracksTask extends TimerTask {
        public void run() {
        	getView().updateTracks(getTracks());
        }
    }
	
	public void addTrack(Track track) {
		TrackRepository trackRepository = repositoryManager.getTrackRepository();
		trackRepository.add(track);
		getView().updateTracks(getTracks());
	}
	
	public void removeTrack(Track track) {
		TrackRepository trackRepository = repositoryManager.getTrackRepository();
		trackRepository.remove(track);
		getView().updateTracks(getTracks());
	}
	
	private List<Track> getTracks() {
		TrackRepository trackRepository = repositoryManager.getTrackRepository();
		return trackRepository.getAll();
	}
	
}
