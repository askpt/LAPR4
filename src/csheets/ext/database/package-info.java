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
 * <b>System sequence diagram Update to Database</b>
 * </p>
 * <img src="doc-files/system_diagram_updateDB.png">
 *
 * <p>
 * <b>System sequence diagram - Synchronize between application and database</b>
 * </p>
 * <img src="doc-files/system_diagram_syncronize.png">
 * 
 * <p>
 * <b>Sequence diagram - Synchronize between application and database</b>
 * </p>
 * <img src="doc-files/sequence_diagram_sync.png">
 * 
 * 
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
 @startuml doc-files/use_case_persistence_v1.png
 left to right direction
 User --> (exports selected sheet \ncontent to database)
 User --> (updates selected \nsheet content in database)
 User --> (imports data from database)
 User --> (Syncronize between database and application) 
 (Merge conflict decision by user) ..> (Syncronize between database and application) : <<extends>>
 @enduml 

 @startuml doc-files/system_diagram_syncronize.png
 actor User as us
 participant System as sys

 us -> sys : choose cells
 us -> sys : choose database
 us -> sys : start sync
 loop timer 30sec
 alt if merge conflict occurs
 sys -> us : choose application or database content
 us -> sys : selected application or database content 
 end
 sys -> us : sync status
 end 

 @enduml

 @startuml doc-files/system_diagram_exportDB.png
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

 @startuml doc-files/system_diagram_updateDB.png
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

 @startuml doc-files/class_diagram_persistance.png

 class ControllerSync {
 ~List<Database> dbList
 ~DatabaseFacade facade
 +ControllerSync()
 +String[][] getDBlist()
 +void startSync(String user, String pass, Cell[][] cells, String tableName, String url, Observer observer)
 +void connect(String url, String user, String pass, String dbName)
 }

 class ThreadSync {
 -Cell[][] cells
 -String url
 -String user
 -String pass
 -String tableName
 -String dbName
 -Observer observer
 +ThreadSync(Cell[][] cells, String url, String user, String pass, String table, String dbName, Observer observer)
 +void run()
 }
 interface Runnable {
 }
 Runnable <|.. ThreadSync

 class UISync {
 -{static}long serialVersionUID
 -String[][] dbDrivers
 -String[] driversName
 -JComboBox comboDrivers
 -ControllerSync ctrlSync
 -JButton btnOk
 -JButton btnCancel
 -JButton btnUrl
 -Cell[][] cells
 -JTextField userTxt
 -JTextField dbTxt
 -JTextField tableTxt
 -JTextField urlTxt
 -JPasswordField pwd
 ~JLabel sysMsg
 ~ThreadSync thrSync
 ~UISync sync
 -int returnValue
 +UISync(Cell[][] cells)
 +void update(Observable o, Object arg)
 }
 class JFrame {
 }
 JFrame <|-- UISync
 interface Observer {
 }
 Observer <|.. UISync




 interface DBConnectionAdapter
 DBConnectionAdapter <|-- HsqlDBConnectAdaptee
 DBConnectionAdapter <|-- DerbyDBConnectAdaptee
 DBConnectionAdapter <|-- MysqlDBConnectAdaptee
 DBConnectionAdapterFactory -- ControllerExport
 DBConnectionAdapterFactory -- ControllerImport
 ControllerExport - DatabaseFacade
 ControllerImport - DatabaseFacade
 DBConnectionAdapterFactory -- DBConnectionAdapter
 ControllerExport: addObserver(Observer this)
 ControllerExport: String urlConnect
 ControllerExport: getDBList()
 ControllerExport: connect(String url, String user, String pass, String adapteeName)
 ControllerExport: setDataToExport(String tableName, Cell [][]cells, int [][]pk)
 ControllerExport: getTableList()
 ControllerExport: setTableToUpdate()
 ControllerExport: startUpdate()
 ControllerExport: alertObservers()
 ControllerImport: String urlConnect
 ControllerImport: getDBList()
 ControllerIxport: connect(String url, String user, String pass, String adapteeName)
 ControllerImport: getTableList()
 ControllerImport: loadTable(String tableName)
 ControllerImport: startImport()
 ControllerImport: getTable()
 ControllerImport: showData()
 DatabaseFacade: getDBList()
 DatabaseFacade: createConnection(String url, String user, String pass, String adapteeName)
 DatabaseFacade: setDataToExport(String tableName, Cell [][]cells, int[][]pk)
 DatabaseFacade: exportData()
 DatabaseFacade: getTableList()
 DatabaseFacade: loadTable(String tableName)
 DatabaseFacade: getTableContent()
 DatabaseFacade: setTableToUpdate()
 DatabaseFacade: update()
 DBConnectionAdapterFactory: getInstance()
 DBConnectionAdapterFactory: getDBTechnology(String adapteeName)
 DBConnectionAdapter: createConnection(String url, String user, String pass)
 DBConnectionAdapter: createTable(String tableName, Cell [][]cells, int [][]pk)
 DBConnectionAdapter: getTableList()
 DBConnectionAdapter: getTableContent()
 DBConnectionAdapter: updateTable()
 @enduml

 @startuml Diagrams/use_case_realization_DBexport.png
 UIExport -> ControllerExport: <<create(Observer this)>>
 ControllerExport -> ControllerExport: addObserver(Observer this)
 ControllerExport -> DatabaseFacade: <<create>>
 UIExport -> ControllerExport: getDBList()
 ControllerExport -> DBCsvReader: <<create>>
 ControllerExport -> DBCsvReader: getDBlist()
 note left of ControllerExport
 thread launched 
 at this point
 end note
 UIExport -> ControllerExport: <<Thread.run>>\n\tstart()
 ControllerExport -> DatabaseFacade: createConnection(String url, String user, String pass, String adapteeName)
 DatabaseFacade -> DBConnectionFactory: getInstance()
 DatabaseFacade -> DBConnectionFactory: getDBTechnology(String adapteeName)
 DatabaseFacade -> DBConnectionStrategy: createConnection(String url, String user, String pass)
 UIExport -> ControllerExport: setDataToExport(Cell [][]cells, String user, String pass, String tableName)
 ControllerExport -> DatabaseFacade: exportData(Cell [][]cells, String tableName)
 DatabaseFacade -> DBConnectionStrategy: createTable(String tableName, Cell [][]cells)
 DatabaseFacade -> DBConnectionStrategy: disconnect()
 @enduml

 @startuml doc-files/use_case_realization_DBimport.png
 UIImport -> ControllerImport:  <<create(Observer this)>>
 ControllerImport -> ControllerImport: addObserver(Observer this)
 ControllerImport -> DatabaseFacade: <<create>>
 UIImport -> ControllerImport: getDBList()
 ControllerImport -> DatabaseFacade: getDBList()
 UIImport -> ControllerImport: <<Thread.run>>\n\tstart()
 UIImport -> ControllerImport: connect(String url, String user, String pass, String dbName)
 ControllerImport -> DatabaseFacade: createConnection(String url, String user, String pass)
 DatabaseFacade -> DBConnectionFactory: getInstance()
 DatabaseFacade -> DBConnectionFactory: getDBTechnology()
 DatabaseFacade -> DBConnectionStrategy: createConnection(String url, String user, String pass)
 UIImport -> UITableSelect: <<create>>
 UITableSelect -> ControllerImport: getTableList()
 ControllerImport -> DatabaseFacade: getTableList()
 DatabaseFacade -> DBConnectionStrategy: getTableList()
 DatabaseFacade -> DBConnectionStrategy: queryToArray() 
 UITableSelect -> ControllerImport: String [][]tableData = loadTable(String tableName)
 ControllerImport -> DatabaseFacade: loadTable(String tableName)
 DatabaseFacade -> DBConnectionStrategy: getTableContent(String tableName)
 DBConnectionStrategy -> DBConnectionStrategy: queryToArray(String tableName)
 DBConnectionStrategy -> DBConnectionStrategy: countsRowsAndCols(String tableName)
 DBConnectionStrategy -> DBConnectionStrategy: queryTo2dArray(String tableName)
 loop i = 0; i < tableData.length
 loop j = 1; j < tableData[0].length
 UITableSelect -> Spreadsheet: getCell(j - 1; i)
 UITableSelect -> Cell: setContent(tableData[i][j])
 end
 end
 @enduml

 @startuml doc-files/use_case_realization_DBupdate.png
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

 @startuml doc-files/sequence_diagram_sync.png
 participant UISync as uis
 participant ThreadSync as thr
 participant ControllerSync as ctrl
 participant DatabaseFacade as dbf

 activate uis
 create thr
 uis -> thr: create
 activate thr
 uis->thr : <<Thread.start()>>\ncreate(cells, url, user, pass, table, dbName, observer)
 create ctrl
 thr->ctrl:create
 activate ctrl

 thr->ctrl:connect(url, user, pass, dbName)
 create dbf
 ctrl -> dbf: create
 activate dbf
 ctrl -> dbf: createConnection(String url, String user, String pass)
 dbf -> DBConnectionStrategyFactory: getInstance()
 dbf -> DBConnectionStrategyFactory: getDBTechnology(urlConnect)
 dbf -> DBConnectionStrategy: createConnection(url, user, pass)

 thr->ctrl:startSync(user, pass, cells, tableName, url, observer)
 ctrl->dbf:startSync(user, pass, cells, tableName, url, observer)
 dbf->dbf:addObserver(observer)
 dbf-> DBConnectionStrategy: createTable(cells, cellsTemp)
 loop while(true)
 dbf->dbf:temporaryStructure(cells, cellsTemp)
 dbf->DBConnectionStrategy:disconnect()
 ...sleep 30 sec...
 dbf -> DBConnectionStrategy: createConnection(url, user, pass)
 loop for i=0;cellsTemp.lenght;i++
 dbf->dbf:indexServ = findLine(cellsTemp[i][0].getRow(),serverCells)
 dbf->dbf:indexApp = findLine(cellsTemp[i][0].getRow(), cells)
 dbf->dbf:checkLine(serverCells[indexServ], cells[indexApp],cellsTemp[i])
 end
 end

 @enduml


 */

/**
 * @author João Carreira
 *
 */
package csheets.ext.database;

