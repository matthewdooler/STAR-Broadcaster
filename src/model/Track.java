package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v2ChapterFrameData;
import com.mpatric.mp3agic.ID3v2ChapterTOCFrameData;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.MutableInteger;

import org.codehaus.jackson.annotate.*;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class Track extends CouchDbDocument {
	
	private static final long serialVersionUID = 1L;
	
	private String type = "Track";
	private String id;
    private String revision;
	
    // TODO: filename
	private boolean autoDj;
	
	/* Non-ID3 track properties */
	// TODO: some of these are mp3.. some will be for other formats
	private int frameCount;
	private int startOffset;
	private int endOffset;
	private long lengthInMilliseconds;
	private long lengthInSeconds;
	private boolean vbr;
	private int bitrate;
	private String channelMode;
	private String emphasis;
	private String layer;
	private String modeExtension;
	private boolean original;
	private int sampleRate;
	private boolean xingFrame;
	private int xingOffset;
	private int xingBitrate;
	
	/* ID3v1 */
	private String version;
	private String track;
	private String artist;
	private String title;
	private String album;
	private String year;
	private int genre;
	private String genreDescription;
	private String comment;
	
	/* ID3v2 */
	private boolean padding = false;
	private boolean footer = false;
	private boolean unsynchronisation = false;
	private String composer = "";
	private String publisher = "";
	private String originalArtist = "";
	private String albumArtist = "";
	private String copyright = "";
	private String url = "";
	private String partOfSet = "";
	private boolean compilation = false;
	private String grouping = "";
	private String encoder = "";
	private byte[] albumImage = null;
	private String albumImageMimeType = "";
	private String itunesComment = "";
	private int dataLength = 0;
	private int length = 0;
	private boolean obseleteFormat = false;
	
	public Track() {
		
	}
	
	public Track(Mp3File file) {
		this.autoDj = false;
		this.frameCount = file.getFrameCount();
		this.startOffset = file.getStartOffset();
		this.endOffset = file.getEndOffset();
		this.lengthInMilliseconds = file.getLengthInMilliseconds();
		this.lengthInSeconds = file.getLengthInSeconds();
		this.vbr = file.isVbr();
		this.bitrate = file.getBitrate();
		this.channelMode = file.getChannelMode();
		this.emphasis = file.getEmphasis();
		this.layer = file.getLayer();
		this.modeExtension = file.getModeExtension();
		this.original = file.isOriginal();
		this.sampleRate = file.getSampleRate();
		this.xingFrame = file.hasXingFrame();
		this.xingOffset = file.getXingOffset();
		this.xingBitrate = file.getXingBitrate();
		
		if(file.hasId3v1Tag()) {
			// ID3v1
			ID3v1 tag = file.getId3v2Tag();
			version = tag.getVersion();
			track = tag.getTrack();
			artist = tag.getArtist();
			title = tag.getTitle();
			album = tag.getAlbum();
			year = tag.getYear();
			genre = tag.getGenre();
			genreDescription = tag.getGenreDescription();
			comment = tag.getComment();
		} else if(file.hasId3v2Tag()) {
			// ID3v2
			ID3v2 tag = file.getId3v2Tag();
			
			// v1 properties (inherited)
			version = tag.getVersion();
			track = tag.getTrack();
			artist = tag.getArtist();
			title = tag.getTitle();
			album = tag.getAlbum();
			year = tag.getYear();
			genre = tag.getGenre();
			genreDescription = tag.getGenreDescription();
			comment = tag.getComment();
			
			// v2 properties
			padding = tag.getPadding();
			footer = tag.hasFooter();
			unsynchronisation = tag.hasUnsynchronisation();
			composer = tag.getComposer();
			publisher = tag.getPublisher();
			originalArtist = tag.getOriginalArtist();
			albumArtist = tag.getAlbumArtist();
			copyright = tag.getCopyright();
			url = tag.getUrl();
			partOfSet = tag.getPartOfSet();
			compilation = tag.isCompilation();
			grouping = tag.getGrouping();
			encoder = tag.getEncoder();
			albumImage = tag.getAlbumImage();
			albumImageMimeType = tag.getAlbumImageMimeType();
			itunesComment = tag.getItunesComment();
			dataLength = tag.getDataLength();
			length = tag.getLength();
			obseleteFormat = tag.getObseleteFormat();
		} else {
			// TODO No ID3 information
		}
	}
	
	
    @JsonProperty("_id")
    public String getId() {
            return id;
    }

    @JsonProperty("_id")
    public void setId(String s) {
            id = s;
    }

    @JsonProperty("_rev")
    public String getRevision() {
            return revision;
    }

    @JsonProperty("_rev")
    public void setRevision(String s) {
            revision = s;
    }
    
    public String getType() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
	
	
	
	/**
	 * @return the frameCount
	 */
	public int getFrameCount() {
		return frameCount;
	}

	/**
	 * @return the startOffset
	 */
	public int getStartOffset() {
		return startOffset;
	}

	/**
	 * @return the endOffset
	 */
	public int getEndOffset() {
		return endOffset;
	}

	/**
	 * @return the lengthInMilliseconds
	 */
	public long getLengthInMilliseconds() {
		return lengthInMilliseconds;
	}

	/**
	 * @return the lengthInSeconds
	 */
	public long getLengthInSeconds() {
		return lengthInSeconds;
	}

	/**
	 * @return the vbr
	 */
	public boolean isVbr() {
		return vbr;
	}

	/**
	 * @return the bitrate
	 */
	public int getBitrate() {
		return bitrate;
	}

	/**
	 * @return the channelMode
	 */
	public String getChannelMode() {
		return channelMode;
	}

	/**
	 * @return the emphasis
	 */
	public String getEmphasis() {
		return emphasis;
	}

	/**
	 * @return the layer
	 */
	public String getLayer() {
		return layer;
	}

	/**
	 * @return the modeExtension
	 */
	public String getModeExtension() {
		return modeExtension;
	}

	/**
	 * @return the original
	 */
	public boolean isOriginal() {
		return original;
	}

	/**
	 * @return the sampleRate
	 */
	public int getSampleRate() {
		return sampleRate;
	}

	/**
	 * @return the xingFrame
	 */
	public boolean isXingFrame() {
		return xingFrame;
	}

	/**
	 * @return the xingOffset
	 */
	public int getXingOffset() {
		return xingOffset;
	}

	/**
	 * @return the xingBitrate
	 */
	public int getXingBitrate() {
		return xingBitrate;
	}

	
	
	
	
	
	

	// TODO: the setters below should modify the ID3 tag
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the track
	 */
	public String getTrack() {
		return track;
	}

	/**
	 * @param track the track to set
	 */
	public void setTrack(String track) {
		this.track = track;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the genre
	 */
	public int getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(int genre) {
		this.genre = genre;
	}

	/**
	 * @return the genreDescription
	 */
	public String getGenreDescription() {
		return genreDescription;
	}

	/**
	 * @param genreDescription the genreDescription to set
	 */
	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the padding
	 */
	public boolean isPadding() {
		return padding;
	}

	/**
	 * @param padding the padding to set
	 */
	public void setPadding(boolean padding) {
		this.padding = padding;
	}

	/**
	 * @return the footer
	 */
	public boolean isFooter() {
		return footer;
	}

	/**
	 * @param footer the footer to set
	 */
	public void setFooter(boolean footer) {
		this.footer = footer;
	}

	/**
	 * @return the unsynchronisation
	 */
	public boolean isUnsynchronisation() {
		return unsynchronisation;
	}

	/**
	 * @param unsynchronisation the unsynchronisation to set
	 */
	public void setUnsynchronisation(boolean unsynchronisation) {
		this.unsynchronisation = unsynchronisation;
	}

	/**
	 * @return the composer
	 */
	public String getComposer() {
		return composer;
	}

	/**
	 * @param composer the composer to set
	 */
	public void setComposer(String composer) {
		this.composer = composer;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the originalArtist
	 */
	public String getOriginalArtist() {
		return originalArtist;
	}

	/**
	 * @param originalArtist the originalArtist to set
	 */
	public void setOriginalArtist(String originalArtist) {
		this.originalArtist = originalArtist;
	}

	/**
	 * @return the albumArtist
	 */
	public String getAlbumArtist() {
		return albumArtist;
	}

	/**
	 * @param albumArtist the albumArtist to set
	 */
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}

	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the partOfSet
	 */
	public String getPartOfSet() {
		return partOfSet;
	}

	/**
	 * @param partOfSet the partOfSet to set
	 */
	public void setPartOfSet(String partOfSet) {
		this.partOfSet = partOfSet;
	}

	/**
	 * @return the compilation
	 */
	public boolean isCompilation() {
		return compilation;
	}

	/**
	 * @param compilation the compilation to set
	 */
	public void setCompilation(boolean compilation) {
		this.compilation = compilation;
	}

	/**
	 * @return the grouping
	 */
	public String getGrouping() {
		return grouping;
	}

	/**
	 * @param grouping the grouping to set
	 */
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	/**
	 * @return the encoder
	 */
	public String getEncoder() {
		return encoder;
	}

	/**
	 * @param encoder the encoder to set
	 */
	public void setEncoder(String encoder) {
		this.encoder = encoder;
	}

	/**
	 * @return the albumImage
	 */
	public byte[] getAlbumImage() {
		return albumImage;
	}

	/**
	 * @param albumImage the albumImage to set
	 */
	public void setAlbumImage(byte[] albumImage) {
		this.albumImage = albumImage;
	}

	/**
	 * @return the albumImageMimeType
	 */
	public String getAlbumImageMimeType() {
		return albumImageMimeType;
	}

	/**
	 * @param albumImageMimeType the albumImageMimeType to set
	 */
	public void setAlbumImageMimeType(String albumImageMimeType) {
		this.albumImageMimeType = albumImageMimeType;
	}

	/**
	 * @return the itunesComment
	 */
	public String getItunesComment() {
		return itunesComment;
	}

	/**
	 * @param itunesComment the itunesComment to set
	 */
	public void setItunesComment(String itunesComment) {
		this.itunesComment = itunesComment;
	}

	/**
	 * @return the dataLength
	 */
	public int getDataLength() {
		return dataLength;
	}

	/**
	 * @param dataLength the dataLength to set
	 */
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the obseleteFormat
	 */
	public boolean isObseleteFormat() {
		return obseleteFormat;
	}

	/**
	 * @param obseleteFormat the obseleteFormat to set
	 */
	public void setObseleteFormat(boolean obseleteFormat) {
		this.obseleteFormat = obseleteFormat;
	}
	
	
	
	
	/**
	 * @return the duration as a formatted string
	 */
	@JsonIgnore
	public String getFormattedDuration() {
		return new SimpleDateFormat("mm:ss").format(new Date(lengthInMilliseconds));
	}
	
	
	// TODO: following are extra properties
	

	/**
	 * @return true if track can be played by AutoDJ
	 */
	public boolean getAutoDj() {
		return autoDj;
	}

	/**
	 * @param autoDj true if track can be played by AutoDJ
	 */
	public void setAutoDj(boolean autoDj) {
		this.autoDj = autoDj;
	}
}
