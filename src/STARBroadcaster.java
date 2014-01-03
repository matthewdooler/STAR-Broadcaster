import gui.BaseView;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Track;
import model.Tracks;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.ektorp.*;
import org.ektorp.http.*;
import org.ektorp.impl.*;

import persistence.RepositoryManager;
import persistence.TrackRepository;

import com.mpatric.mp3agic.*;

public class STARBroadcaster {

	public static void main(String[] args) {
		
		// Standard MVC.
		// e.g., tracks is a model and is responsible for updating the window.
		BaseView view = new BaseView();
		RepositoryManager repositoryManager = new RepositoryManager();
		Tracks tracks = new Tracks(repositoryManager, view);
		
		/*for(int i = 1; i <= 12; i++) {
		Mp3File file = new Mp3File("audio/" + i + ".mp3");
		Track track = new Track(file);
		System.out.println("Track read");
		}*/
		
	}

}
