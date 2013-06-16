package csheets.ext.share.core;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

import csheets.core.Cell;

/**
 * Class that implement the server in the extension
 * 
 * @author Andre
 * 
 */
public class ThreadServer extends Observable implements Runnable {
	/** the cells we will pass throw network */
	private final Cell[][] cells;
	/** server socket */
	private final Socket sock;
	/** connection passoword */
	private final String password;
	/** the observer class */
	private final Observer observer;

	/**
	 * Create internaly a new client
	 * 
	 * @param port
	 *            the connection port
	 * @param cells
	 *            the cells we will pass throw network
	 * @param password
	 *            the connection password
	 * @param observer
	 *            the observer class
	 */
	public ThreadServer(Cell[][] cells, Socket sock, String password,
			Observer observer) {
		this.cells = cells;
		this.sock = sock;
		this.password = password;
		this.observer = observer;
	}

	/**
	 * Method that will send the information throw network
	 * 
	 * @param cells
	 *            value that will be shared throw network
	 * @param sock
	 *            the socket of connection
	 * @throws IOException
	 *             throw this exception if the I/O have errors
	 */
	private synchronized void send(Cell[][] cells, Socket sock)
			throws IOException {
		boolean isAlive = true;
		while (isAlive) {

			DataInputStream in = new DataInputStream(sock.getInputStream());
			if (in.readUTF().equals(password)) {
				DataOutputStream sendProps = new DataOutputStream(
						sock.getOutputStream());
				sendProps.writeUTF(Server.getInstance().getProperties());
				for (int i = 0; i < cells.length; i++) {
					for (int j = 0; j < cells[i].length; j++) {
						CellNetwork cell = new CellNetwork(
								cells[i][j].getContent(), i, j, true);
						ObjectOutputStream outStream = new ObjectOutputStream(
								sock.getOutputStream());
						outStream.writeObject(cell);
					}
				}

				CellNetwork cell = new CellNetwork("", 0, 0, false);
				ObjectOutputStream outStream = new ObjectOutputStream(
						sock.getOutputStream());
				outStream.writeObject(cell);
			}
			if (in.readUTF().equals("Close yourself")) {
				isAlive = false;
			}
		}
	}

	/**
	 * method to send changes to all clients Not called yet because don't be
	 * operation
	 * 
	 * @param cells
	 *            the cells to be shared
	 * @throws IOException
	 *             throws if a I/O exception occurs
	 * @throws InterruptedException
	 *             throws if a thread exception occurs
	 */

	private synchronized void sendAllClients(Cell[][] cells)
			throws IOException, InterruptedException {

		while (true) {
			Thread.sleep(100);

			if (Server.getInstance().getListener().getFlag() == true) {

				for (int c = 0; c < Server.getInstance().getSockets().size(); c++) {

					OutputStream out = Server.getInstance().getSockets().get(c)
							.getOutputStream();
					DataOutputStream outStreamMessage = new DataOutputStream(
							out);
					outStreamMessage.writeUTF("server- send me updated data");
					for (int i = 0; i < cells.length; i++) {
						for (int j = 0; j < cells[i].length; j++) {
							CellNetwork cell = new CellNetwork(
									cells[i][j].getContent(), i, j, true);
							ObjectOutputStream outStream = new ObjectOutputStream(
									Server.getInstance().getSockets().get(c)
											.getOutputStream());
							outStream.writeObject(cell);
						}
					}

					CellNetwork cell = new CellNetwork("", 0, 0, false);
					ObjectOutputStream outStream = new ObjectOutputStream(
							Server.getInstance().getSockets().get(c)
									.getOutputStream());
					outStream.writeObject(cell);
					Server.getInstance().getListener().setFlag(false);
				}

			}

		}

	}

	@Override
	public void run() {
		try {
			addObserver(observer);
			while (true) {

				send(cells, sock);

				sendAllClients(cells);
			}

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE,
					null, e);
			setChanged();
			notifyObservers();
			clearChanged();
		}
	}
}
