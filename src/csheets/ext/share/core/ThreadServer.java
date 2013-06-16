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
	/** the connection port */
	private int port;
	/** the cells we will pass throw network */
	private Cell[][] cells;
	/** server socket */
	private Socket sock;
	/** connection passoword */
	private String password;
	/** the observer class */
	private Observer observer;

	private String properties;

	private Cell cellUpdated;

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
	public ThreadServer(int port, Cell[][] cells, Socket sock, String password,
			String properties, Observer observer) {
		this.port = port;
		this.cells = cells;
		this.sock = sock;
		this.password = password;
		this.properties = properties;
		this.observer = observer;
	}

	/**
	 * Method that will send the information throw network
	 * 
	 * @param cells
	 *            value that will be shared throw network
	 * @param svr
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
	 * @param sock
	 * @throws IOException
	 * @throws InterruptedException
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

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {

			while (true) {

				send(cells, sock);

				sendAllClients(cells);
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
