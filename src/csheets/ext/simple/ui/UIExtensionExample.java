package csheets.ext.simple.ui;

import javax.swing.*;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.*;

/**
 * This class implements the UI interface extension for the simple extension. A
 * UI interface extension must extend the UIExtension abstract class.
 * 
 * @see UIExtension
 * @author Alexandre Braganca
 */
public class UIExtensionExample extends UIExtension {

    /** The icon to display with the extension's name */
    private Icon icon;

    /** The menu of the extension */
    private ExampleMenu menu;

    public UIExtensionExample(Extension extension, UIController uiController) {
	super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name.
     * 
     * @return an icon with style
     */
    @Override
    public Icon getIcon() {
	return null;
    }

    /**
     * Returns an instance of a class that implements JMenu. In this simple case
     * this class only supplies one menu option.
     * 
     * @see ExampleMenu
     * @return a JMenu component
     */
    @Override
    public JMenu getMenu() {
	if (menu == null)
	    menu = new ExampleMenu(uiController);
	return menu;
    }

    /**
     * Returns a cell decorator that visualizes the data added by the extension.
     * 
     * @return a cell decorator, or null if the extension does not provide one
     */
    @Override
    public CellDecorator getCellDecorator() {
	return null;
    }

    /**
     * Returns a table decorator that visualizes the data added by the
     * extension.
     * 
     * @return a table decorator, or null if the extension does not provide one
     */
    @Override
    public TableDecorator getTableDecorator() {
	return null;
    }

    /**
     * Returns a toolbar that gives access to extension-specific functionality.
     * 
     * @return a JToolBar component, or null if the extension does not provide
     *         one
     */
    @Override
    public JToolBar getToolBar() {
	return null;
    }

    /**
     * Returns a side bar that gives access to extension-specific functionality.
     * 
     * @return a component, or null if the extension does not provide one
     */
    @Override
    public JComponent getSideBar() {
	return null;
    }
}
