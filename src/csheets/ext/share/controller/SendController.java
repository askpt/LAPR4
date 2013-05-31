package csheets.ext.share.controller;

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
     */
    public void startServer(int port, Cell[][] cells) {
	Server svr = new Server();
	svr.startServer(port, cells);
    }

}
