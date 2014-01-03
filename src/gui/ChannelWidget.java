package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;

import model.Channel;
import model.Track;

/**
 * An individual channel widget, displaying a track and the controls
 */
public class ChannelWidget {
	
	private Channel channel;
	
	private int channelNum;
	private JPanel channelPanel;
	private JLabel channelLabel;
	private JLabel channelContents;
	
	private JButton playButton;
	private JButton pauseButton;
	private JButton ejectButton;
	private JButton stopButton;
	
	private static final String IMG_FILENAME_PLAY = "img/media-play.png";
	private static final String IMG_FILENAME_PAUSE = "img/media-pause.png";
	private static final String IMG_FILENAME_EJECT = "img/media-eject.png";
	private static final String IMG_FILENAME_STOP = "img/media-stop.png";
	
	private static final ImageIcon IMG_PLAY = new ImageIcon(IMG_FILENAME_PLAY);
	private static final ImageIcon IMG_PAUSE = new ImageIcon(IMG_FILENAME_PAUSE);
	private static final ImageIcon IMG_EJECT = new ImageIcon(IMG_FILENAME_EJECT);
	private static final ImageIcon IMG_STOP = new ImageIcon(IMG_FILENAME_STOP);
	
	private static final String CHANNEL_EMPTY = "Empty";
	
	/**
	 * Create a new channel widget, adding its components to the initialised panel
	 * @param channelNum Channel number
	 * @param channelPanel Channel panel
	 */
	public ChannelWidget(int channelNum, JPanel channelPanel) {
		
		this.channel = new Channel();
		this.channelNum = channelNum;
		this.channelPanel = channelPanel;
		
		//channelPanel.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));
		channelPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		// Stack title,waveform,controls vertically
		channelPanel.setLayout(new GridBagLayout());
		GridBagConstraints vConstraints = new GridBagConstraints();
		vConstraints.fill = GridBagConstraints.VERTICAL;
		vConstraints.weightx = 1;
		vConstraints.gridx = 1;
		
		channelLabel = new JLabel("Channel " + (channelNum+1));
		Font font = channelLabel.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		channelLabel.setFont(boldFont);
		channelPanel.add(channelLabel, vConstraints);
		
		channelContents = new JLabel(CHANNEL_EMPTY);
		channelPanel.add(channelContents, vConstraints);
		
		JPanel controls = setupControls();
		channelPanel.add(controls, vConstraints);
		
		// Accept dragged tracks
		setupDropHandler();
		
		updateChannelView();
	}
	
	private JPanel setupControls() {
		// Place controls horizontally
		JPanel controls = new JPanel();
		controls.setLayout(new GridBagLayout());
		GridBagConstraints hConstraints = new GridBagConstraints();
		hConstraints.fill = GridBagConstraints.VERTICAL;
		hConstraints.weightx = 1;
		hConstraints.gridy = 1;
		int buttonPadding = 8;
		
		playButton = new JButton(IMG_PLAY);
		playButton.setPreferredSize(new Dimension(IMG_PLAY.getIconWidth() + buttonPadding, IMG_PLAY.getIconHeight() + buttonPadding));
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(channel.isPlaying()) channel.pause();
				else channel.play();
				updateChannelView();
			}
		});
		controls.add(playButton, hConstraints);
		/*pauseButton = new JButton(IMG_PAUSE);
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				channel.pause();
				updateChannelView();
			}
		});
		controls.add(pauseButton, hConstraints);*/
		ejectButton = new JButton(IMG_EJECT);
		ejectButton.setPreferredSize(new Dimension(IMG_EJECT.getIconWidth() + buttonPadding, IMG_EJECT.getIconHeight() + buttonPadding));
		ejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				channel.eject();
				updateChannelView();
			}
		});
		controls.add(ejectButton, hConstraints);
		stopButton = new JButton(IMG_STOP);
		stopButton.setPreferredSize(new Dimension(IMG_STOP.getIconWidth() + buttonPadding, IMG_STOP.getIconHeight() + buttonPadding));
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				channel.stop();
				updateChannelView();
			}
		});
		controls.add(stopButton, hConstraints);
		return controls;
	}
	
	private void setupDropHandler() {
		DropTargetListener dtl = new DropTargetAdapter() {
			@Override
			public void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(dtde.getDropAction());
				try {
					DataFlavor trackDataFlavour = TableTrackTransferHandler.getTrackDataFlavor();
					Object obj = dtde.getTransferable().getTransferData(trackDataFlavour);
					if(obj instanceof Track) {
						Track track = (Track) obj;
						channel.insertTrack(track);
						updateChannelView();
						//System.out.println(track.toString());
						//channelContents.setText(track.getArtist() + " - " + track.getName());
					} else {
						System.err.println("Dropped object is not a track");
					}
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					dtde.dropComplete(true);
				}
			}
		};
		channelPanel.setDropTarget(new DropTarget(channelPanel, dtl));
	}
	
	private void updateChannelView() {
		
		// Show either play/pause button
		if(channel.isPlaying()) {
			playButton.setIcon(IMG_PAUSE);
		} else {
			playButton.setIcon(IMG_PLAY);
		}
		
		Track track = channel.getCurrentTrack();
		if(track != null) {
			channelContents.setText(track.getArtist() + " - " + track.getTitle());
		} else {
			channelContents.setText(CHANNEL_EMPTY);
		}
	}

}
