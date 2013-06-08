package csheets.ext.share.core;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;

import javax.swing.JOptionPane;

import csheets.core.Cell;

/**
 * Class that implement the server in the extension
 * 
 * @author Andre
 * 
 */
public class ThreadServer implements Runnable {
	/** the connection port */
	private int port;
	/** the cells we will pass throw network */
	private Cell[][] cells;
	/** server socket */
	private Socket sock;

	/**
	 * Create a new server
	 */
	private ThreadServer() {
	}

	/**
	 * Create internaly a new client
	 * 
	 * @param port
	 *            the connection port
	 * @param cells
	 *            the cells we will pass throw network
	 */
	public ThreadServer(int port, Cell[][] cells, Socket sock) {
		this.port = port;
		this.cells = cells;
		this.sock = sock;
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
	private void send(Cell[][] cells, Socket sock) throws IOException {
		boolean isAlive = true;
		while (isAlive) {

			DataInputStream in = new DataInputStream(sock.getInputStream());
			if (in.readUTF().equals("send me data")) {
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
			sock.close();
		}
	}

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {
			send(cells, sock);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE,
					null, e);
		}
	}
}
