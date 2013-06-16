package csheets.ext.share.controller;

import java.util.Observer;

import csheets.core.Cell;
import csheets.ext.share.core.Server;

/**
 * Controller of the send action
 * 
 * @author Andre
 * 
 */
public class SendController {
	/**
	 * Method that will create a new server and will start the connection
	 * 
	 * @param port
	 *            connection port of the cells
	 * @param cells
	 *            value that will be shared throw network
	 * @param password
	 *            the connection password
	 * @param observer
	 *            the observer class
	 */
	public void startServer(int port, Cell[][] cells, String password,
			String properties, Observer observer) {
		Server svr = Server.getInstance();
		svr.startServer(port, cells, password, properties, observer);
	}

}
