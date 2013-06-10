package csheets.ext.share.core;

import java.io.IOException;
import java.net.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import csheets.core.Cell;

/**
 * Class that implement the server in the extension
 * 
 * @author Andre
 * 
 */
public class Server implements Runnable {
	/** the connection port */
	private int port;
	/** the cells we will pass throw network */
	private Cell[][] cells;
	/** server socket */
	private ServerSocket svr;

	private String Ip;

	private boolean changesClient = false;

	private CellNetworkListenerServer listener = new CellNetworkListenerServer();

	private static Server instance = null;

	/**
	 * Create a new server
	 */
	private Server() {
	}

	public void setUpdated(boolean flag) {
		changesClient = flag;
	}

	public boolean getFlag() {
		return changesClient;
	}

	public static synchronized Server getInstance() {
		if (instance == null)
			instance = new Server();
		return instance;
	}

	public CellNetworkListenerServer getListener() {
		return listener;
	}

	/**
	 * Create internaly a new client
	 * 
	 * @param port
	 *            the connection port
	 * @param cells
	 *            the cells we will pass throw network
	 */
	private Server(int port, Cell[][] cells, ServerSocket svr) {
		this.port = port;
		this.cells = cells;
		this.svr = svr;
	}

	/**
	 * Method that will start the server and share the cells throw network
	 * 
	 * @param port
	 *            connection port
	 * @param cells
	 *            value that will be shared throw network
	 */
	public void startServer(int port, Cell[][] cells) {

		try {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					cells[i][j].addCellListener(listener);
				}
			}
			svr = new ServerSocket(port);
			this.cells = cells;
			// TODO introduce the boolean check if server stopped
			Thread thr = new Thread(getInstance());
			thr.start();
			// svr.close();
			ServerDiscover.getInstance().findClients(port);
		} catch (Exception e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {
			while (true) {

				Socket sock = svr.accept();
				Thread thr = new Thread(new ThreadServer(port, cells, sock));
				thr.start();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
