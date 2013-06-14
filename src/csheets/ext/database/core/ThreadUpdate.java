package csheets.ext.database.core;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.database.controller.ControllerImport;
import csheets.ext.database.controller.ControllerUpdate;
import csheets.ext.database.ui.UITableSelect;
import csheets.ext.database.ui.UITableSelectUpdate;

/**
 * The thread responsible for the update of Cleansheet's content with a database
 * @author João Carreira
 */
public class ThreadUpdate implements Runnable
{
    private String url, user, pass, dbName;
    private ControllerUpdate ctrlUp;
    private Cell [][]cells;
    
    /**
     * construtor
     * @param url path to the database
     * @param user username
     * @param pass password
     * @param tableName table in the database
     * @param dbName database name
     * @param ctrlUp ControllerUpdate object
     * @param Spreadsheet current spreadsheet
     */
    public ThreadUpdate(String url, String user, String pass, String dbName, ControllerUpdate ctrlUp, Cell [][]cells)
    {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.dbName = dbName;
        this.ctrlUp = ctrlUp;
        this.cells = cells;
    }
    
    @Override
    public void run() 
    {
        try
        {
            /* connects with database */
            ctrlUp.connect(url, user, pass, dbName);
            /* launches the select table window */
            UITableSelectUpdate ts = new UITableSelectUpdate(cells, dbName, ctrlUp);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
