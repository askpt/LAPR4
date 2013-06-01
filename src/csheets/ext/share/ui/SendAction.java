package csheets.ext.share.ui;

import java.awt.event.ActionEvent;

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

    private static final long serialVersionUID = 1L;
    /** User Interface Controller */
    protected UIController uiController;

    /** The first instance of this action */
    protected static SendAction instance;

    /**
     * Method that returns the first instance of this action
     * 
     * @return the first instance of this action
     */
    public static SendAction getInstance() {
	return instance;
    }

    /**
     * Creates a new send action
     * 
     * @param uiController
     *            user interface controller
     */
    public SendAction(UIController uiController) {
	this.uiController = uiController;
	if (instance == null) {
	    instance = this;
	}
    }

    /**
     * This method will create a new UI for the server
     * 
     * @param event
     *            the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {
	System.out.println(uiController.getActiveWorkbook());
	SendUI sui = new SendUI();
	Cell[][] cells = focusOwner.getSelectedCells();
	sui.createUI(cells);

    }

    /**
     * Method that will get the active cell and sends that information to the
     * controller
     * 
     * @param port
     *            the connection port
     */
    public void clickOnSidebar(int port) {
	Cell[][] cells = focusOwner.getSelectedCells();
	SendController sc = new SendController();
	sc.startServer(port, cells);
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
