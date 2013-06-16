package csheets.ext.share.controller;

import java.util.*;

import csheets.core.Cell;
import csheets.ext.share.core.*;

/**
 * Controller of the receive action
 * 
 * @author Andre
 * 
 */
public class ReceiveController {

	/**
	 * Method that will create a new client and will start the connection
	 * 
	 * @param IP
	 *            the server ip
	 * @param port
	 *            the connection port of the server
	 * @param cellStart
	 *            cell where we paste the content of the shared cells
	 * @param password
	 *            the connection password
	 * @param observer
	 *            the observer class
	 */
	public void startClient(String IP, int port, Cell cellStart,
			String password, Observer observer) {
		Client cli = new Client();
		cli.startClient(IP, port, cellStart, password, observer);
	}

	/**
	 * Find a servers that have an active sharing
	 * 
	 * @param observer
	 *            the observer class
	 * @return a list of servers with active sharing
	 */
	public List<Connections> findServers(Observer observer) {
		return ClientDiscover.getInstance().findServers(observer);
	}

	/**
	 * Method that will create a new client and will start the connection
	 * 
	 * @param connection
	 *            the connection details
	 * @param cellStart
	 *            cell where we paste the content of the shared cells
	 * @param observer
	 *            the observer class
	 */
	public void startClient(Connections connection, Cell cellStart,
			Observer observer) {
		Client cli = new Client();
		cli.startClient(connection, cellStart, observer);
	}

}
