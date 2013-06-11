package csheets.ext.database.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import csheets.ui.ctrl.UIController;

/**
 * UI extension menu regarding databases
 * @author João Carreira
 */
public class DatabaseMenu extends JMenu 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1069502879234788510L;

        /**
	 * Creates a new simple menu.
	 * This constructor creates and adds the menu options. 
	 * In this simple example only one menu option is created.
	 * A menu option is an action (in this case {@link csheets.ext.simple.ui.ExampleAction})
	 * @param uiController the user interface controller
	 */
	public DatabaseMenu(UIController uiController) 
	{
            super("Database");
            setMnemonic(KeyEvent.VK_E);

            // menu options
            add(new Export(uiController));
            add(new Import(uiController));
            //add(new Update(uiController));
	}	
}
