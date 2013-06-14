package csheets.ext.database.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import javax.swing.JOptionPane;

import csheets.core.Cell;

/**
 * Creates a new connection to a derby database
 * 
 * @author Andre
 * 
 */
public class DerbyConnection implements DBConnectionStrategy {

	/** driver path */
	private final String driverPath = "org.apache.derby.jdbc.EmbeddedDriver";
	/** connection method */
	private Connection connection;

	@Override
	public void createConnection(String url, String user, String pass)
			throws ClassNotFoundException, SQLException {
		try {
			Class.forName(driverPath);
			connection = DriverManager.getConnection(url + ";create=true",
					user, pass);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: class not found!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error: connection to database!");
			e.printStackTrace();
		}
	}

	@Override
	public void createTable(String tableName, Cell[][] cells) {
		/* defining row and column number */
		int numberOfColumns = cells[0].length;
		int numberOfRows = cells.length;

		/* beginning the construction of the sql statement */
		String stat = "CREATE TABLE " + tableName + "(linha INTEGER, ";

		/* the first line of the exported cells is the name of each column */
		String[] columnsName = new String[cells[0].length];
		String[] columnsNameCopy = new String[cells[0].length];

		/* cycle to build the columns name string */
		for (int i = 0; i < cells[0].length; i++) {
			/* if it's not the last column */
			if (i != ((cells[0].length) - 1)) {
				columnsName[i] = cells[0][i].getContent() + " VARCHAR(200), ";
				columnsNameCopy[i] = cells[0][i].getContent();
			}
			/* if it's the last column must close with bracket */
			else {
				columnsName[i] = cells[0][i].getContent() + " VARCHAR(200))";
				columnsNameCopy[i] = cells[0][i].getContent();
			}
		}

		/*
		 * in the end of this cycle we should have the final sql statement for
		 * table creation
		 */
		for (int i = 0; i < columnsName.length; i++) {
			stat += columnsName[i];
		}

		/* sql statement */
		Statement st = null;

		/* creates the table based on the sql statement */
		try {
			st = connection.createStatement();
			int i = st.executeUpdate(stat);
			/* keep this until observer is implemented */
			JOptionPane.showMessageDialog(null, "Data succesfully exported!");
		} catch (SQLException ex) {
			Logger.getLogger(HsqlDBConnection.class.getName()).log(
					Level.SEVERE, null, ex);
			/* keep this until observer is implemented */
			JOptionPane.showMessageDialog(null, "Error: table already exists");
		}

		/* now beggins the "insert into" sql statement */
		String insertStat = "insert into " + tableName + "(linha,";
		for (int i = 0; i < columnsNameCopy.length; i++) {
			if (i != (columnsNameCopy.length - 1)) {
				insertStat += columnsNameCopy[i] + ",";
			} else {
				insertStat += columnsNameCopy[i] + ")";
			}
		}
		/* continuing concatenation */
		insertStat += " VALUES(";

		/* creating a number of insert statements equal to number of rows -1 */
		String[] insertVector = new String[numberOfRows - 1];
		for (int i = 0; i < insertVector.length; i++) {
			String temp = Integer
					.toString(cells[i][0].getAddress().getRow() + 1);
			insertVector[i] = insertStat + temp + ",";
		}

		/* concatenating the respecting insert statements */
		for (int i = 1; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				if (j != (numberOfColumns - 1)) {
					insertVector[i - 1] += "'" + cells[i][j].getContent()
							+ "',";
				} else {
					insertVector[i - 1] += "'" + cells[i][j].getContent()
							+ "')";
				}
			}
		}

		/* inserting values into the table */
		for (int i = 0; i < insertVector.length; i++) {
			Statement insertSt = null;
			try {
				insertSt = connection.createStatement();
				int j = st.executeUpdate(insertVector[i].toString());
			} catch (SQLException ex) {
				Logger.getLogger(HsqlDBConnection.class.getName()).log(
						Level.SEVERE, null, ex);
				/* keep this until observer is implemented */
				JOptionPane.showMessageDialog(null, "Error: data not inserted");
			}
		}
	}

	@Override
	public void disconnet() {
		try {
			connection.close();
		} catch (SQLException ex) {
			Logger.getLogger(HsqlDBConnection.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	@Override
	public ArrayList queryToArray() 
        {
            ArrayList temp = new ArrayList();
            try
            {
                Statement st = null;
                st = connection.createStatement();
                ResultSet results = st.executeQuery("SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE='T'");
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                Object obj = null;
                for (; results.next();) 
                {
                    for (int i = 0; i < numberCols; i++) 
                    {
                        obj = results.getObject(i + 1);
                        temp.add(obj);
                    }
                }
                results.close();
                st.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
            return temp;
	}

	@Override
	public String[] getTableList(ArrayList list) 
        {
		int size = list.size();
		String[] temp = new String[size];
		for (int i = 0; i < size; i++) {
			temp[i] = list.get(i).toString();
		}
		return temp;
	}

	@Override
	public String[][] getTableContent(String tableName) 
        {
            ArrayList temp = new ArrayList();
            int []rowsAndCols = new int[2];
                  
            try 
            {            
                temp = queryToArray(tableName);
                rowsAndCols = countsRowsAndCols(tableName);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(HsqlDBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            return queryTo2dArray(temp, new String[rowsAndCols[0]][rowsAndCols[1]]);
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ArrayList queryToArray(String tableName) throws SQLException 
        {
            Statement st = null;
            ResultSet rs = null;
            ArrayList temp = new ArrayList();
            Object obj = null;
        
            String expression = "SELECT * FROM " + tableName ;
        
            st = connection.createStatement();         
            rs = st.executeQuery(expression);   
       
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rows = countRows(tableName);
        
            for(int i = 1; i <= cols; i++)
            {
                obj = meta.getColumnName(i);
                temp.add(obj);
            }
        
            for(; rs.next(); )
            {
                for(int i = 0; i < cols; i ++)
                {
                    obj = rs.getObject(i + 1);
                    temp.add(obj);
                }
            }
            st.close();
            return temp;            
	}

	@Override
	public int[] countsRowsAndCols(String tableName) throws SQLException 
        {
            int []result = new int[2];
            int rows, cols;
            Statement st = null;
            ResultSet rs = null;
            Object obj = null;
            String sqlExpression = "SELECT * FROM " + tableName;
       
            st = connection.createStatement();
            rs = st.executeQuery(sqlExpression);
            ResultSetMetaData meta = rs.getMetaData();
            rs.next();
        
            obj = rs.getObject(1);
            result[0] = countRows(tableName);
            result[1] = meta.getColumnCount();
            
            st.close();
                  
            return result;
	}

	@Override
	public int countRows(String tableName) throws SQLException 
        {
            Statement st = null;
            ResultSet rs = null;
            Object obj = null;
            String sqlExpression = "SELECT COUNT(*) FROM " + tableName;
       
            st = connection.createStatement();
            rs = st.executeQuery(sqlExpression);
        
            ResultSetMetaData meta = rs.getMetaData();
            rs.next();
        
            obj = rs.getObject(1);
        
            st.close();
        
            return Integer.parseInt(obj.toString()) + 1;
	}

    @Override
    public String[][] queryTo2dArray(ArrayList array, String[][] table) 
    {
        for(int i = 0, k = 0; i < table.length; i++)
        {
            for(int j = 0; j < table[0].length; j++, k++)
            {
                table[i][j] = array.get(k).toString();
            }          
        }
        return table;
    }

}
