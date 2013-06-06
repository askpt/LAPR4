/**
 * Week 1 - Adding new functionalites to formulae language
 * <p>
 * <b>Use Case Diagram</b>
 * </p>
 * <img src="Diagrams/use_case.png">
 *
 * <p>
 * <b>Sequence Diagram for existing language</b>
 * </p>
 * <img src="Diagrams/sd_current_language.png">
 *
 * <p>
 * <b>Sequence Diagram explaining the co-existence of '=' and '#' formulae languages</b>
 * </p>
 * <img src="Diagrams/sd_current_language.png">
 * 
 * <p>
 * <b>Sequence Diagram to implement the attribution operator ':='</b>
 * </p>
 * 
 * <p>
 * <img src="Diagrams/sd_cell_attribution.png">
 * <p>
 * <b>Sequence Setup Diagram for the '#'-based formulae language</b>
 *
 * </p>
 * <img src="Diagrams/seq_setup_diagram_insert_expr_number_signal.png">
 * <b>Sequence Setup Diagram for the cell-assigment formulae language</b>
 * </p>
 * 
 * <p>
 * <img src="Diagrams/seq_setup_diagram_cell_attribution.png">
 * <b>Class diagram</b>
 * </p>
 * <img src="Diagrams/class_diagram.png">
 *
 * Week 2 - Persistence
 * <p>
 * <b>Use case diagram (version A)</b>
 * </p>
 * <img src="Diagrams/use_case_persistence_v1.png">
 *
 * <p>
 * <b>Use case diagram (version B)</b>
 * </p>
 * <img src="Diagrams/use_case_persistence_v2.png">
 *
 * <p>
 * <b>System system diagram Export to Database</b>
 * </p>
 * <img src="Diagrams/system_diagram_exportDB.png">
 *
 * <p>
 * <b>System system diagram Update to Database</b>
 * </p>
 * <img src="Diagrams/system_diagram_updateDB.png">
 *
 * <p>
 * <b>Class Diagram (persistence) </b>
 * </p>
 * <img src="Diagrams/class_diagram_persistance.png">
 *
 * <p>
 * <b>Use Case Realization - Database Export </b>
 * </p>
 * <img src="Diagrams/use_case_realization_DBexport.png">
 *
 * <p>
 * <b>Use Case Realization - Database Import </b>
 * </p>
 * <img src="Diagrams/use_case_realization_DBimport.png">
 *
 * <p>
 * <b>Use Case Realization - Database Update </b>
 * </p>
 * <img src="Diagrams/use_case_realization_DBupdate.png">
 */
