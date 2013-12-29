package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.*;

import model.Track;

/**
 * An individual channel widget, displaying a track and the controls
 */
public class ChannelWidget {
	
	private int channelNum;
	private JPanel channelPanel;
	private JLabel channelLabel;
	private JLabel channelContents;
	
	private JButton playButton;
	private JButton pauseButton;
	private JButton ejectButton;
	private JButton backButton;
	
	/**
	 * Create a new channel widget, adding its components to the initialised panel
	 * @param channelNum Channel number
	 * @param channelPanel Channel panel
	 */
	public ChannelWidget(int channelNum, JPanel channelPanel) {
		
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
		channelContents = new JLabel("Empty");
		channelPanel.add(channelContents, vConstraints);
		
		// Place controls horizontally
		JPanel controls = new JPanel();
		controls.setLayout(new GridBagLayout());
		GridBagConstraints hConstraints = new GridBagConstraints();
		hConstraints.fill = GridBagConstraints.VERTICAL;
		hConstraints.weightx = 1;
		hConstraints.gridy = 1;
		
		playButton = new JButton("Play");
		controls.add(playButton, hConstraints);
		pauseButton = new JButton("Pause");
		controls.add(pauseButton, hConstraints);
		ejectButton = new JButton("Eject");
		controls.add(ejectButton, hConstraints);
		backButton = new JButton("Back");
		controls.add(backButton, hConstraints);
		channelPanel.add(controls, vConstraints);
		
		// Accept dragged tracks
		setupDropHandler();
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
						System.out.println(track.toString());
						channelContents.setText(track.getArtist() + " - " + track.getName());
						System.out.println(trackDataFlavour.isRepresentationClassInputStream());
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

}
