package csheets.ext.share.ui;

import javax.swing.JOptionPane;

import csheets.core.Cell;
import csheets.ext.share.controller.ReceiveController;
import csheets.ext.share.core.Validate;

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
				IPIsNotCorrect = !Validate.checkIFIPIsCorrect(IP);
			} else {
				break;
			}
		}

		while (portIsNotCorrect) {
			String portTemp = JOptionPane
					.showInputDialog("Please input a port (49152 to 65535)");
			if (portTemp != null) {
				portAsNumber = Validate.checkIfANumber(portTemp);
				if (portAsNumber) {
					port = Integer.parseInt(portTemp);
					portIsNotCorrect = !Validate.checkPort(port);
				}
			} else {
				break;
			}
		}

		if (!IPIsNotCorrect && !portIsNotCorrect) {
			String password = JOptionPane.showInputDialog("Enter password");
			ReceiveController rc = new ReceiveController();
			rc.startClient(IP, port, cellStart,
					Validate.encrypt(password.getBytes()));
		}
	}
}
