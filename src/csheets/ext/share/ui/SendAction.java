package csheets.ext.share.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import csheets.core.Cell;
import csheets.ext.share.controller.SendController;
import csheets.ui.ctrl.*;

/**
 * An action for the send action in the sharing extension
 * 
 * @see FocusOwnerAction
 * @author Andre
 * 
 */
public class SendAction extends FocusOwnerAction {

    /** User Interface Controller */
    protected UIController uiController;

    /**
     * Creates a new send action
     * 
     * @param uiController
     *            user interface controller
     */
    public SendAction(UIController uiController) {
	this.uiController = uiController;
    }

    /**
     * This method will create a new UI for the server
     * 
     * @param event
     *            the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {
	// SendUI sUI = new SendUI();
	// TODO create sidebar ui

	// TODO create threads

	String portTemp = JOptionPane.showInputDialog("Please input a port");
	int port = Integer.parseInt(portTemp);
	Cell[][] cells = focusOwner.getSelectedCells();

	SendController sc = new SendController();
	if (sc.startServer(port, cells))
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
	return "Send";
    }
}
