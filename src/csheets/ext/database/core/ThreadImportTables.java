package csheets.ext.database.core;

import csheets.core.Cell;
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
    
    /**
     * construtor
     * @param cell cell used as reference for import
     * @param url path to the database
     * @param user username
     * @param pass password
     * @param tableName table in the database
     * @param dbName database name
     * @param ctrlImp ControllerImport object
     */
    public ThreadImportTables(String url, String user, String pass, String dbName, ControllerImport ctrlImp)
    {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.dbName = dbName;
        this.ctrlImp = ctrlImp;
    }
    
    @Override
    public void run() 
    {
        try
        {
            /* connects with database */
            ctrlImp.connect(url, user, pass, dbName);
            /* launches the select table window */
            UITableSelect ts = new UITableSelect(dbName, ctrlImp);
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
