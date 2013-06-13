package csheets.ext.database.core;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.database.controller.ControllerImport;
import csheets.ext.database.ui.UITableSelect;

/**
 * The thread responsible for the import of table information from a database
 * @author João Carreira
 */
public class ThreadImportTables implements Runnable
{
    private String url, user, pass, dbName;
    private ControllerImport ctrlImp;
    private Spreadsheet spreadSheet;
    
    /**
     * construtor
     * @param url path to the database
     * @param user username
     * @param pass password
     * @param tableName table in the database
     * @param dbName database name
     * @param ctrlImp ControllerImport object
     * @param Spreadsheet current spreadsheet
     */
    public ThreadImportTables(String url, String user, String pass, String dbName, ControllerImport ctrlImp, Spreadsheet spreadSheet)
    {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.dbName = dbName;
        this.ctrlImp = ctrlImp;
        this.spreadSheet = spreadSheet;
    }
    
    @Override
    public void run() 
    {
        try
        {
            /* connects with database */
            ctrlImp.connect(url, user, pass, dbName);
            /* launches the select table window */
            UITableSelect ts = new UITableSelect(spreadSheet, dbName, ctrlImp);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * gets the controller import
     * @return ControllerImport object
     */
    public ControllerImport getControllerImport()
    {
        return ctrlImp;
    }
}
