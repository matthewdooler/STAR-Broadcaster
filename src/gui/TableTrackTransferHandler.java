package gui;

import gui.model.TracksTableModel;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.activation.*;
import javax.swing.*;

import model.Track;

/**
 * Drag tracks out of the table
 */
public class TableTrackTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;
	private static final DataFlavor trackDataFlavor = new ActivationDataFlavor(Track.class, DataFlavor.javaJVMLocalObjectMimeType, "Track");
	private JTable table;

	public TableTrackTransferHandler(JTable table) {
		this.table = table;
	}
	
	public static DataFlavor getTrackDataFlavor() {
		return trackDataFlavor;
	}
	
	@Override
	protected Transferable createTransferable(JComponent c) {
		// Get selected track and package it into a data handler, ready for transfer
		assert(c == table);
		int selectedRow = table.getSelectedRow();
		TracksTableModel tableModel = (TracksTableModel) table.getModel();
		Track track = tableModel.getRow(selectedRow);
		DataHandler transferable = new DataHandler(track, trackDataFlavor.getMimeType());
		return transferable;
	}
	
	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY;
	}

}
