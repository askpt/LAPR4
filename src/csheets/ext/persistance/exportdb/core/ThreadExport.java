package csheets.ext.persistance.exportdb.core;

import csheets.core.Cell;
import csheets.ext.persistance.exportdb.controllers.ControllerExport;


/**
 * Representes the thread
 * @author 1110333 Tiago Pacheco
 */
@Deprecated
public class ThreadExport implements Runnable {

	private Cell[][]cells;
	private String user;
	private String pass;
	private String tableName;
	private ControllerExport control;
	public ThreadExport(Cell[][]cells,String user, String pass, String tableName)
	{
		this.cells=cells;
		this.user=user;
		this.pass=pass;
		this.tableName=tableName;
	}
	@Override
	public void run() {
		control=new ControllerExport();
		 try {
			 control.ExportSelectedCells(cells, user, pass, tableName);
		           
		        } catch (Exception ex) {
		            ex.printStackTrace();
	}

}
}
