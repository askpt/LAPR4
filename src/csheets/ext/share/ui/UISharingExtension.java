package csheets.ext.share.ui;

import javax.swing.JMenu;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * This class implements the UI interface extension for the sharing extension.
 * 
 * @see UIExtension
 * @author Andre
 * 
 */
public class UISharingExtension extends UIExtension {

    /** The menu of the extension */
    private SharingMenu menu;

    /**
     * Creates a new UI for sharing extension
     * 
     * @param extension
     *            the sharing extension
     * @param uiController
     *            the user interface controller
     */
    public UISharingExtension(Extension extension, UIController uiController) {
	super(extension, uiController);
    }

    /**
     * Returns an instance of the SharingMenu
     * 
     * @see SharingMenu
     * @return a JMenu component
     */
    @Override
    public JMenu getMenu() {
	if (menu == null)
	    menu = new SharingMenu(uiController);

	return menu;
    }

}
