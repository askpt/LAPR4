/*
 *@startuml doc-files/classDiagram.png
class ConnectionHSQLDB {
	-String driver
	-Connection conn
	-String userName
	-String password
	-String url
	+Statement statement
	+ResultSet resultSet
	+ConnectionHSQLDB()
	+void connect(String url, String user, String pass)
	+void desconnect()
	+void executeSQL(String sql)
	+void update(String expression)
	+void insertTable(Cell[][] cells, String tableName)
}
interface BasicDataBase {
}
BasicDataBase <|.. ConnectionHSQLDB



class ControllerExport {
	-String url
	+ControllerExport()
	+void ExportSelectedCells(Cell[][] cells, String user, String pass, String tableName)
}



interface BasicDataBase {
	void connect(String url, String user, String pass)
	void desconnect()
	void executeSQL(String sql)
	void insertTable(Cell[][] cells, String tableName)
}





class DataBaseFactory {
}



class ExportAction {
	#UIController uiController
	+ExportAction(UIController uiController)
	#String getName()
	#void defineProperties()
	+void actionPerformed(ActionEvent event)
}
abstract class FocusOwnerAction {
}
FocusOwnerAction <|-- ExportAction





class ExportMenu {
	+ExportMenu(UIController uiController)
}
class JMenu {
}
JMenu <|-- ExportMenu





class UIExport {
	-String[] databaseDrivers
	-JComboBox driversCombo
	-ControllerExport control
	-JButton ok
	-JButton cancel
	-Cell[][] cells
	-JTextField userText
	-JTextField passText
	-JTextField tableText
	-JTextField databaseText
	-ThreadExport threadExp
	+UIExport(Cell[][] cells)
	-void butOkActionPerformed(ActionEvent event)
	-void CancelActionPerformed(ActionEvent event)
	-void comboDriverAction(ActionEvent event)
}
class JFrame {
}
JFrame <|-- UIExport



class UIExportExtension {
	-Icon icon
	-ExportMenu menu
	+UIExportExtension(Extension extension, UIController uiController)
	+Icon getIcon()
	+JMenu getMenu()
	+CellDecorator getCellDecorator()
	+TableDecorator getTableDecorator()
	+JToolBar getToolBar()
	+JComponent getSideBar()
}
abstract class UIExtension {
}
UIExtension <|-- UIExportExtension



class ExtensionExport {
	+{static}String NAME
	+ExtensionExport()
	+UIExtension getUIExtension(UIController uiController)
}
abstract class Extension {
}
Extension <|-- ExtensionExport



class ThreadExport {
	-Cell[][] cells
	-String user
	-String pass
	-String tableName
	-ControllerExport control
	+ThreadExport(Cell[][] cells, String user, String pass, String tableName)
	+void run()
}
interface Runnable {
}
Runnable <|.. ThreadExport
@enduml
 * 
 * 
 * 
 */
/**
 * <p>
 * <b>Startup Function Setup Diagram - Startup Extension</b>
 * </p>
 * <img src="doc-files/FunctionSetupDiagram.png">
 * 
 * <p>
 * <b>Startup Use Case Diagram - Startup Extension</b>
 * </p>
 * <img src="doc-files/UseCaseDiagramExportDataBase.png">
 * 
 * <p>
 * <b>Use Case Realization Diagram - Startup Extension</b>
 * </p>
 * <img src="doc-files/useCaseRealizationDiagram.png">
 * 
 * <p>
 * <b>Use Case Realization Diagram - Startup Extension</b>
 * </p>
 * <img src="doc-files/DesignDiagram.png">
 * 
 * <p>
 * <b>Class Diagram</b>
 * </p>
 * <img src="doc-files/classDiagram.png">
 * @author Tiago Pacheco
 * 
 *
 */
package csheets.ext.exportdb;