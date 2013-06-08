package csheets.ext.share.controller;

import java.util.List;

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
     */
    public void startClient(String IP, int port, Cell cellStart) {
	Client cli = new Client();
	cli.startClient(IP, port, cellStart);
    }

    /**
     * Find a servers that have an active sharing
     * 
     * @return a list of servers with active sharing
     */
    public List<Connections> findServers() {
	return ClientDiscover.getInstance().findServers();
    }

}
