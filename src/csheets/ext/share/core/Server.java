package csheets.ext.share.core;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.*;

import csheets.core.Cell;

/**
 * Class that implement the server in the extension
 * 
 * @author Andre
 * 
 */
public class Server extends Observable implements Runnable {
	/** the connection port */
	/** the cells we will pass throw network */
	private Cell[][] cells;
	/** server socket */
	private ServerSocket svr;
	/** connection passoword */
	private String password;
	/** observer class */
	private Observer observer;
	/** the properties of the connection */
	private String properties;
	/** the sockets list */
	private final ArrayList<Socket> sockets = new ArrayList<Socket>();
	/** the cell listeners */
	private final CellNetworkListenerServer listener = new CellNetworkListenerServer();
	/** the running instance */
	private static Server instance = null;

	/**
	 * Create a new server
	 */
	private Server() {
	}

	/**
	 * Method that returns the running instance of the server
	 * 
	 * @return the running instance of the server
	 */
	public static synchronized Server getInstance() {
		if (instance == null)
			instance = new Server();
		return instance;
	}

	/**
	 * Method that returns the listener of the cells
	 * 
	 * @return the listener of the cells
	 */
	public CellNetworkListenerServer getListener() {
		return listener;
	}

	/**
	 * Method that returns the properties of the connection
	 * 
	 * @return the properties of the connection
	 */
	public String getProperties() {
		return properties;
	}

	/**
	 * Create internaly a new client
	 * 
	 * @param cells
	 *            the cells we will pass throw network
	 * @param svr
	 *            the server socket
	 * @param password
	 *            the connection password
	 * @param properties
	 *            the connection properties
	 * @param observer
	 *            the observer class
	 */
	private Server(Cell[][] cells, ServerSocket svr, String password,
			String properties, Observer observer) {
		this.cells = cells;
		this.svr = svr;
		this.password = password;
		this.properties = properties;
		this.observer = observer;
	}

	/**
	 * Return the cells to be share
	 * 
	 * @return the shared cells
	 */
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
	 * @param password
	 *            the connection password
	 * @param properties
	 *            the connection properties
	 * @param observer
	 *            the observer class
	 */
	public void startServer(int port, Cell[][] cells, String password,
			String properties, Observer observer) {

		try {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					cells[i][j].addCellListener(listener);
				}
			}
			svr = new ServerSocket(port);
			this.cells = cells;
			this.password = password;
			this.properties = properties;
			this.observer = observer;
			addObserver(observer);

			Thread thr = new Thread(getInstance());
			thr.start();

			ServerDiscover.getInstance().findClients(port, observer);
		} catch (Exception e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
			setChanged();
			notifyObservers();
			clearChanged();
		}

	}

	/**
	 * Return the connection sockets
	 * 
	 * @return the connection sockets
	 */
	public ArrayList<Socket> getSockets() {
		return sockets;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Socket sock = svr.accept();
				sockets.add(sock);
				addObserver(observer);

				Thread thr = new Thread(new ThreadServer(cells, sock, password,
						observer));
				thr.start();

				if (properties.equals("wr")) {
					Thread tr = new Thread(new ThreadServerReceiving(sock,
							getListener(), observer));
					tr.start();
				}
			}

		} catch (IOException e) {
			// JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
			setChanged();
			notifyObservers();
			clearChanged();
		}
	}
}
