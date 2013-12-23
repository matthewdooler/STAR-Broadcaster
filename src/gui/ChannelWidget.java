package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

/**
 * An individual channel widget, displaying a track and the controls
 */
public class ChannelWidget {
	
	private int channelNum;
	private JPanel channelPanel;
	
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
		
		channelPanel.add(new JLabel("Channel " + (channelNum+1)), vConstraints);
		channelPanel.add(new JLabel("Waveform here test test test"), vConstraints); // TODO
		
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
	}

}
