package csheets.ext.persistence.adapter;

import csheets.core.Cell;

/**
 * Adapter interface that allows interacting with different database
 * systems (HSQL, Derby and other)
 * (direct application of the adapter pattern)
 * @author João Carreira
 */
@Deprecated
public interface DBConnectionAdapter 
{
    public void createConnection();
    public void createTable(String tableName, Cell [][]cells, int [][]pk);
    public String[] getTableList();
    public String[][] getTableContent();
    public void updateTable(String tableName, Cell [][]cells, int [][]pk);
}
