package csheets.ext.database.core;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A console-based test class
 * @author João Carreira
 */
public class Console 
{
    public static void main(String []args) throws FileNotFoundException
    {
        ArrayList<Database> array = new ArrayList<Database>();
        DBCsvReader test = new DBCsvReader();
        array = test.getDBList();
        for(int i = 0; i < array.size(); i++)
        {
//            System.out.println(array.get(i).getName());
//            System.out.println(array.get(i).getUrl());
//            System.out.println(array.get(i).getAdapteeName());
        }
        
//        System.out.println("CONSOLE: Trying to connect");
//        DatabaseFacade df = new DatabaseFacade();
//        try 
//        {
//            df.createConnection("jdbc:hsqldb:file:/Users/joaocarreira/Desktop/hsqltest", "joao", "pass", "HSQL");
//           
//           
//        } catch (Exception ex) {
//          
//        }
        
        
        HsqlDBConnection hsql = new HsqlDBConnection();
        try {
            hsql.createConnection("jdbc:hsqldb:file:/Users/joaocarreira/Desktop/hsqltest/hsql", "joao", "pass");
            //hsql.createTable(null, null, null);
        } catch (ClassNotFoundException ex) {
            System.out.println("class not found");
        } catch (SQLException ex) {
           System.out.println("sql exception");
        }
        
        
    }
}
