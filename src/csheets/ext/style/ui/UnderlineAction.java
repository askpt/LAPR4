/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package csheets.ext.style.ui;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;

/**
 * An underlining operation.
 * @author Einar Pehrson
 */
@SuppressWarnings("serial")
public class UnderlineAction extends StyleAction {

	/**
	 * Creates a new underlining action.
	 * @param uiController the user interface controller
	 */
	public UnderlineAction(UIController uiController) {
		super(uiController);
	}

	protected String getName() {
		return "Underline";
	}

	protected void defineProperties() {
		setEnabled(false);
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(SMALL_ICON, new ImageIcon(StyleExtension.class.getResource("res/img/font_underline.gif")));
	}

	/**
	 * Toggles the underlining of the selected cells in the focus owner table.
	 * @param cell the cell to which style should be applied
	 */
	protected void applyStyle(StylableCell cell) {

	}
}