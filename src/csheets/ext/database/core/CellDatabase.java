package csheets.ext.database.core;

/**
 * The cell structure for sync
 * 
 * @author Andre
 * 
 */
public class CellDatabase {
	/** content of the cell */
	private final String content;
	/** row of the cell */
	private final int row;
	/** column of the cell */
	private final int column;

	/**
	 * Creates a new cell structure for sync
	 * 
	 * @param content
	 *            content of the cell
	 * @param row
	 *            row of the cell
	 * @param column
	 *            column of the cell
	 */
	public CellDatabase(String content, int row, int column) {
		this.content = content;
		this.row = row;
		this.column = column;
	}

	/**
	 * Method that returns the content of the cell
	 * 
	 * @return the content of the cell
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Method that returns the row of the cell
	 * 
	 * @return the row of the cell
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Method that returns the column of the cell
	 * 
	 * @return the column of the cell
	 */
	public int getColumn() {
		return column;
	}
}
