package csheets.ext.share.core;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import csheets.core.Cell;

/**
 * Class that implement the client to can receives updates from server
 * 
 * @author Tiago
 * 
 */
public class ThreadServerReceiving extends Observable implements Runnable {

	/** the cells we will pass throw network */
	private Cell cellStart;
	/** server socket */
	private Socket sock;
	/** the observer class */
	private Observer observer;

	private Cell[][] cells;

	private CellNetworkListenerServer listenerServer;

	public ThreadServerReceiving(Cell[][] cells, Socket sock,
			CellNetworkListenerServer listenerServer, Observer observer) {

		this.cellStart = cellStart;
		this.sock = sock;
		this.listenerServer = listenerServer;
		this.observer = observer;
	}

	/**
	 * Method to wait from message of server to update client's information
	 * 
	 * @param cellStart
	 * @param cli
	 * @throws Exception
	 */
	private synchronized void receiveUpdates(Socket cli) throws Exception {
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
									.removeCellListener(listenerServer);

							Server.getInstance().getCells()[i][j]
									.getSpreadsheet()

									.getCell(cell.getColumn(), cell.getRow())
									.setContent(cell.getContent());

							Server.getInstance().getCells()[i][j]
									.getSpreadsheet()

									.getCell(cell.getColumn(), cell.getRow())
									.addCellListener(listenerServer);

							Server.getInstance().getCells()[i][j]
									.setContent(cell.getContent());
							listenerServer.setFlag(true);

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
				receiveUpdates(sock);
			}

		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE,
					null, e);
			e.printStackTrace();
			setChanged();
			notifyObservers();
			clearChanged();
		}
	}
}
