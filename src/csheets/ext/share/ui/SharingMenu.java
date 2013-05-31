package csheets.ext.share.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import csheets.ui.ctrl.UIController;

/**
 * The class that will add the menu elements to the main menu of the application
 * 
 * @see JMenu
 * 
 * @author Andre
 * 
 */
public class SharingMenu extends JMenu {

    /**
     * Creates a simple menu bar with the options to choose a Receive or a
     * Sharing Action
     * 
     * @param uiController
     *            the user interface controller
     */
    public SharingMenu(UIController uiController) {
	super("Sharing");
	setMnemonic(KeyEvent.VK_S);

	// Sub-extensions
	add(new ReceiveAction(uiController));
	add(new SendAction(uiController));
    }
}
