package csheets.ext.share.core;

import csheets.core.*;

public class CellNetworkListenerServer implements CellListener {
	boolean flag = false;

	@Override
	public void valueChanged(Cell cell) {

		this.flag = true;
		this.flag = true;
		System.out.println("Celula alterada: " + cell.getContent());

	}

	@Override
	public void contentChanged(Cell cell) {
		this.flag = true;

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
