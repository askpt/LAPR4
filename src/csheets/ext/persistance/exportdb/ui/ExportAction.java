package csheets.ext.persistance.exportdb.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ui.ctrl.*;

/**
 * An action of the export extension that exemplifies how to interact with the
 * spreadsheet.
 * 
 * @author 1110333 Tiago Pacheco
 */
public class ExportAction extends FocusOwnerAction {

    /** The user interface controller */
    protected UIController uiController;

    /**
     * Creates a new action.
     * 
     * @param uiController
     *            the user interface controller
     */
    public ExportAction(UIController uiController) {
	this.uiController = uiController;
    }

    @Override
    protected String getName() {
	return "Export...";
    }

    @Override
    protected void defineProperties() {
    }

    /**
     * A simple action that presents a confirmation dialog. this method catch
     * the selected cells and create the UIExport
     * 
     * @param event
     *            the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {

	Cell[][] cells = focusOwner.getSelectedCells();

	try {
	    UIExport uiExp = new UIExport(cells);
	} catch (Exception e2) {
	    e2.printStackTrace();
	}

    }
}
