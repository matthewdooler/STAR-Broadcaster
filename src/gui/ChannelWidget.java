package gui;

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
	private JButton backButton;
	
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
		
		// Stack title,waveform,controls vertically
		channelPanel.setLayout(new GridBagLayout());
		GridBagConstraints vConstraints = new GridBagConstraints();
		vConstraints.fill = GridBagConstraints.VERTICAL;
		vConstraints.weightx = 1;
		vConstraints.gridx = 1;
		
		channelLabel = new JLabel("Channel " + (channelNum+1));
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
		
		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				channel.play();
				updateChannelView();
			}
		});
		controls.add(playButton, hConstraints);
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				channel.pause();
				updateChannelView();
			}
		});
		controls.add(pauseButton, hConstraints);
		ejectButton = new JButton("Eject");
		ejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				channel.eject();
				updateChannelView();
			}
		});
		controls.add(ejectButton, hConstraints);
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				channel.reset();
				updateChannelView();
			}
		});
		controls.add(backButton, hConstraints);
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
		Track track = channel.getCurrentTrack();
		if(track != null) {
			channelContents.setText(track.getArtist() + " - " + track.getName());
		} else {
			channelContents.setText(CHANNEL_EMPTY);
		}
	}

}
