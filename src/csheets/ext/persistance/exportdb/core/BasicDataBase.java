package csheets.ext.persistance.exportdb.core;

import csheets.core.Cell;

/**
 * Representes the interface to the classes implements this methodes
 * The classes that will implement this interface will specify this methods to any driver
 * @author 1110333 Tiago Pacheco
 */
public interface BasicDataBase {
	
	 public void connect(String url, String user, String pass) throws Exception;
	 public void desconnect();
	 public void executeSQL(String sql);
	 public void insertTable(Cell[][]cells,String tableName) throws Exception;
}
