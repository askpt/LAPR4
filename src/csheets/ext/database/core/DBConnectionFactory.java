package csheets.ext.database.core;

/**
 * A factory of database connection adapters
 * @author João Carreira
 */
public class DBConnectionFactory 
{
    private static DBConnectionFactory uniqueInstance = null;
    
    /**
     * private constructor (as required in the singleton pattern)
     */
    private DBConnectionFactory()
    {
        
    }
    
    /**
     * getInstance
     * @return DBConnectionFactory singleton
     */
    public static DBConnectionFactory getInstance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new DBConnectionFactory();
        }
        return uniqueInstance;
    }
    
    public DBConnectionStrategy getDBTechnology(String dbName) throws Exception
    {
        //return (DBConnectionStrategy) Class.forName(adapteeName).newInstance();
        if(dbName.startsWith("HSQL"))
        {
            return new HsqlDBConnection();
        }
        else
        {
            return null;
        }
    }
}
