package csheets.ext.database.core;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;

/**
 * The action consisting in importing data from a database table to a cleansheets 
 * spreadsheet
 * @author João Carreira
 */
public class ImportAction extends BaseAction 
{
	/** The user interface controller */
	protected UIController uiController;
        
        String[][] tableData;

	/**
	 * Creates a new action.
	 * @param uiController the user interface controller
	 */
	public ImportAction(UIController uiController) 
        {
		this.uiController = uiController;
	}

	protected String getName() 
        {
		return "Importing data from database";
	}

	protected void defineProperties() 
        {
	}
        
	public void actionPerformed(ActionEvent event) 
        {
		try 
                {
			this.uiController.getActiveSpreadsheet().getCell(0, 0).setContent("Changed");
		} 
                catch (Exception ex) 
                {
			
		}
	}

    public void writeToSpreadSheet(ActionEvent e, String[][] tableData) 
    {
       this.tableData = tableData;
       actionPerformed(e);
    }
}
