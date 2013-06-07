package csheets.ext.database;

import csheets.ext.Extension;
import csheets.ext.database.ui.UIDatabaseExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;


/**
 * Database extension
 * @author João Carreira
 */
public class DatabaseExtension extends Extension 
{
	/* The name of the extension */
	public static final String NAME = "Database";

	public DatabaseExtension() 
	{
		super(NAME);
	}
	
        /**
         * returns the GUI for this extension
         * @param uiController
         * @return 
         */
        @Override
	public UIExtension getUIExtension(UIController uiController) 
	{
		return new UIDatabaseExtension(this, uiController);
	}
}
