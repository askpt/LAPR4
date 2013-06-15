package csheets.ext.share.ui;

import javax.swing.JOptionPane;

import csheets.core.Cell;
import csheets.ext.share.controller.SendController;
import csheets.ext.share.core.Validate;

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
				portAsNumber = Validate.checkIfANumber(portTemp);
				if (portAsNumber) {
					port = Integer.parseInt(portTemp);
					portIsNotCorrect = !Validate.checkPort(port);
				}
			} else {
				break;
			}
		}
		if (!portIsNotCorrect) {
			SendController sc = new SendController();
			String password = JOptionPane
					.showInputDialog("Please input the password!");
			sc.startServer(port, cells, Validate.encrypt(password.getBytes()));
		}
	}
}
