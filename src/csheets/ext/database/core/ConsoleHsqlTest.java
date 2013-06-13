package csheets.ext.database.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A console-based application to test the HSQL database
 * What this application does:
 * 
 * 1) it creates an hsql database in the path "res/csheets/ext/database/hsql_db"
 * 
 * 2) it creates a table called SAMPLE_TABLE with four entries
 * 
 * 3) in order to view the data entered go to http://www.hsqldb.org and download
 * the latest stable release (mine is 2.2.9)
 * 
 * 4) once you have it go to terminal, move to "hsqldb-2.2.9/hsqldb/lib" and 
 * run the following command:
 * $ java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
 * 
 * 5) once launched, select the following:
 * TYPE: HSQL database engine standalone
 * DRIVER: org.hsqldb.jdbcDriver
 * URL: jdbc:hsqldb:file:src-resources/csheets/ext/database/embebbed_db/hsql_db
 * USER: user
 * PASS: pass
 * 
 * 6) You should be prompted with the SAMPLE_TABLE that was created when this
 * program was run
 * 
 * (based in the code of Karl Meissner, at http://www.hsqldb.org/doc/guide/apb.html)
 * @author João Carreira
 */
public class ConsoleHsqlTest
{
    /* connection to the DB that persists for the entire life of the program */
    Connection conn;                                               

    public ConsoleHsqlTest(String db_file_name_prefix) throws Exception 
    {   
        /* Load the HSQL Database Engine JDBC driver */ 
        /* the hsqldb.jar should be in the class path or made part of the current jar */
        Class.forName("org.hsqldb.jdbcDriver");

        /* connects to the database.   
         * This will load the db files and start the
         * database if it is not alread running.
         * db_file_name_prefix is used to open or create files that hold the state
         * of the db. It can contain directory names relative to the
         * current working directory */
        conn = DriverManager.getConnection("jdbc:hsqldb:" 
                                            + db_file_name_prefix,    // filenames
                                            "user",                     // username
                                            "pass");                      // password
    }

    /**
     * safe shutdown of HSQL database
     * @throws SQLException 
     */
    public void shutdown() throws SQLException 
    {
        Statement st = conn.createStatement();

        /* db writes out to files and performs clean shuts down
         * otherwise there will be an unclean shutdown
         * when program ends */
        st.execute("SHUTDOWN");
        conn.close();    // if there are no other open connection
    }

    /**
     * select statement
     * @param expression
     * @throws SQLException 
     */
    public synchronized void query(String expression) throws SQLException 
    {
        Statement st = null;
        ResultSet rs = null;
        
        /* statement objects can be reused with */
        st = conn.createStatement();         

        /* repeated calls to execute but we
         * hoose to make a new one each time
         */
        rs = st.executeQuery(expression);   

        /* do something with the result set. */
        dump(rs);
        st.close();    
        
        /* NOTE!! if you close a statement the associated ResultSet is
         * closed too so you should copy the contents to some other object.
         * the result set is invalidated also  if you recycle an Statement
         * and try to execute some other query before the result set has been
         * completely examined.
         */
    }

    
    /**
     * SQL commands CREATE, DROP, INSERT and UPDATE
     * @param expression
     * @throws SQLException 
     */
    public synchronized void update(String expression) throws SQLException 
    {
        Statement st = null;
        /* statements */
        st = conn.createStatement();    
        /* running the query */
        int i = st.executeUpdate(expression);    

        if (i == -1) 
        {
            System.out.println("Database error: " + expression);
        }
        st.close();
    }  

    /**
     * using cursors
     * @param rs
     * @throws SQLException 
     */
    public static void dump(ResultSet rs) throws SQLException 
    {
        /* the order of the rows in a cursor are implementation dependent 
         * unless you use the SQL ORDER statement */
        ResultSetMetaData meta   = rs.getMetaData();
        int               colmax = meta.getColumnCount();
        int               i;
        Object            o = null;

        /* the result set is a cursor into the data.  You can only
         * point to one row at a time assume we are pointing to BEFORE 
         * the first row. rs.next() points to next row and returns true
         * or false if there is no next row, which breaks the loop
         */
        for (; rs.next(); ) 
        {
            for (i = 0; i < colmax; ++i) 
            {
                o = rs.getObject(i + 1);    
                System.out.print(o.toString() + " ");
            }
            System.out.println(" ");
        }
    }
    
    /**
     * saves a query in an ArrayList
     * @param expression sql expression
     * @return arraylist with indidivual items
     * @throws SQLException 
     */
    public synchronized ArrayList queryToArray(String tableName) throws SQLException 
    {
        Statement st = null;
        ResultSet rs = null;
        ArrayList temp = new ArrayList();
        Object obj = null;
        
        String expression = "SELECT * FROM " + tableName + ";";
        
        st = conn.createStatement();         
        rs = st.executeQuery(expression);   
        st.close();    
        
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rows = countRows(tableName);
        
        for(; rs.next(); )
        {
            for(int i = 0; i < cols; i ++)
            {
                obj = rs.getObject(i + 1);
                temp.add(obj);
            }
        }
        
//        String [][]result = new String[rows][cols];
//        
//        for(int i = 0, k = 0; i < rows; i++)
//        {
//            for(int j = 0; j < cols; j++, k++)
//            {
//                result[i][j] = temp.get(k).toString();
//            }
//        }
        
        return temp;
    }
    
    /**
     * counts rows and columns of a give database table
     * @param tableName table name
     * @return 2D array with number of rows (index 0) and columns (index 1)
     * @throws SQLException 
     */
    public synchronized int[] countsRowsAndCols(String tableName) throws SQLException
    {
        int []result = new int[2];
        int rows, cols;
        Statement st = null;
        ResultSet rs = null;
        Object obj = null;
        String sqlExpression = "SELECT * FROM " + tableName + ";";
       
        st = conn.createStatement();
        rs = st.executeQuery(sqlExpression);
        st.close();
        ResultSetMetaData meta = rs.getMetaData();
        rs.next();
        
        obj = rs.getObject(1);
        result[0] = countRows(tableName);
        result[1] = meta.getColumnCount();
        
        return result;
    }
    

    /**
     * counts the rows of a given table
     * @param tableName
     * @return
     * @throws SQLException 
     */
    public synchronized int countRows(String tableName) throws SQLException
    {
        Statement st = null;
        ResultSet rs = null;
        Object obj = null;
        String sqlExpression = "SELECT COUNT(*) FROM " + tableName + ";";
       
        st = conn.createStatement();
        rs = st.executeQuery(sqlExpression);
        st.close();
        
        ResultSetMetaData meta = rs.getMetaData();
        rs.next();
        
        obj = rs.getObject(1);
        
        return Integer.parseInt(obj.toString());
    }
    
    
    /**
     * Console test application
     * @param args 
     */
    public static void main(String[] args) 
    {

        ConsoleHsqlTest db = null;

        try 
        {
            db = new ConsoleHsqlTest("src-resources/csheets/ext/database/embebbed_db/hsql_db");
        } 
        catch (Exception ex1) 
        {
            ex1.printStackTrace();   
            return;                  
        }
        try 
        {
            /* make an empty table by declaring the id column IDENTITY, 
             * the db will automatically generate unique values for new rows
             * this is useful for row keys 
             */
            db.update("CREATE TABLE sample_table ( id INTEGER IDENTITY, str_col "
                    + "VARCHAR(256), num_col INTEGER)");
        } 
        catch (SQLException ex2) 
        {

           /* second time we run program should throw execption since table
            * already there this will have no effect on the db */
        }
        
        try 
        {
            /* make an empty table by declaring the id column IDENTITY, 
             * the db will automatically generate unique values for new rows
             * this is useful for row keys 
             */
            db.update("CREATE TABLE TABELA2 ( id INTEGER IDENTITY, str_col "
                    + "VARCHAR(256), num_col INTEGER)");
        } 
        catch (SQLException ex2) 
        {

           /* second time we run program should throw execption since table
            * already there this will have no effect on the db */
        }
        
        
        
        
        
        try 
        {
            /* adding some rows - will create duplicates if run more then once
             * the id column is automatically generated */
            db.update(
                "INSERT INTO sample_table(str_col,num_col) VALUES('Ford', 100)");
            db.update(
                "INSERT INTO sample_table(str_col,num_col) VALUES('Toyota', 200)");
//            db.update(
//                "INSERT INTO sample_table(str_col,num_col) VALUES('Honda', 300)");
//            db.update(
//                "INSERT INTO sample_table(str_col,num_col) VALUES('GM', 400)");
//            db.update(
//                "INSERT INTO sample_table(str_col,num_col) VALUES('Ferrari', 600)");

            /* doing a query */
            db.query("SELECT * FROM sample_table WHERE num_col < 250");
            
            /* query about all database tables */
//            System.out.println("***** ALL TABLES *****");
//            db.query("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.SYSTEM_TABLES where TABLE_TYPE='TABLE'");
            
            /* saving queries to arraylist */
            ArrayList temp = new ArrayList();
//            temp = db.queryToArray("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.SYSTEM_TABLES where TABLE_TYPE='TABLE'");
//            for(int i = 0; i < temp.size(); i++)
//            {
//                System.out.println("TABELA nº " + (i + 1) + temp.get(i).toString());
//            }
            
            
           ArrayList temp2 = new ArrayList();
            temp2 = db.queryToArray("sample_table");
            
//            for(int i = 0; i < temp2.size(); i++)
//            {
//                System.out.println(temp2.get(i).toString());
//            }
            
            
//            System.out.println("numero de linhas");
//            ArrayList temp3 = new ArrayList();
//            temp2 = db.queryToArrayList("SELECT COUNT(*) FROM sample_table");
//            
           // System.out.println("Número de linhas = " + db.countRows("sample_table"));
            
            int teste[] = new int[2];
           
            teste = db.countsRowsAndCols("sample_table");
             System.out.println("linha de teste");
              System.out.println("rows = " + teste[0]);
              System.out.println("cols = " + teste[1]);
            
            
            /* shutting down db */
            db.shutdown();
        } 
        catch (SQLException ex3) 
        {
            ex3.printStackTrace();
        }
    }    
}    

