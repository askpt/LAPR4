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
public class ThreadClient implements Runnable {
	/** the connection port */
	private int port;
	/** the cells we will pass throw network */
	private Cell cellStart;
	/** server socket */
	private Socket sock;

	/**
	 * Create a new server
	 */
	private ThreadClient() {
	}

	public ThreadClient(int port, Cell cellStart, Socket sock) {
		this.port = port;
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
	private synchronized void receiveUpdates(Cell cellStart, Socket cli)
			throws Exception {
		boolean isCell = true;
		int cellStartRow = cellStart.getAddress().getRow();
		int cellStartColumn = cellStart.getAddress().getColumn();

		DataInputStream in = new DataInputStream(sock.getInputStream());
		if (in.readUTF().equals("send me updated data")) {
			while (isCell) {
				ObjectInputStream inStream = new ObjectInputStream(
						cli.getInputStream());
				CellNetwork cell = (CellNetwork) inStream.readObject();
				if (cell.isCell()) {

					this.cellStart
							.getSpreadsheet()
							.getCell(cellStartColumn + cell.getColumn(),
									cellStartRow + cell.getRow())
							.setContent(cell.getContent());

				} else {
					isCell = false;
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