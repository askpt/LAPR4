package csheets.ext.database.core;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerSync;

public class ThreadSync implements Runnable {

	private final Cell[][] cells;
	private final String url, user, pass, tableName, dbName;

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
