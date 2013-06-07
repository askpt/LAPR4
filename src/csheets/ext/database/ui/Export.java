package csheets.ext.database.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ui.ctrl.*;

/**
 * Export submenu
 * 
 * @author João Carreira
 */
public class Export extends FocusOwnerAction 
{

    /* The user interface controller */
    protected UIController uiController;

    /**
     * Export action
     * @param uiController 
     */
    public Export(UIController uiController) 
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
    	return "Export to database";
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
        /* select cells to export */
    	Cell [][]cells = focusOwner.getSelectedCells();
    	
        try 
    	{
    		UIExport uiExp = new UIExport(cells);
    	} 
    	catch (Exception e2) 
    	{
    		e2.printStackTrace();
    	}
    }
}
