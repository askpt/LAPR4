package csheets.ext.share.core;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
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

	private ArrayList<Socket> sockets = new ArrayList<Socket>();

	private CellNetworkListenerServer listener = new CellNetworkListenerServer();

	private static Server instance = null;

	/**
	 * Create a new server
	 */
	private Server() {
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

	public Cell[][] getCells() {
		return cells;
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

			Thread thr = new Thread(getInstance());
			thr.start();

			ServerDiscover.getInstance().findClients(port);
		} catch (Exception e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	public ArrayList<Socket> getSockets() {
		return sockets;
	}

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {
			while (true) {
				Socket sock = svr.accept();
				sockets.add(sock);
				System.out.println(sock);
				Thread thr = new Thread(new ThreadServer(port, cells, sock));
				thr.start();

			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
