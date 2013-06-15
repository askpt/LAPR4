package csheets.ext.share.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ext.share.controller.ReceiveController;
import csheets.ext.share.core.Connections;
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

	/** The first instance of this action */
	private static ReceiveAction instance;

	/**
	 * Method that returns the first instance of this action
	 * 
	 * @return the first instance of this action
	 */
	protected static ReceiveAction getInstance() {
		return instance;
	}

	/**
	 * Creates a new receive action
	 * 
	 * @param uiController
	 *            user interface controller
	 */
	public ReceiveAction(UIController uiController) {
		this.uiController = uiController;
		if (instance == null) {
			instance = this;
		}
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

	/**
	 * Method that will get the active cell and sends that information to the
	 * controller
	 * 
	 * @param IP
	 *            the server IP
	 * @param port
	 *            the connection port
	 * @param password
	 *            the connection password
	 */
	public void clickOnSidebar(String IP, int port, String password) {
		Cell cellStart = focusOwner.getSelectedCell();
		ReceiveController rc = new ReceiveController();
		rc.startClient(IP, port, cellStart, password);
	}

	/**
	 * Method that will get the active cell and sends that information to the
	 * controller
	 * 
	 * @param connection
	 *            the connection details
	 */
	public void clickOnSidebar(Connections connection) {
		Cell cellStart = focusOwner.getSelectedCell();
		ReceiveController rc = new ReceiveController();
		rc.startClient(connection, cellStart);
	}
}
