package csheets.ext.share.core;

import java.io.IOException;
import java.net.ServerSocket;

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

	try {
	    ServerSocket svr = new ServerSocket(port);
	    send(cells, svr);
	} catch (IOException e) {
	    e.printStackTrace();

	    return false;
	}

	return true;
    }

    private void send(Cell[][] cells, ServerSocket svr) {
	// TODO send cells
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
