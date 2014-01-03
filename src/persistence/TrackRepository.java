package persistence;

import java.util.*;

import model.Track;

import org.ektorp.*;
import org.ektorp.support.*;

@View( name = "all", map = "function(doc) { if (doc.type == 'Track' ) emit( null, doc._id )}")
public class TrackRepository extends CouchDbRepositorySupport<Track> {

	public TrackRepository(CouchDbConnector db) {
		super(Track.class, db);
		initStandardDesignDocument();
	}
	
	@Override
    @View( name="all", map = "function(doc) { if (doc.type == 'Track' ) emit( null, doc._id )}")
    public List<Track> getAll() {
		System.out.println("Pulling all tracks from database");
		List<Track> tracks = new ArrayList<Track>();
        ViewQuery query = createQuery("all").descending(true).reduce(false);
        ViewResult result = db.queryView(query);
        for(ViewResult.Row row : result) {
        	Track t = db.get(Track.class, row.getId());
        	tracks.add(t);
        }
        return tracks;
    }

}