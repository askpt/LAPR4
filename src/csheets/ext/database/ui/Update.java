package csheets.ext.database.ui;

import csheets.core.Cell;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Update submenu
 * 
 * @author João Carreira
 */
public class Update extends FocusOwnerAction 
{

    /* The user interface controller */
    protected UIController uiController;

    /**
     * Constructor
     * @param uiController 
     */
    public Update(UIController uiController) 
    {
    	this.uiController = uiController;
    }

    /**
     * return name of the Update submenu
     * @return 
     */
    @Override
    protected String getName() 
    {
    	return "Update with database";
    }

    /**
     * 
     */
    @Override
    protected void defineProperties() 
    {
    }

    /**
     * A simple action that presents a confirmation dialog. this method catch
     * the selected cells and create the UIExport
     * 
     * @param event
     *            the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        /* gettings the current spreadsheet  */
        Cell [][]cells = focusOwner.getSelectedCells();
        
        try 
        {
            UIUpdate uiUpdate = new UIUpdate(cells);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ImportAction.class.getName()).log(Level.SEVERE, null, ex);
        } 	
    }

}
