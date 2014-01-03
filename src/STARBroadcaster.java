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
			try {
				String filename = i + ".mp3";
				Mp3File file = new Mp3File("tmp/" + filename);
				Track track = new Track(file, filename);
				System.out.println("Track read");
				tracks.addTrack(track, file.getFilename());
				System.out.println("Track imported");
			} catch (UnsupportedTagException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}*/
		
	}

}
