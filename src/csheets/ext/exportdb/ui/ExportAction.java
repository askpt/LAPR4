package csheets.ext.exportdb.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ext.exportdb.controllers.ControllerExport;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;

/**
 * An action of the export extension that exemplifies how to interact with the spreadsheet.
 * @author 1110333 Tiago Pacheco
 */
public class ExportAction extends FocusOwnerAction {

	/** The user interface controller */
	protected UIController uiController;

	/**
	 * Creates a new action.
	 * @param uiController the user interface controller
	 */
	public ExportAction(UIController uiController) {
		this.uiController = uiController;
	}

	protected String getName() {
		return "Export...";
	}

	protected void defineProperties() {
	}

	/**
	 * A simple action that presents a confirmation dialog.
	 * this method catch the selected cells and create the UIExport 
	 * @param event the event that was fired
	 */
	public void actionPerformed(ActionEvent event) {

		Cell[][] cells = focusOwner.getSelectedCells();
	
		try {
			UIExport uiExp=new UIExport(cells);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	

	
	}
}
