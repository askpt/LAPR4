package csheets.ext.share.core;

import java.io.Serializable;

/**
 * The object that will be passed throw network (content of the cell, row,
 * column and if is a cell)
 * 
 * @author Andre
 * 
 */
public class CellNetwork implements Serializable {
    /** content of the cell */
    private final String content;
    /** row of the original cell */
    private final int row;
    /** column of the original cell */
    private final int column;
    /** true if is a cell */
    private final boolean isCell;

    /**
     * Create a new object to be passed throw network
     * 
     * @param content
     *            content of the cell
     * @param row
     *            row of the original cell
     * @param column
     *            column of the original cell
     * @param isCell
     *            true if is a cell
     */
    public CellNetwork(String content, int row, int column, boolean isCell) {
	this.content = content;
	this.row = row;
	this.column = column;
	this.isCell = isCell;
    }

    /**
     * Method that will say if the passed object was a cell
     * 
     * @return true if is a cell
     */
    public boolean isCell() {
	return isCell;
    }

    /**
     * Method that returns the content of the original cell
     * 
     * @return the content of the original cell
     */
    public String getContent() {
	return content;
    }

    /**
     * Method that returns the row of the original cell
     * 
     * @return the row of the original cell
     */
    public int getRow() {
	return row;
    }

    /**
     * Method that returns the column of the original cell
     * 
     * @return the column of the original cell
     */
    public int getColumn() {
	return column;
    }

}
