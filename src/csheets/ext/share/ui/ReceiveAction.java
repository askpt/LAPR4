package csheets.ext.share.ui;

import java.awt.event.ActionEvent;
import java.util.Observer;

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

	/** generated id */
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

	@Override
	public void actionPerformed(ActionEvent event) {
		ReceiveUI rui = new ReceiveUI();
		Cell cellStart = focusOwner.getSelectedCell();
		rui.createUI(cellStart);
	}

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
	 * @param observer
	 *            the observer class
	 */
	public void clickOnSidebar(String IP, int port, String password,
			Observer observer) {
		Cell cellStart = focusOwner.getSelectedCell();
		ReceiveController rc = new ReceiveController();
		rc.startClient(IP, port, cellStart, password, observer);
	}

	/**
	 * Method that will get the active cell and sends that information to the
	 * controller
	 * 
	 * @param connection
	 *            the connection details
	 * @param observer
	 *            the observer class
	 */
	public void clickOnSidebar(Connections connection, Observer observer) {
		Cell cellStart = focusOwner.getSelectedCell();
		ReceiveController rc = new ReceiveController();
		rc.startClient(connection, cellStart, observer);
	}
}
