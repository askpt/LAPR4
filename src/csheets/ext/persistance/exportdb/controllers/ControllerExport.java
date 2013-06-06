package csheets.ext.persistance.exportdb.controllers;


import csheets.core.*;
import csheets.ext.persistance.exportdb.core.ConnectionHSQLDB;
import csheets.ext.persistance.exportdb.ui.UIExport;
/**
 * This class represents the controller that do the connection to dataBase, call create and insert methods of dataBaseDriver
 * @author Tiago Pacheco
 *
 */

public class ControllerExport 
{
	private String url="jdbc:hsqldb:" + "db_file_name_prefix";
	
	public ControllerExport()
	{
		
	}
	
	
	public ControllerExport(UIExport uiExport)
	{
		/* call here new Database facade */
	}
	
	
	public void ExportSelectedCells(Cell[][]cells,String user, String pass, String tableName) throws Exception
	{
		ConnectionHSQLDB con = new ConnectionHSQLDB();
		con.connect(url, user, pass);
		con.insertTable(cells,tableName);
		con.desconnect();
	}
}
