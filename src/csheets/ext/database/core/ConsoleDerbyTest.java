package csheets.ext.database.core;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;


public class ConsoleDerbyTest
{
    private static final String driverPath = "org.apache.derby.jdbc.EmbeddedDriver";
    private static Connection connection;
    private static String url = "jdbc:derby:src-resources/csheets/ext/database/embebbed_db/derby_db";
    
    private static String tableName = "championships";
    private static String insertSt = "insert into " + tableName + " VALUES('1', 'SLB', '32')";
    private static String insertSt2 = "insert into " + tableName + " VALUES('2', 'FCP', '27')";
    
    private static ArrayList allTables = new ArrayList();;

    public static void main(String[] args) throws SQLException
    {
        createConnection();
//        createTable(tableName);
//        insertRestaurants(insertSt);
//        insertRestaurants(insertSt2);
//        selectRestaurants();
//        showAllTables();
        allTables = saveAllTableNamesToArrayList();
        printArrayList(allTables);
        shutdown();
    }
    
    private static void createConnection()
    {
        try 
        {
            Class.forName(driverPath);
            connection = DriverManager.getConnection(url + ";create=true","user","pass");
            System.out.println("CONNECTED");
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("Error: class not found!");
	} 
        catch (SQLException e) 
        {
            System.out.println("Error: connection to database!");
            e.printStackTrace();
	}
    }
    
    
    private static void createTable(String tableName) throws SQLException
    {
        String stat = "CREATE TABLE " + tableName + "(id VARCHAR(50), CLUB VARCHAR(50), TITLES VARCHAR(50))";
        Statement st = null;
        st = connection.createStatement();
        int i = st.executeUpdate(stat);
        System.out.println("TABLE CREATED: " + tableName);
    }
    
    private static void insertRestaurants(String insertSt)
    {
        System.out.println(insertSt);
        try
        {
            Statement st = null;
            st = connection.createStatement();
            st.executeUpdate(insertSt);
            st.close();
            System.out.println("DATA INSERTED");
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    private static void selectRestaurants()
    {
        try
        {
            Statement st = null;
            st = connection.createStatement();
            ResultSet results = st.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            st.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    private static void showAllTables()
    {
        try
        {
            Statement st = null;
            st = connection.createStatement();
            ResultSet results = st.executeQuery("SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE='T'");
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                String tableName = results.getString(1);
                System.out.println(tableName);
            }
            results.close();
            st.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    private static ArrayList saveAllTableNamesToArrayList()
    {
        ArrayList temp = new ArrayList();
        try
        {
            Statement st = null;
            st = connection.createStatement();
            ResultSet results = st.executeQuery("SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE='T'");
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            
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
    
    private static void printArrayList(ArrayList a)
    {
        for(int i = 0; i < a.size(); i++)
        {
            System.out.println(a.get(i).toString());
        }
    }
    
    private static void shutdown() throws SQLException
    {
         Statement st = null;
         st = connection.createStatement();
        try
        {
            if (st != null)
            {
                st.close();
            }
            if (st != null)
            {
                DriverManager.getConnection(url + ";shutdown=true");
                connection.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
}
