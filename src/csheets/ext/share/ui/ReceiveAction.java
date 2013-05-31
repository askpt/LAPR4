package csheets.ext.share.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import csheets.core.Cell;
import csheets.ext.share.controller.ReceiveController;
import csheets.ui.ctrl.*;

/**
 * An action for the receive action in the sharing extension
 * 
 * @see FocusOwnerAction
 * @author Andre
 * 
 */
public class ReceiveAction extends FocusOwnerAction {

    /** User Interface Controller */
    protected UIController uiController;

    /**
     * Creates a new receive action
     * 
     * @param uiController
     *            user interface controller
     */
    public ReceiveAction(UIController uiController) {
	this.uiController = uiController;
    }

    /**
     * This method will create a new UI for the client
     * 
     * @param event
     *            the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {
	// ReceiveUI = new ReceiveUI();
	// TODO create sidebar UI (Receive)

	// TODO create threads

	String IP = JOptionPane.showInputDialog("Please input an IP");
	String portTemp = JOptionPane.showInputDialog("Please input a port!");
	int port = Integer.parseInt(portTemp);
	Cell cellStart = focusOwner.getSelectedCell();

	ReceiveController rc = new ReceiveController();
	if (rc.startClient(IP, port, cellStart))
	    JOptionPane.showMessageDialog(focusOwner, "Sucessfull");
	else
	    JOptionPane.showMessageDialog(focusOwner, "Unsucessfull");
    }

    /**
     * The method that will define the name in the bar
     * 
     * @return the name in the bar
     */
    @Override
    protected String getName() {
	return "Receive";
    }

}
