package csheets.ext.persistance.exportdb.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import csheets.ui.ctrl.UIController;

/**
 * Representes the UI extension menu of the export extension.
 * @author 1110333 Tiago Pacheco
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
		super("Connect with database");
		setMnemonic(KeyEvent.VK_E);

		// Adds actions
		add(new ExportAction(uiController));
		add(new ImportAction(uiController));
		add(new UpdateAction(uiController));
	}	
}
