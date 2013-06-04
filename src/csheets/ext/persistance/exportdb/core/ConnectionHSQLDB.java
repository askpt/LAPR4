package csheets.ext.persistance.exportdb.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import csheets.core.Cell;

/**
 * Representes the class ConnectionHSQLDB that will implements BasicDataBase
 * This class do the connection to hsqldb, create table, insert values and create query
 * @author 1110333 Tiago Pacheco
 */
public class ConnectionHSQLDB implements BasicDataBase{
	
	
	//atributes of class
	final private String driver="org.hsqldb.jdbcDriver";
	private Connection conn;                                               
    private String userName,password,url;
    public Statement statement;
    public ResultSet resultSet;
    
   //contructor
    public ConnectionHSQLDB(){}
    
    /**
     * This method do the connection to hsqldb
     */
    public void connect(String url, String user, String pass) throws Exception {    

    	boolean result =true;
    	try{
    		
    		Class.forName(driver);
    		conn=DriverManager.getConnection(url, user, pass);
    		JOptionPane.showMessageDialog(null,"Connection successfully!");
    	}
    	catch (ClassNotFoundException Driver)
    	{
    		JOptionPane.showMessageDialog(null,"Driver not found!");
    		result=false;
    	}
    	catch(SQLException connection  )
    	{
    		JOptionPane.showMessageDialog(null,"Connection Error!");
    		result =false;
    	}
    }
    
    /**
     * this method close the connection
     */
    public void desconnect()
    {
    	boolean result=true;
    	try{
    		conn.close();
    		JOptionPane.showMessageDialog(null,"Connection closed");
    	}
    	catch(SQLException close){
    		JOptionPane.showMessageDialog(null,"Error closing connection!");
    		result=false;
    	}
    }
    				
    /**
     * this method execute queries	
     */
    public void executeSQL(String sql)
    {
    	try{
    		statement=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    		resultSet=statement.executeQuery(sql);
    	}
    	catch(SQLException sqlexcept){
    		JOptionPane.showMessageDialog(null,"Command error");
    	}
    	
    }
    
    /**
     * this method execute updates on the hsqldb
     * @param expression
     * @throws SQLException
     */
    public synchronized void update(String expression) throws SQLException {

        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
            System.out.println("db error : " + expression);
        }

        st.close();
    }    // void update()
    
    /**
     * this method create tables and insert values of cells matrix on the table
     */
    public void insertTable(Cell[][]cells,String tableName) throws Exception
    {
    	int numColumns=cells[0].length;
    	int numLines=cells.length;
    	String stateCreate="",stateInsertValues="",stateColumn="",location;
    	for(int i=0;i<cells[0].length;i++)
    	{
    		if(i+1!=numColumns)
    			stateCreate+=cells[0][i].getContent()+" VARCHAR(200),";
    		else
    			stateCreate+=cells[0][i].getContent()+" VARCHAR(200)";
    	}
    	
    	try{
		update("CREATE TABLE "+tableName+"(id INTEGER IDENTITY, "+stateCreate+")");  
		JOptionPane.showMessageDialog(null, "Table created");
    	}catch(SQLException exeTable)
    	{
    		JOptionPane.showMessageDialog(null, "Table already exists or eror creating table! Change name");
    	}
    	for(int i=1;i<numLines;i++)
    	{
    		for(int j=0;j<numColumns;j++)
    		{
    			stateColumn=cells[0][j].getContent();
    			stateCreate=cells[i][j].getContent();
    			
    			try{
    				update("INSERT INTO "+tableName+" ("+stateColumn+") VALUES ('"+stateCreate+"')");
    			}
    	        catch (SQLException ex3) {
    	        	JOptionPane.showMessageDialog(null, "Error inserting table");
    	            ex3.printStackTrace();
    			}
    		}
    	}
 
    }
    }
        
    	
    
    	
            
    
   
   
   
