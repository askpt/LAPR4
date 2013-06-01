package csheets.ext.share.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ui.ctrl.*;

/**
 * An action for the receive action in the sharing extension
 * 
 * @see FocusOwnerAction
 * @author Andre
 * 
 */
public class ReceiveAction extends FocusOwnerAction {

    private static final long serialVersionUID = 1L;
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
	ReceiveUI rui = new ReceiveUI();
	Cell cellStart = focusOwner.getSelectedCell();
	rui.createUI(cellStart);
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
