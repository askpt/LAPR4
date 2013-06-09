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
    private String url, user, pass, tableName;
    private ControllerExport ctrlExp;

    public ThreadExport(Cell [][]cells, String url, String user, String pass, String tableName)
    {
        this.cells = cells;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.tableName = tableName;
    }
    
    @Override
    public void run() 
    {
        ctrlExp = new ControllerExport();
        try
        {
            ctrlExp.connect(url, user, pass, tableName);
            ctrlExp.setDataToExport(cells, user, pass, tableName);
        }
        catch(Exception e)
        {
            
        }
    }
    
}
