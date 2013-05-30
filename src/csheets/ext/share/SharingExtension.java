package csheets.ext.share;

import csheets.ext.Extension;
import csheets.ext.share.ui.UISharingExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension that will share cells throw network.
 * 
 * @see Extension
 * @author Andre
 * 
 */
public class SharingExtension extends Extension {

    /** The name of the extension */
    public static final String NAME = "Sharing";

    /**
     * Creates a new Sharing extension
     */
    public SharingExtension() {
	super(NAME);
    }

    /**
     * Returns the user interface extension of this extension.
     * 
     * @param uiController
     *            the user interface controller
     * @return a user interface extension
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
	return new UISharingExtension(this, uiController);
    }
}
