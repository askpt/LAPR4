package csheets.ext.database.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ui.ctrl.*;

/**
 * Class that will create a JMenu item for the sync
 * 
 * @author Andre
 * 
 */
public class Sync extends FocusOwnerAction {

	/** generated id */
	private static final long serialVersionUID = 1L;
	/** ui controller */
	protected UIController uiController;

	/**
	 * Creates a new sync menu item
	 * 
	 * @param uiController
	 *            ui controller
	 */
	public Sync(UIController uiController) {
		this.uiController = uiController;
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		Cell[][] cells = focusOwner.getSelectedCells();
		try {
			UISync uiSync = new UISync(cells);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected String getName() {
		return "Sync";
	}

}
