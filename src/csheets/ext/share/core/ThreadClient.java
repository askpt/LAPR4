package csheets.ext.share.core;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

import csheets.core.Cell;

/**
 * Class that implement the client to can receives updates from server
 * 
 * @author Tiago
 * 
 */
public class ThreadClient extends Observable implements Runnable {
	/** the connection port */
	private int port;
	/** the cells we will pass throw network */
	private Cell cellStart;
	/** server socket */
	private Socket sock;
	/** the observer class */
	private Observer observer;

	private CellNetworkListenerClient listenerClient;

	public ThreadClient(int port, Cell cellStart, Socket sock,
			CellNetworkListenerClient listener, Observer observer) {
		this.port = port;
		this.cellStart = cellStart;
		this.sock = sock;
		this.listenerClient = listener;
		this.observer = observer;
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
		while (true) {
			Thread.sleep(100);
			boolean isCell = true;
			int cellStartRow = cellStart.getAddress().getRow();
			int cellStartColumn = cellStart.getAddress().getColumn();

			DataInputStream in = new DataInputStream(sock.getInputStream());

			if (in.readUTF().equals("server- send me updated data")) {
				listenerClient.setFlag(false);

				while (isCell) {
					if (listenerClient.getFlag() == false) {
						ObjectInputStream inStream = new ObjectInputStream(
								cli.getInputStream());
						CellNetwork cell = (CellNetwork) inStream.readObject();
						if (cell.isCell()) {

							this.cellStart
									.getSpreadsheet()
									.getCell(
											cellStartColumn + cell.getColumn(),
											cellStartRow + cell.getRow())
									.removeCellListener(listenerClient);
							this.cellStart
									.getSpreadsheet()
									.getCell(
											cellStartColumn + cell.getColumn(),
											cellStartRow + cell.getRow())
									.setContent(cell.getContent());

							this.cellStart
									.getSpreadsheet()
									.getCell(
											cellStartColumn + cell.getColumn(),
											cellStartRow + cell.getRow())
									.addCellListener(listenerClient);
							listenerClient.setFlag(false);

						} else {
							isCell = false;
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
			addObserver(observer);
			while (true) {
				receiveUpdates(cellStart, sock);
			}

		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE,
					null, e);
			setChanged();
			notifyObservers();
			clearChanged();
		}
	}
}