package gui;

import java.awt.*;

import javax.swing.*;

import java.util.List;
import java.util.ArrayList;

/**
 * The main window
 */
public class BaseWindow {
	
	private static String WINDOW_TITLE = "STAR Broadcaster";
	private JFrame frame;
	private int numChannels = 4;
	private List<ChannelWidget> channelWidgets = new ArrayList<ChannelWidget>();
	
	/**
	 * Setup the window and display it
	 */
	public BaseWindow() {
		
		// Initialise window and maximise it
		frame = new JFrame(WINDOW_TITLE);
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		
		setupMenuBar();
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		//LayoutManager layoutManager = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		//contentPane.setLayout(layoutManager);
		
		setupBrowser(contentPane);
		setupPlayoutChannels(contentPane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Setup the main menu bar along the top of the window
	 */
	private void setupMenuBar() {
		MenuBar menuBar = new MenuBar();
		menuBar.setName(WINDOW_TITLE);
		Menu file = new Menu("File");
		Menu view = new Menu("View");
		Menu help = new Menu("Help");
		menuBar.add(file);
		menuBar.add(view);
		menuBar.add(help);
		frame.setMenuBar(menuBar);
	}
	
	private void setupBrowser(Container container) {
		// TODO
		container.add(new JLabel("Browser"), BorderLayout.NORTH);
	}
	
	/**
	 * Setup all of the playout channels, adding them to the container
	 * @param container Parent container
	 */
	private void setupPlayoutChannels(Container container) {
		
		// Add channels to the bottom of the window
		Container channels = new Container();
		container.add(channels, BorderLayout.SOUTH);
		
		LayoutManager layoutManager = new BoxLayout(channels, BoxLayout.X_AXIS);
		channels.setLayout(layoutManager);
		
		for(int i = 0; i < numChannels; i++) {
			setupPlayoutChannel(i, channels);
		}
	}
	
	/**
	 * Setup a playout channel and add it to the container
	 * @param channelNum Channel number
	 * @param container Parent container
	 */
	private void setupPlayoutChannel(int channelNum, Container container) {
		JPanel channelPanel = new JPanel();
		ChannelWidget channelWidget = new ChannelWidget(channelNum, channelPanel);
		channelWidgets.add(channelWidget);
		container.add(channelPanel);
	}
}
