package csheets.ext.database.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ui.ctrl.*;

public class Sync extends FocusOwnerAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected UIController uiController;

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
