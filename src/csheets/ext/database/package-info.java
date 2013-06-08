/** <p>
 * <b>Use case diagram (version A)</b>
 * </p>
 * <img src="doc-files/use_case_persistence_v1.png">
 *
 * <p>
 * <b>System system diagram Export to Database</b>
 * </p>
 * <img src="doc-files/system_diagram_exportDB.png">
 *
 * <p>
 * <b>System system diagram Update to Database</b>
 * </p>
 * <img src="doc-files/system_diagram_updateDB.png">
 *
 * <p>
 * <b>Class Diagram (persistence) </b>
 * </p>
 * <img src="doc-files/class_diagram_persistance.png">
 *
 * <p>
 * <b>Use Case Realization - Database Export </b>
 * </p>
 * <img src="doc-files/use_case_realization_DBexport.png">
 *
 * <p>
 * <b>Use Case Realization - Database Import </b>
 * </p>
 * <img src="doc-files/use_case_realization_DBimport.png">
 *
 * <p>
 * <b>Use Case Realization - Database Update </b>
 * </p>
 * <img src="doc-files/use_case_realization_DBupdate.png">
 */
/*
@startuml use_case_persistence_v1.png
left to right direction
User --> (exports selected sheet \ncontent to database)
User --> (updates selected \nsheet content in database)
User --> (imports data from database)
@enduml 

@startuml Diagrams/system_diagram_exportDB.png
actor User
participant System
User -> System: selects a range of cells
User -> System: selects export option
System -> System: checks available databases
System -> User: shows available databases
User -> System: selects a database
User -> System: inputs access credentials
System -> System: saves data into database
System -> User: shows 'successful export'
@enduml

@startuml Diagrams/system_diagram_updateDB.png
actor User
participant System
User -> System: selects a range of cells
User -> System: selects export option
System -> System: checks available databases
System -> User: shows available databases
User -> System: selects a database
User -> System: inputs access credentials
System -> System: updates data into database
System -> User: shows 'successful export'
@enduml

@startuml Diagrams/class_diagram_persistance.png
interface DBConnectionAdapter
DBConnectionAdapter <|-- HsqlDBConnectAdaptee
DBConnectionAdapter <|-- DerbyDBConnectAdaptee
DBConnectionAdapterFactory -- ControllerExport
DBConnectionAdapterFactory -- ControllerImport
ControllerExport -- DatabaseFacade
ControllerImport -- DatabaseFacade
DBConnectionAdapterFactory -- DBConnectionAdapter
DBCsvReader -- ControllerExport
Database -- ControllerExport
DBCsvReader -- ControllerImport
Database -- ControllerImport
ControllerExport: addObserver(Observer this)
ControllerExport: String urlConnect
ControllerExport: getDBList()
ControllerExport: getCredentials(String url, String user, String pass)
ControllerExport: setDataToExport(String tableName, Cell [][]cells, int [][]pk)
ControllerExport: getTableList()
ControllerExport: setTableToUpdate()
ControllerExport: startUpdate()
ControllerExport: alertObservers()
ControllerImport: addObserver(Observer this)
ControllerImport: String urlConnect
ControllerImport: getDBList()
ControllerImport: getCredentials(String url, String user, String pass)
ControllerImport: getTableList()
ControllerImport: loadTable(String tableName)
ControllerImport: startImport()
ControllerImport: getTable()
ControllerImport: showData()
ControllerImport: alertObservers()
DatabaseFacade: createConnection(String url, String user, String pass)
DatabaseFacade: setDataToExport(String tableName, Cell [][]cells, int[][]pk)
DatabaseFacade: exportData()
DatabaseFacade: getTableList()
DatabaseFacade: loadTable(String tableName)
DatabaseFacade: getTableContent()
DatabaseFacade: setTableToUpdate()
DatabaseFacade: update()
DBConnectionAdapterFactory: getInstance()
DBConnectionAdapterFactory: getDBTechnology(String urlConnect)
DBConnectionAdapter: createConnection(String url, String user, String pass)
DBConnectionAdapter: createTable(String tableName, Cell [][]cells, int [][]pk)
DBConnectionAdapter: getTableList()
DBConnectionAdapter: getTableContent()
DBConnectionAdapter: updateTable()
DBCsvReader: getDBList()
Database: getUrlConnection()
@enduml

@startuml Diagrams/use_case_realization_DBexport.png
UIExport -> ControllerExport: <<create(Observer this)>>
ControllerExport -> ControllerExport: addObserver(Observer this)
ControllerExport -> DatabaseFacade: <<create>>
UIExport -> ControllerExport: getDBList()
ControllerExport -> DBCsvReader: <<create>>
ControllerExport -> DBCsvReader: getDBlist()
UIExport -> ControllerExport: getCredentials(String url, String user, String pass)
ControllerExport -> Database: urlConnect = getUrlConnection()
ControllerExport -> DatabaseFacade: urlConnect = createConnection(String url, String user, String pass)
DatabaseFacade -> DBConnectionAdapterFactory: getInstance()
DatabaseFacade -> DBConnectionAdapterFactory: getDBTechnology(String urlConnect)
DatabaseFacade -> DBConnectionAdapter: createConnection(String url, String user, String pass)
UIExport -> ControllerExport: setDataToExport(String tableName, Cell [][]cells, int [][]pk)
ControllerExport -> DatabaseFacade: setDataToExport(String tableName, Cell [][]cells, int [][]pk)
UIExport -> ControllerExport: startExport()
note left of ControllerExport
 thread launched 
 at this point
end note
ControllerExport -> DatabaseFacade: exportData()
DatabaseFacade -> DBConnectionAdapter: createTable(String tableName, Cell [][]cells, int [][]pk)
@enduml

@startuml Diagrams/use_case_realization_DBimport.png
UIImport -> ControllerImport:  <<create(Observer this)>>
ControllerImport -> ControllerImport: addObserver(Observer this)
ControllerImport -> DatabaseFacade: <<create>>
UIImport -> ControllerImport: getDBList()
ControllerImport -> DatabaseFacade: getDBList()
UIImport -> ControllerImport: setCredentials(String url, String user, String pass)
ControllerImport -> DatabaseFacade: urlConnect = getUrlConnection()
ControllerImport -> DatabaseFacade: createConnection(String url, String user, String pass)
DatabaseFacade -> DBConnectionAdapterFactory: getInstance()
DatabaseFacade -> DBConnectionAdapterFactory: getDBTechnology()
DatabaseFacade -> DBConnectionAdapter: createConnection(String url, String user, String pass)
UIImport -> ControllerImport: getTableList()
ControllerImport -> DatabaseFacade: getTableList()
DatabaseFacade -> DBConnectionAdapter: getTableList()
UIImport -> ControllerImport: loadTable(String tableName)
ControllerImport -> DatabaseFacade: loadTable(String tableName)
UIImport -> ControllerImport: startImport()
ControllerImport -> DatabaseFacade: getTableContent()
DatabaseFacade -> DBConnectionAdapter: getTableContent(String tableName)
UIImport -> ControllerImport: getTable()
note left of ControllerImport
 thread launched 
 at this point
end note
UIImport -> UIImport: showData()
@enduml

@startuml Diagrams/use_case_realization_DBupdate.png
UIExport -> ControllerExport: <<create(Observer this)>>
ControllerExport -> ControllerExport: addObserver(Observer this)
ControllerExport -> DatabaseFacade: <<create>>
UIExport -> ControllerExport: getDBList()
ControllerExport -> DatabaseFacade: getDBList()
UIExport -> ControllerExport: getCredentials(String url, String user, String pass)
ControllerExport -> DatabaseFacade: urlConnect = getUrlConnection()
ControllerExport -> DatabaseFacade: urlConnect = createConnection(String url, String user, String pass)
DatabaseFacade -> DBConnectionAdapterFactory: getInstance()
DatabaseFacade -> DBConnectionAdapterFactory: getDBTechnology(String urlConnect)
DatabaseFacade -> DBConnectionAdapter: createConnection(String url, String user, String pass)
UIExport -> ControllerExport: getTableList()
ControllerExport -> DatabaseFacade: getTableList()
DatabaseFacade -> DBConnectionAdapter: getTableList()
UIExport -> UIExport: targetTable = selectTable()
UIExport -> ControllerExport: setDataToUpdate(String targetTable, Cell [][]cells, int [][]pk)
ControllerExport -> DatabaseFacade: setDataToUpdate(String targetTable, Cell [][]cells, int [][]pk)
UIExport -> ControllerExport: starUpdate()
note left of ControllerExport
 thread launched 
 at this point
end note
ControllerExport -> DatabaseFacade: update()
DatabaseFacade -> DBConnectionAdapter: updateTable(String targetTable, Cell [][]cells, int [][]pk)
ControllerExport -> ControllerExport: alertObservers()
@enduml
*/

/**
 * @author João Carreira
 *
 */
package csheets.ext.database;