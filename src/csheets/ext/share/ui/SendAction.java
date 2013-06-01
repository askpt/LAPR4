package csheets.ext.share.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
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
	SendUI sui = new SendUI();
	Cell[][] cells = focusOwner.getSelectedCells();
	sui.createUI(cells);

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
