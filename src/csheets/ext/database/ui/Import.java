package csheets.ext.database.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ui.ctrl.*;

/**
 * Import submenu
 * 
 * @author João Carreira
 */
public class Import extends FocusOwnerAction 
{

    /* The user interface controller */
    protected UIController uiController;

    /**
     * Import action
     * @param uiController 
     */
    public Import(UIController uiController) 
    {
    	this.uiController = uiController;
    }

    /**
     * return name of the Export submenu
     * @return 
     */
    @Override
    protected String getName() 
    {
    	return "Import from database";
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
        /* select cells to import */
    	Cell [][]cells = focusOwner.getSelectedCells();
    	
        try 
    	{
    		UIImport uiImp = new UIImport(cells);
    	} 
    	catch (Exception e2) 
    	{
    		e2.printStackTrace();
    	}
    }
}
