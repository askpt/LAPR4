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
	private int port;
	/** the cells we will pass throw network */
	private Cell[][] cells;
	/** server socket */
	private ServerSocket svr;
	/** connection passoword */
	private String password;
	/** observer class */
	private Observer observer;

	private String properties;

	private final ArrayList<Socket> sockets = new ArrayList<Socket>();

	private final CellNetworkListenerServer listener = new CellNetworkListenerServer();

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

	public String getProps() {
		return properties;
	}

	/**
	 * Create internaly a new client
	 * 
	 * @param port
	 *            the connection port
	 * @param cells
	 *            the cells we will pass throw network
	 */
	private Server(int port, Cell[][] cells, ServerSocket svr, String password,
			String properties, Observer observer) {
		this.port = port;
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

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {
			while (true) {
				Socket sock = svr.accept();
				sockets.add(sock);
				addObserver(observer);

				Thread thr = new Thread(new ThreadServer(port, cells, sock,
						password, properties, observer));
				thr.start();
				if (properties.equals("wr")) {
					Thread tr = new Thread(new ThreadServerReceiving(cells,
							sock, getListener(), observer));
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
