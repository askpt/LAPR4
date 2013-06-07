package csheets.ext.database.ui;

import javax.swing.*;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.*;

/**
 * UI extension that offers database options (export, import and update)
 * @see UIExtension
 * @author João Carreira
 */
public class UIDatabaseExtension extends UIExtension 
{

    /** The icon to display with the extension's name */
    private Icon icon;

    /** The menu of the extension */
    private DatabaseMenu menu;

    /**
     * constructor
     * @param extension
     * @param uiController 
     */
    public UIDatabaseExtension(Extension extension, UIController uiController) 
    {
    	super(extension, uiController);
    }

    /**
     * return the icon
     * @return Icon object
     */
    @Override
    public Icon getIcon() 
    {
    	return null;
    }

    /**
     * return instance that implements JMenu
     * @return 
     */
    @Override
    public JMenu getMenu() 
    {
	if (menu == null)
        {
	    menu = new DatabaseMenu(uiController);
        }
	return menu;
    }

    /**
     * return a cell decorator
     * @return 
     */
    @Override
    public CellDecorator getCellDecorator() 
    {
    	return null;
    }

    /**
     * returns a table decorator
     * @return 
     */
    @Override
    public TableDecorator getTableDecorator() 
    {
    	return null;
    }

    /**
     * return a toolbar specific to the extension
     * @return 
     */
    @Override
    public JToolBar getToolBar() 
    {
    	return null;
    }

   /**
    * return a sidebar specific to the extension
    * @return 
    */
    @Override
    public JComponent getSideBar() 
    {
	return null;
    }
}
