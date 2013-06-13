package csheets.ext.database.controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

import csheets.core.Cell;
import csheets.ext.database.core.*;

public class ControllerSync {

	List<Database> dbList;
	DatabaseFacade facade;

	public ControllerSync() {
		facade = new DatabaseFacade();
	}

	public String[][] getDBlist() throws FileNotFoundException {
		/* new DBCsvReader */
		DBCsvReader reader = new DBCsvReader();
		/* instantiating a new arraylist and getting all databases */
		dbList = new ArrayList<Database>();
		dbList = reader.getDBList();

		/* String array to store only the name of the databases */
		String[][] driversName = new String[dbList.size()][2];
		int i = 0;
		for (; i < dbList.size(); i++) {
			driversName[i][0] = dbList.get(i).getName();
			driversName[i][1] = dbList.get(i).getUrl();
		}
		/* returns all names of supported databases */
		return driversName;
	}

	public void startSync(String user, String pass, Cell[][] cells,
			String tableName) {
		facade.startSync(user, pass, cells, tableName);
	}

	public void connect(String url, String user, String pass, String dbName) {
		try {
			facade.createConnection(url, user, pass, dbName);
		}
		/* replace below with proper exceptions */
		catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} catch (Exception e) {
		}
	}

}
