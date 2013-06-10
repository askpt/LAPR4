package csheets.ext.share.core;

import csheets.core.*;

public class CellNetworkListenerServer implements CellListener {
	private boolean flag = false;
	private Cell cell = null;

	public void setFlag(boolean flag) {
		this.flag = flag;

	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Cell getCell() {
		return cell;
	}

	public boolean getFlag() {
		return flag;
	}

	@Override
	public void valueChanged(Cell cell) {

		this.flag = true;

	}

	@Override
	public void contentChanged(Cell cell) {
		this.flag = true;
		this.cell = cell;

	}

	@Override
	public void dependentsChanged(Cell cell) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void cellCleared(Cell cell) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void cellCopied(Cell cell, Cell source) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