/*
@startuml Diagrams/use_case.png
left to right direction
User --> (inserts expression starting with "number signal") 
User --> (assigns expression to a cell)
@enduml 

@startuml Diagrams/sd_current_language.png
note left of CellEditor
 this class is used to 
 edit cells in a spreadsheet
end note
note left of CellEditor
 the method stopCellEditing
 stops editing and updates
 cell content
end note
CellEditor -> CellImpl: setContent(String content)
CellImpl -> FormulaCompiler: getInstance()
CellImpl -> FormulaCompiler: compile(this, String content)
FormulaCompiler -> ExcelExpressionCompiler: getStarter()
opt source.charAt(0) == '='
FormulaCompiler -> ExcelExpressionCompiler: compile(Cell cell, String source)
ExcelExpressionCompiler -> FormulaLexer: new(String source)
ExcelExpressionCompiler -> FormulaParser: new
ExcelExpressionCompiler -> FormulaParser: expression()
FormulaParser -> ASTPair: new
FormulaParser -> FormulaParser: match(EQ)
FormulaParser -> FormulaParser: comparison
FormulaParser -> FormulaParser: match(EOF)
ExcelExpressionCompiler -> FormulaParser: getAST()
ExcelExpressionCompiler -> ExcelExpressionCompiler: convert()
FormulaCompiler -> Formula: new(Cell cell, Expression expression)
end
CellImpl -> Formula: getReferences()
CellImpl -> CellImpl: fireContentChanged()
CellImpl -> Formula: evaluate()
Formula -> Expression: evaluate()
CellEditor -> CellEditor: fireEditingStopped()
@enduml

@startuml Diagrams/sd_numbersign_language.png
note left of CellEditor
 the addition of 
 NumberSignExpressionCompiler
 allows working with both 
 formulae languages
 at the same time
end note
CellEditor -> CellImpl: setContent(String content)
CellImpl -> FormulaCompiler: getInstance()
CellImpl -> FormulaCompiler: compile(this, String content)
FormulaCompiler -> ExcelExpressionCompiler: getStarter()
alt source.charAt(0) == '='
FormulaCompiler -> ExcelExpressionCompiler: compile(Cell cell, String source)
ExcelExpressionCompiler -> FormulaLexer: new(String source)
ExcelExpressionCompiler -> FormulaParser: new
ExcelExpressionCompiler -> FormulaParser: expression()
FormulaParser -> ASTPair: new
FormulaParser -> FormulaParser: match(EQ)
FormulaParser -> FormulaParser: comparison
FormulaParser -> FormulaParser: match(EOF)
ExcelExpressionCompiler -> FormulaParser: getAST()
ExcelExpressionCompiler -> ExcelExpressionCompiler: convert()
FormulaCompiler -> Formula: new(Cell cell, Expression expression)
else
 FormulaCompiler -> NumberSignExpressionCompiler: getStarter()
  opt source.charAt(0) == '#'
   FormulaCompiler -> NumberSignExpressionCompiler: compile(Cell cell, String source)
   NumberSignExpressionCompiler -> NumberSignFormulaLexer: new(String source)
   NumberSignExpressionCompiler -> NumberSignFormulaParser: new
   NumberSignExpressionCompiler -> NumberSignFormulaParser: expression()
   NumberSignFormulaParser -> ASTPair: new
   NumberSignFormulaParser -> NumberSignFormulaParser: match(NSIGN)
   NumberSignFormulaParser -> NumberSignFormulaParser: comparison
   NumberSignFormulaParser -> NumberSignFormulaParser: match(EOF)
   NumberSignExpressionCompiler -> NumberSignFormulaParser: getAST()
   NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert()
   FormulaCompiler -> Formula: new(Cell cell, Expression expression)
end
end
CellImpl -> Formula: getReferences()
CellImpl -> CellImpl: fireContentChanged()
CellImpl -> Formula: evaluate()
Formula -> Expression: evaluate()
CellEditor -> CellEditor: fireEditingStopped()
@enduml

@startuml Diagrams/sd_cell_attribution.png
note left of NumberSignExpressionCompiler
 to assign a value to a cell
 we describe method convert() 
end note
note left of NumberSignExpressionCompiler
 this method is recursive
 therefore it is included
 in a loop
end note 
loop recursive calls depending on the depth of the generated ANTLR AST
 NumberSignExpressionCompiler -> ASTPair: getNumberOfChildren()
 alt if numberOfChildren() == 0
  NumberSignExpressionCompiler -> ASTPair: getType()
  alt if node == NUMBER
   NumberSignExpressionCompiler -> Literal: new()
  else if node == STRING
   NumberSignExpressionCompiler -> Literal: new()
  else if node == CELL_REF
   NumberSignExpressionCompiler -> CellReference: new()
  end
   NumberSignExpressionCompiler -> Language: getInstance()
   NumberSignExpressionCompiler -> Language: getFunction()
  opt if function != NULL
   NumberSignExpressionCompiler -> ASTPair: getFirstChild()
   loop recursive calls while getFirstChild() != NULL
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, child)
   end
   NumberSignExpressionCompiler -> FunctionCall: new()
  end
  else if numberOfChildren == 1
   NumberSignExpressionCompiler -> Language: getInstance()
   NumberSignExpressionCompiler -> Language: getUnaryOperator(node.getText())
   loop recursive calls while node.firstChild() != NULL
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, node.firstChild())
   end
    NumberSignExpressionCompiler -> UnaryOperation: new()
  else if numberOfChildren == 2
   NumberSignExpressionCompiler -> Language: getInstance()
   NumberSignExpressionCompiler -> Language: getBinaryOperator(node.getText())
   alt operator is Attribution
    NumberSignExpressionCompiler -> CellReference: new()
    note left: new code to deal with ':='
    NumberSignExpressionCompiler -> CellReference: getCell()
    NumberSignExpressionCompiler -> ASTPair: getFirstChild()
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert()
    NumberSignExpressionCompiler -> Expression: value = evaluate()
    NumberSignExpressionCompiler -> UpdateCellContent: getInstance()
    NumberSignExpressionCompiler -> UpdateCellContent: triggerUpdate()
    UpdateCellContent -> Cell: setContent(value)
   else else
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, node.getFirstChild())
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, node.getNextSibling()) 
   end
 end
end
@enduml

@startuml Diagrams/seq_setup_diagram_insert_expr_number_signal.png
actor User
participant System
User -> System: selects a cell
User -> System: inserts expression starting with #
User -> System: presses 'return'
System -> System: compiles expression
System -> User: displays result in the same cell
@enduml

@startuml Diagrams/seq_setup_diagram_cell_attribution.png
actor User
participant System
User -> System: selects a cell
User -> System: inserts assignment expression
User -> System: presses 'return'
System -> System: compiles expression
System -> User: displays result in the same and destination cell
@enduml

@startuml Diagrams/class_diagram.png
CellEditor --o Cell
CellEditor: fireEditingStopped()
Cell <|-- CellImpl
Cell: setContent()
CellImpl: Formula formula
CellImpl: getFormula(): Formula
CellImpl: setContent(String content): void
CellImpl: storeContent(): void
CellImpl: fireContentChanged(): void
CellImpl --o Formula
CellImpl -- FormulaCompiler
FormulaCompiler: getInstance()
FormulaCompiler: compile(this, String content)
FormulaCompiler o-- ExpressionCompiler
ExpressionCompiler <|-- ExcelExpressionCompiler
ExpressionCompiler <|-- NumberSignExpressionCompiler
ExpressionCompiler: getStarter()
ExpressionCompiler: compile(Cell cell, String source)
NumberSignExpressionCompiler --o NumberSignFormulaLexer
NumberSignExpressionCompiler -- Language
NumberSignExpressionCompiler -- UpdateCellContent
Cell -- UpdateCellContent
NumberSignExpressionCompiler -- CellReference
NumberSignFormulaLexer --o NumberSignFormulaParser
NumberSignFormulaParser --o ASTPair
NumberSignFormulaParserTokenTypes <|-- NumberSignFormulaParser
FormulaCompiler -- Formula
Formula --|> Expression
Literal --|> Expression
Expression o-- UnaryOperation
NumberSignFormulaParser: expression()
NumberSignFormulaParser: match(NUMBER_SIGN)
NumberSignFormulaParser: comparison()
NumberSignFormulaParser: match(EOF)
NumberSignFormulaParser: getAST()
NumberSignFormulaParser --|> antlr.LLkParser
NumberSignExpressionCompiler: convert() 
Formula: getReferences()
Formula: evaluate()
Expression: evaluate()
ASTPair: getNumberOfChildren()
ASTPair: getType()
ASTPair: getFirstChild()
Language: getInstance()
Language: getFunction()
Language: getUnaryOperator()
Language: getBinaryOperator()
CellReference: getCell()
UpdateCellContent: getInstance()
UpdateCellContent: triggerUpdate()
@enduml

@startuml use_case_persistence_v1.png
left to right direction
User --> (exports selected sheet \ncontent to database)
User --> (updates selected \nsheet content in database)
User --> (imports data from database)
@enduml 

@startuml use_case_persistence_v2.png
left to right direction
User --> (exports selected sheet \ncontent to database) 
User --> (imports data from database)
(exports selected sheet \ncontent to database) .> (updates existing data) : extends
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
DBConnectionAdapter <|-- MysqlDBConnectAdaptee
DBConnectionAdapterFactory -- ControllerExport
DBConnectionAdapterFactory -- ControllerImport
ControllerExport - DatabaseFacade
ControllerImport - DatabaseFacade
DBConnectionAdapterFactory -- DBConnectionAdapter
ControllerExport: addObserver(Observer this)
ControllerExport: String urlConnect
ControllerExport: getDBList()
ControllerExport: getCredentials(String url, String user, String pass)
ControllerExport: setDataToExport(String tableName, Cell [][]cells, int [][]pk)
ControllerExport: getTableList()
ControllerExport: setTableToUpdate()
ControllerExport: startUpdate()
ControllerExport: alertObservers()
ControllerImport: String urlConnect
ControllerImport: getDBList()
ControllerImport: getCredentials(String url, String user, String pass)
ControllerImport: getTableList()
ControllerImport: loadTable(String tableName)
ControllerImport: startImport()
ControllerImport: getTable()
ControllerImport: showData()
DatabaseFacade: getDBList()
DatabaseFacade: getUrlConnection()
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
@enduml

@startuml Diagrams/use_case_realization_DBexport.png
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
DatabaseFacade -> DBConnectionAdaptee: createConnection(String url, String user, String pass)
UIExport -> ControllerExport: setDataToExport(String tableName, Cell [][]cells, int [][]pk)
ControllerExport -> DatabaseFacade: setDataToExport(String tableName, Cell [][]cells, int [][]pk)
UIExport -> ControllerExport: startExport()
note left of ControllerExport
 thread launched 
 at this point
end note
ControllerExport -> DatabaseFacade: exportData()
DatabaseFacade -> DBConnectionAdaptee: createTable(String tableName, Cell [][]cells, int [][]pk)
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
DatabaseFacade -> DBConnectionAdaptee: createConnection(String url, String user, String pass)
UIImport -> ControllerImport: getTableList()
ControllerImport -> DatabaseFacade: getTableList()
DatabaseFacade -> DBConnectionAdaptee: getTableList()
UIImport -> ControllerImport: loadTable(String tableName)
ControllerImport -> DatabaseFacade: loadTable(String tableName)
UIImport -> ControllerImport: startImport()
ControllerImport -> DatabaseFacade: getTableContent()
DatabaseFacade -> DBConnectionAdaptee: getTableContent(String tableName)
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
DatabaseFacade -> DBConnectionAdaptee: createConnection(String url, String user, String pass)
UIExport -> ControllerExport: getTableList()
ControllerExport -> DatabaseFacade: getTableList()
DatabaseFacade -> DBConnectionAdaptee: getTableList()
UIExport -> UIExport: targetTable = selectTable()
UIExport -> ControllerExport: setDataToUpdate(String targetTable, Cell [][]cells, int [][]pk)
ControllerExport -> DatabaseFacade: setDataToUpdate(String targetTable, Cell [][]cells, int [][]pk)
UIExport -> ControllerExport: starUpdate()
note left of ControllerExport
 thread launched 
 at this point
end note
ControllerExport -> DatabaseFacade: update()
DatabaseFacade -> DBConnectionAdaptee: updateTable(String targetTable, Cell [][]cells, int [][]pk)
ControllerExport -> ControllerExport: alertObservers()
@enduml

*/