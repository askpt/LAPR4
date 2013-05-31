package csheets.ext.share.core;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

import csheets.core.Cell;

/**
 * Class that implement the client in the extension
 * 
 * @author Andre
 * 
 */
public class Client {
    /**
     * Method that will start the client and receive cells throw network
     * 
     * @param IP
     *            the server ip
     * @param port
     *            the connection port of the server
     * @param cellStart
     *            cell where we paste the content of the shared cells
     * @return the result of the connection
     */
    public boolean startClient(String IP, int port, Cell cellStart) {

	try {
	    Socket cli = new Socket(IP, port);
	    receive(cellStart, cli);
	} catch (UnknownHostException e) {
	    Logger.getLogger(getClass()).log(Level.SEVERE, null, e);

	    return false;
	} catch (IOException e) {
	    Logger.getLogger(getClass()).log(Level.SEVERE, null, e);

	    return false;
	}

	return true;
    }

    private void receive(Cell cellStart, Socket cli) {
	// TODO receive cells using network
	throw new UnsupportedOperationException("Not supported yet.");
    }

}
