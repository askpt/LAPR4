package csheets.ext.share.core;

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

	startClient(IP, port);
	receive(cellStart);

	System.out.println(IP + ":" + port);
	System.out.println(cellStart.getContent());

	return true;
    }

    private void receive(Cell cellStart) {
	// TODO receive cells using network
	throw new UnsupportedOperationException("Not supported yet.");
    }

    private void startClient(String iP, int port) {
	// TODO create client
	throw new UnsupportedOperationException("Not supported yet.");
    }

}
