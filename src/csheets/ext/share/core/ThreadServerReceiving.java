package csheets.ext.share.core;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;

import javax.swing.JOptionPane;

import csheets.core.Cell;

/**
 * Class that implement the client to can receives updates from server
 * 
 * @author Tiago
 * 
 */
public class ThreadServerReceiving implements Runnable {

	/** the cells we will pass throw network */
	private Cell cellStart;
	/** server socket */
	private Socket sock;

	private Cell[][] cells;

	/**
	 * Create a new server
	 */
	private ThreadServerReceiving() {
	}

	public ThreadServerReceiving(Cell[][] cells, Cell cellStart, Socket sock) {

		this.cellStart = cellStart;
		this.sock = sock;
	}

	/**
	 * Method to wait from message of server to update client's information
	 * 
	 * @param cellStart
	 * @param cli
	 * @throws Exception
	 */
	private synchronized void receiveUpdates(Cell cellUpdated, Socket cli)
			throws Exception {
		while (true) {
			Thread.sleep(100);

			DataInputStream in = new DataInputStream(sock.getInputStream());
			if (in.readUTF().equals("send me updated data")) {

				ObjectInputStream inStream = new ObjectInputStream(
						cli.getInputStream());
				CellNetwork cell = (CellNetwork) inStream.readObject();

				for (int i = 0; i < Server.getInstance().getCells().length; i++) {
					for (int j = 0; j < Server.getInstance().getCells()[i].length; j++) {
						if (Server.getInstance().getCells()[i][j].getAddress()
								.getColumn() == cell.getColumn()
								&& Server.getInstance().getCells()[i][j]
										.getAddress().getRow() == cell.getRow()) {
							Server.getInstance().getCells()[i][j]
									.getSpreadsheet()

									.getCell(cell.getColumn(), cell.getRow())
									.setContent(cell.getContent());
							Server.getInstance().getCells()[i][j]
									.setContent(cell.getContent());

						}
					}
				}

			}

		}

	}

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {

			receiveUpdates(cellStart, sock);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE,
					null, e);
			e.printStackTrace();

		}
	}
}
