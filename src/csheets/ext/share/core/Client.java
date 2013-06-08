package csheets.ext.share.core;

import java.io.*;
import java.net.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;

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

	private UIController control;

	private CellNetworkListener listener = new CellNetworkListener();

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
	 * @throws ClassNotFoundException
	 *             throw this exception if the object passed throw network was
	 *             invalid
	 * @throws FormulaCompilationException
	 *             throw if the cell does not respect the formula compiler
	 */
	private void receive(Cell cellStart, Socket cli) throws IOException,
			ClassNotFoundException, FormulaCompilationException {
		boolean isCell = true;
		int cellStartRow = cellStart.getAddress().getRow();
		int cellStartColumn = cellStart.getAddress().getColumn();
		OutputStream out = cli.getOutputStream();
		DataOutputStream outStream = new DataOutputStream(out);
		outStream.writeUTF("send me data");
		while (isCell) {
			ObjectInputStream inStream = new ObjectInputStream(
					cli.getInputStream());
			CellNetwork cell = (CellNetwork) inStream.readObject();
			if (cell.isCell()) {

				this.cellStart
						.getSpreadsheet()
						.getCell(cellStartColumn + cell.getColumn(),
								cellStartRow + cell.getRow())
						.setContent(cell.getContent());

				this.cellStart
						.getSpreadsheet()
						.getCell(cellStartColumn + cell.getColumn(),
								cellStartRow + cell.getRow())
						.addCellListener(listener);

			} else {
				isCell = false;
			}
		}
		outStream.writeUTF("Close yourself");
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
			JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		} catch (FormulaCompilationException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void updateCell(Cell cell, Socket sock, int column, int row) {
		Cell cellChanged = control.getActiveSpreadsheet().getCell(column, row);

	}
}
