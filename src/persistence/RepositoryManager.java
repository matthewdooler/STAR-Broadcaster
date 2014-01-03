package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import model.Track;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class RepositoryManager {
	
	private TrackRepository trackRepository;
	
	public RepositoryManager() {

		try {
    		String url = "";
    		String database = "";
    		String username = "";
    		String password = "";
			Properties prop = new Properties();
	    	try {
	    		prop.load(new FileInputStream("couchdb.properties"));
	    		url = prop.getProperty("url", "https://127.0.0.1:6984/");
	    		database = prop.getProperty("database", "");
	    		username = prop.getProperty("username", "");
	    		password = prop.getProperty("password", "");
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
			
	    	// Login to CouchDB and connect to the database
			HttpClient authenticatedHttpClient = new StdHttpClient.Builder()
				.url(url)
				.username(username)
				.password(password)
				.relaxedSSLSettings(true) // TODO: add our self-signed certificate to a trust store
				.build();
			CouchDbInstance dbInstance = new StdCouchDbInstance(authenticatedHttpClient);
			CouchDbConnector db = dbInstance.createConnector(database, true);
			
			// Initialise repositories
			this.trackRepository = new TrackRepository(db);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TrackRepository getTrackRepository() {
		return trackRepository;
	}
}
