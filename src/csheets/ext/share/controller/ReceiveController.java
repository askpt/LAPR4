package csheets.ext.share.controller;

import csheets.core.Cell;
import csheets.ext.share.core.Client;

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

}
