package csheets.ext.database.core;

/**
 * A factory of database connection adapters
 * @author João Carreira
 */
public class DBConnectionAdapterFactory 
{
    private static DBConnectionAdapterFactory uniqueInstance = null;
    
    /**
     * private constructor (as required in the singleton pattern)
     */
    private DBConnectionAdapterFactory()
    {
        
    }
    
    /**
     * getInstance
     * @return DBConnectionAdapterFactory singleton
     */
    public static DBConnectionAdapterFactory getInstance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new DBConnectionAdapterFactory();
        }
        return uniqueInstance;
    }
    
    public DBConnectionAdapter getDBTechnology(String dbName) throws Exception
    {
        //return (DBConnectionAdapter) Class.forName(adapteeName).newInstance();
        if(dbName.startsWith("HSQL"))
        {
            return new HsqlDBConnectionAdaptee();
        }
        else
        {
            /* teste line */
            System.out.println("Mega fail");
            return null;
        }
    }
}
