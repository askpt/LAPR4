package csheets.ext.persistance.exportdb.core;

import csheets.ext.Extension;
import csheets.ext.persistance.exportdb.ui.UIDatabaseExtension;
import csheets.ext.simple.ui.UIExtensionExample;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * A export extension just to show how the extension mechanism works.
 * An extension must extend the Extension abstract class.
 * The class that implements the Extension is the "bootstrap" of the extension.
 * @see Extension
 * @author 1110333 Tiago Pacheco
 */
@Deprecated
public class ExtensionExport extends Extension 
{

	/** The name of the extension */
	public static final String NAME = "Export";

	/**
	 * Creates a new Example extension.
	 */
	public ExtensionExport() 
	{
		super(NAME);
	}
	
	/**
	 * Returns the user interface extension of this extension (an instance of the class {@link  csheets.ext.simple.ui.UIExtensionExample}). <br/>
	 * In this extension example we are only extending the user interface.
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	public UIExtension getUIExtension(UIController uiController) 
	{
		return new UIDatabaseExtension(this, uiController);
	}
}
