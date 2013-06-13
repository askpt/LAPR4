package csheets.ext.database.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Import submenu
 * 
 * @author João Carreira
 */
public class ImportAction extends FocusOwnerAction 
{

    /* The user interface controller */
    protected UIController uiController;

    /**
     * Import action
     * @param uiController 
     */
    public ImportAction(UIController uiController) 
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
    	Cell cell = focusOwner.getSelectedCell();
        try 
        {
            UIImport uiImp = new UIImport(cell);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ImportAction.class.getName()).log(Level.SEVERE, null, ex);
        } 	
    }
}
