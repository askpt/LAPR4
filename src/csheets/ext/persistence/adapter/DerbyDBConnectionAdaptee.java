package csheets.ext.persistence.adapter;

import csheets.core.Cell;

/**
 * Connection with the Derby database
 * (using the adapter pattern)
 * @author João Carreira
 */
@Deprecated
public class DerbyDBConnectionAdaptee implements DBConnectionAdapter
{

    @Override
    public void createConnection() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable(String tableName, Cell[][] cells, int[][] pk) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getTableList() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getTableContent() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTable(String tableName, Cell[][] cells, int[][] pk) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
