package csheets.ext.database.core;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerImport;

/**
 * The thread responsible for the import of data from a database
 * @author João Carreira
 */
public class ThreadImport implements Runnable
{
    private Cell cell;
    private String url, user, pass, tableName, dbName;
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
    public ThreadImport(Cell cell, String url, String user, String pass, String tableName, String dbName, ControllerImport ctrlImp)
    {
        this.cell = cell;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.tableName = tableName;
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
