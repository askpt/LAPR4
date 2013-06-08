package csheets.ext.database.core;

import csheets.core.Cell;

/**
 * Creates a connection to a HSQL database
 * @author João Carreira
 */
public class HsqlDBConnectionAdaptee implements DBConnectionAdapter
{

    @Override
    public void createConnection(String url, String user, String pass) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable(String tableName, Cell[][] cells, int[][] pk) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getTableList() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getTableContent()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTable() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
