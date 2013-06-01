package csheets.ext.share.ui;

import javax.swing.JOptionPane;

import csheets.core.Cell;
import csheets.ext.share.controller.SendController;

/**
 * Create a user interface for the send action
 * 
 * @author Andre
 * 
 */
public class SendUI {

    /**
     * Create the UI for the send action
     * 
     * @param cells
     *            the cells to be shared
     */
    public void createUI(Cell[][] cells) {
	boolean portIsNotCorrect = true;
	boolean portAsNumber;
	int port = 0;
	while (portIsNotCorrect) {
	    String portTemp = JOptionPane
		    .showInputDialog("Please input a port (49152 to 65535)");
	    if (portTemp != null) {
		portAsNumber = checkIfANumber(portTemp);
		if (portAsNumber) {
		    port = Integer.parseInt(portTemp);
		    portIsNotCorrect = !checkPort(port);
		}
	    } else {
		break;
	    }
	}
	if (!portIsNotCorrect) {
	    SendController sc = new SendController();
	    sc.startServer(port, cells);
	}
    }

    /**
     * Check the port if is on allowed communication ports
     * 
     * @param port
     *            port to be tested
     * @return the result of the test
     */
    private boolean checkPort(int port) {
	return ((port > 49152) && (port < 65535));
    }

    /**
     * Check if the port passed is an number
     * 
     * @param port
     *            port to be tested
     * @return true if the port is a number
     */
    private boolean checkIfANumber(String port) {
	return port.matches("^[0-9]+$");
    }

}
