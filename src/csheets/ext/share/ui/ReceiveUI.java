package csheets.ext.share.ui;

import javax.swing.JOptionPane;

import csheets.core.Cell;
import csheets.ext.share.controller.ReceiveController;

/**
 * Create a user interface for the receive action
 * 
 * @author Andre
 * 
 */
public class ReceiveUI {

    /**
     * Create a user interface for the receive action
     * 
     * @param cellStart
     *            the cell where we start the written process
     */
    public void createUI(Cell cellStart) {
	boolean portIsNotCorrect = true;
	boolean portAsNumber;
	int port = 0;
	String IP = "";

	boolean IPIsNotCorrect = true;
	while (IPIsNotCorrect) {
	    IP = JOptionPane
		    .showInputDialog("Please input an IP.\nIPv4 or localhost");
	    if (IP != null) {
		IPIsNotCorrect = !checkIFIPIsCorrect(IP);
	    } else {
		break;
	    }
	}

	while (portIsNotCorrect) {
	    String portTemp = JOptionPane
		    .showInputDialog("Please input a port (49152–65535)");
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

	if (!IPIsNotCorrect && !portIsNotCorrect) {
	    ReceiveController rc = new ReceiveController();
	    rc.startClient(IP, port, cellStart);
	}
    }

    /**
     * Check if the introduced IP was valid
     * 
     * @param IP
     *            IP to be checked
     * @return true if IP is valid or if equal to localhost
     */
    private boolean checkIFIPIsCorrect(String IP) {
	return IP
		.matches("^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$")
		|| IP.equalsIgnoreCase("localhost");
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
