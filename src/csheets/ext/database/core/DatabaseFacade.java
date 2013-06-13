package csheets.ext.database.core;

import java.sql.SQLException;

import csheets.core.Cell;

/**
 * A class that deals with all the data going into and from the databases (UML
 * facade pattern)
 * 
 * @author João Carreira
 */
public class DatabaseFacade {
	private DBConnectionStrategy adapter;
	private String tableName;
	private Cell[][] cells;
	private int[] pk;

	/**
	 * constructor
	 */
	public DatabaseFacade() {
	}

	/**
	 * creates a connection to a database driver
	 * 
	 * @param url
	 *            path to the driver
	 * @param user
	 *            username
	 * @param pass
	 *            password
	 */
	public void createConnection(String url, String user, String pass,
			String dbName) throws SQLException, ClassNotFoundException,
			Exception {
		/* gets factory instance */
		DBConnectionFactory factory = DBConnectionFactory.getInstance();
		/* gets the corresponding adapter based on the adaptee class name */
		adapter = factory.getDBTechnology(dbName);
		adapter.createConnection(url, user, pass);
	}

	/**
	 * exports data to a database
	 * 
	 * @param cells
	 *            cells to be exported
	 * @param tableName
	 *            table's name in the database
	 */
	public void exportData(Cell[][] cells, String tableName) {
		this.tableName = tableName;
		this.cells = cells;
		adapter.createTable(tableName, cells);
		adapter.disconnet();
	}

	/**
	 * gets the table list of a given database
	 */
	public String[] getTableList() {
		return adapter.getTableList(adapter.queryToArray());
	}

	/**
	 * loads data from a table of the database
	 * 
	 * @param tableName
	 *            name of the table
	 */
	public String[][] loadTable(String tableName) {
		return adapter.getTableContent(tableName);
	}

	/**
	 * Starts a new sync with database
	 * 
	 * @param user
	 *            username
	 * @param pass
	 *            username's password
	 * @param cells
	 *            cells to be sync
	 * @param tableName
	 *            name of the table
	 */
	public void startSync(String user, String pass, Cell[][] cells,
			String tableName) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
