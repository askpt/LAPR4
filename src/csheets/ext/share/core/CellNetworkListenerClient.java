package csheets.ext.share.core;

import csheets.core.*;

/**
 * Class that will detect changes on client's cells This class implements
 * CellListener's interface Change the flag when any cell from client is changed
 * Just the method contentChanged(Cell cell) is implement, the other are not
 * used
 * 
 * @author Tiago
 * 
 */

public class CellNetworkListenerClient implements CellListener {
	/** flag control */
	private boolean flag = false;
	/** cell changed */
	private Cell cell;

	/**
	 * method to set the flag's value
	 * 
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;

	}

	/**
	 * method to set the cell
	 * 
	 * @param cell
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
	}

	/**
	 * method to return the cell changed
	 * 
	 * @return cell
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * method that returns the flag's value
	 * 
	 * @return flag
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * not implemented
	 */
	@Override
	public void valueChanged(Cell cell) {

	}

	@Override
	/** when a cell is changed this method change the flag and in other method of program will detect this 
	 * process and start to treat that
	 */
	public void contentChanged(Cell cell) {
		this.flag = true;

		this.cell = cell;

	}

	/**
	 * not implemented
	 */
	@Override
	public void dependentsChanged(Cell cell) {

	}

	/**
	 * not implemented
	 */
	@Override
	public void cellCleared(Cell cell) {

	}

	/**
	 * not implemented
	 */
	@Override
	public void cellCopied(Cell cell, Cell source) {

	}

}
