package csheets.ext.share;

import java.io.IOException;
import java.util.logging.*;

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

	/** The log file size = 1MB */
	public static final int LOGGER_SIZE = 1024 * 1024;

	/**
	 * Creates a new Sharing extension
	 */
	public SharingExtension() {
		super(NAME);
		try {
			Handler fh = new FileHandler("sharingExtension.log", LOGGER_SIZE,
					1, true);
			Logger.getLogger("csheets.ext.share").setUseParentHandlers(false);
			Logger.getLogger("csheets.ext.share").addHandler(fh);
		} catch (SecurityException e) {
		} catch (IOException e) {
		}
	}

	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UISharingExtension(this, uiController);
	}
}
