/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.database.core;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerExport;

/**
 * The thread responsible for the export of data to a database
 * @author João Carreira
 */
public class ThreadExport implements Runnable
{
    private Cell[][] cells;
    private String url, user, pass, tableName, dbName;
    private ControllerExport ctrlExp;

    public ThreadExport(Cell [][]cells, String url, String user, String pass, String tableName)
    {
        this.cells = cells;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.tableName = tableName;
    }
    
    public ThreadExport(Cell [][]cells, String url, String user, String pass, String tableName, String dbName, ControllerExport ctrlExp)
    {
        this.cells = cells;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.tableName = tableName;
        this.dbName = dbName;
        this.ctrlExp = ctrlExp;
    }
    
    @Override
    public void run() 
    {
        try
        {
            ctrlExp.connect(url, user, pass, dbName);
            ctrlExp.setDataToExport(cells, user, pass, tableName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
