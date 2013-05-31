package csheets.ext.share.core;

import csheets.core.Cell;

/**
 * Class that implement the server in the extension
 * 
 * @author Andre
 * 
 */
public class Server {

    /**
     * Method that will start the server and share the cells throw network
     * 
     * @param port
     *            connection port
     * @param cells
     *            value that will be shared throw network
     * @return the result of the connection
     */
    public boolean startServer(int port, Cell[][] cells) {
	startConnection(port);
	send(cells);
	return true;
    }

    private void send(Cell[][] cells) {
	// TODO create server
	throw new UnsupportedOperationException("Not supported yet.");
    }

    private void startConnection(int port) {
	// TODO send cells using network
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
