package csheets.ext.database.core;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerSync;

/**
 * Creates a new thread for the sync function
 * 
 * @author Andre
 * 
 */
public class ThreadSync implements Runnable {
	/** cells to be sync */
	private final Cell[][] cells;
	/** database details */
	private final String url, user, pass, tableName, dbName;

	/**
	 * Creates a new thread for sync function
	 * 
	 * @param cells
	 *            cells to be sync
	 * @param url
	 *            database url
	 * @param user
	 *            username
	 * @param pass
	 *            username's password
	 * @param table
	 *            table name
	 * @param dbName
	 *            database name
	 */
	public ThreadSync(Cell[][] cells, String url, String user, String pass,
			String table, String dbName) {
		this.cells = cells;
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.tableName = table;
		this.dbName = dbName;
	}

	@Override
	public void run() {
		ControllerSync sync = new ControllerSync();
		sync.connect(url, user, pass, dbName);
		sync.startSync(user, pass, cells, tableName);
	}

}
