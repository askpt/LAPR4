package csheets.ext.database.core;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerImport;

/**
 * The thread responsible for the import of table information from a database
 * @author João Carreira
 */
public class ThreadImportTables implements Runnable
{
    private String dbName;
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
    public ThreadImportTables(String dbName, ControllerImport ctrlImp)
    {
        this.dbName = dbName;
        this.ctrlImp = ctrlImp;
    }
    
    @Override
    public void run() 
    {
        try
        {
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
