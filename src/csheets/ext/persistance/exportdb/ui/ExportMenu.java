package csheets.ext.persistance.exportdb.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import csheets.ui.ctrl.UIController;

/**
 * Representes the UI extension menu of the export extension.
 * @author 1110333 Tiago Pacheco
 */
public class ExportMenu extends JMenu {

	/**
	 * Creates a new simple menu.
	 * This constructor creates and adds the menu options. 
	 * In this simple example only one menu option is created.
	 * A menu option is an action (in this case {@link csheets.ext.simple.ui.ExampleAction})
	 * @param uiController the user interface controller
	 */
	public ExportMenu(UIController uiController) {
		super("Export");
		setMnemonic(KeyEvent.VK_E);

		// Adds font actions
		add(new ExportAction(uiController));
	}	
}
