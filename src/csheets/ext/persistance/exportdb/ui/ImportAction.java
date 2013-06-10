package csheets.ext.persistance.exportdb.ui;

import java.awt.event.ActionEvent;

import csheets.core.Cell;
import csheets.ui.ctrl.*;

/**
 * An action of the import extension 
 * @author João Carreira
 */
@Deprecated
public class ImportAction extends FocusOwnerAction 
{
	private static final long serialVersionUID = 1L;
	
	/** The user interface controller **/
    protected UIController uiController;

    /**
     * Creates a new action.
     */
    public ImportAction(UIController uiController) 
    {
    	this.uiController = uiController;
    }

    @Override
    protected String getName() 
    {
    	return "Import";
    }

    @Override
    protected void defineProperties() 
    {
    }

    @Override
    public void actionPerformed(ActionEvent event) 
    {
    	/* add code here */
    }
}
