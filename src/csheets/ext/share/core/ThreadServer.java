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

	private Cell cellUpdated;

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
	private synchronized void send(Cell[][] cells, Socket sock)
			throws IOException {
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
		}
	}

	/**
	 * Method that will be on a loop to listen message from client when client
	 * send a message it will update server information
	 * 
	 * @param cellUpdated
	 * @param cli
	 * @throws Exception
	 */

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

			if (Server.getInstance().getListener().getFlag() == true) {
				Thread.sleep(100);

				for (int c = 0; c < Server.getInstance().getSockets().size(); c++) {

					OutputStream out = Server.getInstance().getSockets().get(c)
							.getOutputStream();
					DataOutputStream outStreamMessage = new DataOutputStream(
							out);
					outStreamMessage.writeUTF("send me updated data");
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
				}

			}
			Server.getInstance().getListener().setFlag(false);

		}

	}

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {

			send(cells, sock);
			Thread tr = new Thread(new ThreadServerReceiving(cells,
					cellUpdated, sock));
			tr.start();
			sendAllClients(cells);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE,
					null, e);
			e.printStackTrace();

		}
	}
}
