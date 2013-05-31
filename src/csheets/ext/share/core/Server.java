package csheets.ext.share.core;

import java.io.*;
import java.net.*;

import csheets.core.Cell;

/**
 * Class that implement the server in the extension
 * 
 * @author Andre
 * 
 */
public class Server implements Runnable {
    /** the connection port */
    private int port;
    /** the cells we will pass throw network */
    private Cell[][] cells;

    /**
     * Create a new server
     */
    public Server() {
    }

    /**
     * Create internaly a new client
     * 
     * @param port
     *            the connection port
     * @param cells
     *            the cells we will pass throw network
     */
    private Server(int port, Cell[][] cells) {
	this.port = port;
	this.cells = cells;
    }

    /**
     * Method that will start the server and share the cells throw network
     * 
     * @param port
     *            connection port
     * @param cells
     *            value that will be shared throw network
     * @return the result of the connection
     */
    public void startServer(int port, Cell[][] cells) {
	Thread thr = new Thread(new Server(port, cells));
	thr.start();
    }

    /**
     * Method that will send the information throw network
     * 
     * @param cells
     *            value that will be shared throw network
     * @param svr
     *            the socket of connection
     * @throws IOException
     *             throw this exception if the I/O have errors
     */
    private void send(Cell[][] cells, ServerSocket svr) throws IOException {
	while (true) {
	    Socket sock = svr.accept();
	    DataInputStream in = new DataInputStream(sock.getInputStream());
	    System.out.println(in.readUTF());
	    sock.close();
	}
    }

    /**
     * Running thread
     */
    @Override
    public void run() {
	try {
	    ServerSocket svr = new ServerSocket(port);
	    send(cells, svr);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
