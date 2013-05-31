package csheets.ext.share.core;

import java.io.*;
import java.net.*;

import csheets.core.Cell;

/**
 * Class that implement the client in the extension
 * 
 * @see Runnable
 * @author Andre
 * 
 */
public class Client implements Runnable {
    /** the ip of the server */
    private String IP;
    /** the port of the connection */
    private int port;
    /** the cell where the program will copy */
    private Cell cellStart;

    /**
     * Create a new client
     */
    public Client() {
    }

    /**
     * Create internaly a new client
     * 
     * @param IP
     *            of the server
     * @param port
     *            the port of the connection
     * @param cellStart
     *            the cell where the program will copy
     */
    private Client(String IP, int port, Cell cellStart) {
	this.IP = IP;
	this.port = port;
	this.cellStart = cellStart;
    }

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
    public void startClient(String IP, int port, Cell cellStart) {
	Thread thr = new Thread(new Client(IP, port, cellStart));
	thr.start();
    }

    /**
     * The method that will treat the incoming cells
     * 
     * @param cellStart
     *            cell where we paste the content
     * @param cli
     *            the socket of connection
     * @throws IOException
     *             throw this exception if the I/O have errors
     */
    private void receive(Cell cellStart, Socket cli) throws IOException {
	OutputStream out = cli.getOutputStream();
	DataOutputStream outStream = new DataOutputStream(out);
	outStream.writeUTF("test");
	cli.close();
    }

    /**
     * The running thread
     */
    @Override
    public void run() {
	try {
	    Socket cli = new Socket(IP, port);
	    receive(cellStart, cli);
	} catch (UnknownHostException e) {
	    e.printStackTrace();

	} catch (IOException e) {
	    e.printStackTrace();

	}
    }
}
