/**
 * An extension to share cells throw network.
 * 
 * <p>
 * <b>Use Case Diagram</b>
 * </p>
 * <img src="doc-files/use_case_diagram.png">
 * 
 *  * <p>
 * <b>Use Case Diagram (week 2)</b>
 * </p>
 * <img src="docs/1110333/w2_rail_5/diagrams/UseCaseDiagram.png">
 * <p>
 * <b>Startup Sequence Diagram - Startup Extension</b>
 * </p>
 * <img src="doc-files/sd_start_ext.png">
 * 
 * <p>
 * <b>Startup Sequence Diagram - Menu Creation</b>
 * </p>
 * <img src="doc-files/sd_start_menu.png">
 * 
 * <p>
 * <b>Startup Sequence Diagram - Interface Creation</b>
 * </p>
 * <img src="doc-files/sd_start_int.png">
 * 
 * <p>
 * <b>System Sequence Diagram - Send</b>
 * </p>
 * <img src="doc-files/sd_system_send.png">
 * 
 * <p>
 * <b>System Sequence Diagram - Receive</b>
 * </p>
 * <img src="doc-files/sd_system_rec.png">
 * 
 * <p>
 * <b>Sequence Diagram - Send Cells (Server)</b>
 * </p>
 * <img src="doc-files/sd_send_server.png">
 * 
 * <p>
 * <b>Sequence Diagram - Receive Cells (Client)</b>
 * </p>
 * <img src="doc-files/sd_receive_client.png">
 * 
 * <p>
 * <b>Sequence Diagram - Discover Servers</b>
 * </p>
 * <img src="doc-files/sd_cli_discover.png">
 * 
 * <p>
 * <b>Diagram to illustrate data communication beetween Client and Server</b>
 * </p>
 * <img src="doc-files/sd_client_server.png">
 * 
 * <p>
 * <b> Class Diagram </b>
 * </p>
 * <img src="doc-files/class_diagram.png">
 * 
 * @author Andre Silva
 */
/*

 @startuml doc-files/use_case_diagram.png

 left to right direction

 Actor User

 User --> (Share cells throw network)
 User --> (Receive cells throw network)
 @enduml

 @startuml doc-files/sd_start_ext.png

 participant ExtensionManager as ExtM
 participant Class
 participant "aClass:Class" as aClass
 participant "extension : SharingExtension" as ESharing
 ExtM -> Class : aClass = forName("csheets.ext.share.SharingExtension");
 activate Class
 create aClass
 Class -> aClass : new
 deactivate Class
 ExtM -> aClass : extension = (Extension)newInstance();
 activate aClass
 create ESharing
 aClass -> ESharing : new
 deactivate aClass
 ExtM -> ESharing : name = getName();
 activate ESharing
 deactivate ESharing
 ExtM -> ExtM : extensionMap.put(name, extension)
 @enduml

 @startuml doc-files/sd_start_int.png
 participant UIController as UIC
 participant ExtensionManager as ExtM
 participant "extension : SharingExtension" as ESharing
 participant "uiExtension : UISharingExtension" as UIShr
 UIC -> ExtM : extensions=getExtensions();
 loop for Extension ext : extensions
 UIC -> ESharing : uiExtension=getUIExtension(this);
 activate ESharing
 create UIShr
 ESharing -> UIShr : new
 deactivate ESharing
 UIC -> UIC : uiExtensions.add(uiExtension);
 end
 @enduml


 @startuml doc-files/sd_start_menu.png
 participant MenuBar as MB
 participant "extensionsMenu : JMenu" as extensionsMenu
 participant UIController as UIC
 participant "extension : UISharingExtension" as UIS
 participant "extensionMenu : SharingMenu" as SM 
 MB -> MB : extensionsMenu = addMenu("Extensions", KeyEvent.VK_X);
 activate MB
 create extensionsMenu
 MB -> extensionsMenu : new
 deactivate MB
 MB -> UIC : extensions=getExtensions();
 loop for UIExtension extension : extensions
 MB -> UIS : extensionMenu=extension.getMenu();
 activate UIS
 create SM
 UIS -> SM : new
 deactivate UIS
 MB -> SM : icon = getIcon();
 MB -> extensionsMenu : add(extensionMenu); 
 end
 @enduml


 @startuml doc-files/sd_system_send.png
 Actor User as us
 participant System as sys

 us -> sys : startSharing()
 us -> sys : selectSharingMode()
 us -> sys : sendPort(port)
 us -> sys : sendCells(cells)
 sys --> us : return confirmation 
 @enduml

 @startuml doc-files/sd_system_rec.png
 Actor User as us
 participant System as sys

 us -> sys : startSharing()
 us -> sys : selectSharingMode()
 us -> sys : sendPort(port)
 us -> sys : sendIP (IP)
 sys --> us : return confirmation
 @enduml

 @startuml doc-files/sd_send_server.png
 Actor User as us
 participant "UIExtension : UISharingExtension" as sui
 participant SendAction as sa
 participant SendController as sc
 participant Server as svr
 participant ServerDiscover as svrDisc

 activate svrDisc
 activate sui
 activate sa
 us -> sui : insert(port)
 us -> sa : ActionPerformed(ActionEvent event)

 Create sc
 sa -> sc : create
 activate sc
 sa -> sc : startServer(port, cells)
 Create svr
 sc -> svr : create
 activate svr
 sc -> svr : startServer(port, cells)
 svr -> svrDisc : findClients(port)
 svrDisc -> svrDisc : broadcast()
 note right svr
 Using TCP sockets
 end note

 svr -> svr : send(cells, svr)
 deactivate svr
 deactivate sc

 @enduml

 @startuml doc-files/sd_receive_client.png

 actor user as us
 participant "UIExtension : UISharingExtension" as rui
 participant ReceiveAction as ra
 participant ReceiveController as rc
 participant Client as cli

 activate rui
 activate ra
 us -> rui : insert(IP, port)
 rui -> ra : ActionPerformed(ActionEvent event)
 Create rc
 ra -> rc : create
 activate rc
 ra -> rc : startClient(IP, port, cell)

 Create cli

 rc -> cli : create
 activate cli
 rc -> cli : startClient(IP, port, cell)
 note right cli
 Using TCP sockets
 end note
 cli -> cli : receive(cell, cli)
 deactivate cli
 deactivate rc

 @enduml

 @startuml doc-files/sd_client_server.png
 participant Server as svr
 participant Network as net
 participant Client as cli
 participant CellNetworkListener as listener
 participant ThreadServer as threadsvr
 participant ThreadClient as threadcli

 svr->threadsvr : new ThreadServer()
 cli -> net : out("send me data")
 net -> threadsvr : in ("send me data")
 alt if in=send me data
 loop for i < cells.size && while isCell
 loop for j < cells[i].size
 threadsvr->net : out(cell) 
 net -> cli : in (cell)
 cli->listener : addListner(cell)
 end 
 end
 end
 threadsvr -> net : out("Close yourself")
 cli -> cli : close()
 net -> threadsvr : in ("Close yourself")
 alt if in=Close yourself
 threadsvr -> threadsvr : close()
 end

 loop
 listener->cli : contentChanged(cell)
 cli-> net : out("send me updated data")
 net-> threadsvr: in("send me updated data")

 alt [if in="send me updated data"]
 loop


 cli->net :sendToServer(cell)
 cli-> threadcli : new ThreadClient ()
 net->svr : receiveUpdates(cell)
 svr-> svr : update(cell)
 svr-> net : sendAllClients(cell)
 net->threadcli : receiveUpdates(cell)
 threadcli->threadcli : update(cell)


 end
 end




 @enduml


 @startuml doc-files/class_diagram.png

 package java
 package java.lang
 interface Runnable {
 }
 end package

 package java.io
 interface Serializable {
 }
 end package
 end package

 package javax.swing
 class JMenu {
 }
 end package


 package csheets
 package csheets.ui
 package csheets.ui.ctrl
 abstract class FocusOwnerAction {
 }
 end package
 package csheets.ui.ext
 abstract class UIExtension {
 }
 end package
 end package
 package csheets.ext
 abstract class Extension {
 }
 package csheets.ext.share
 class SharingExtension {
 +{static}String NAME
 +{static}int LOGGER_SIZE
 +SharingExtension()
 +UIExtension getUIExtension(UIController uiController)
 }

 package csheets.ext.share.controller
 class ReceiveController {
 +void startClient(String IP, int port, Cell cellStart)
 }
 class SendController {
 +void startServer(int port, Cell[][] cells)
 }
 end package

 package csheets.ext.share.core

 class Validate {
 +{static}boolean checkIFIPIsCorrect(String IP)
 +{static}boolean checkPort(int port)
 +{static}boolean checkIfANumber(String port)
 }
 class Server {
 -int port
 -Cell[][] cells
 -ServerSocket svr
 -String Ip
 -boolean changesClient
 -CellNetworkListenerServer listener
 -{static}Server instance
 -Server()
 +{static}Server getInstance()
 +CellNetworkListenerServer getListener()
 -Server(int port, Cell[][] cells, ServerSocket svr)
 +void startServer(int port, Cell[][] cells)
 +void run()
 }
 interface Runnable {
 }
 Runnable <|.. Server

 class Client {
 -String IP
 -int port
 -Cell cellStart
 -Connections connection
 -CellNetworkListenerClient listener
 +Client()
 -Client(String IP, int port, Cell cellStart)
 -Client(Connections connection, Cell cellStart)
 +void startClient(String IP, int port, Cell cellStart)
 +void startClient(Connections connection, Cell cellStart)
 -void receive(Cell cellStart, Socket cli)
 +void sendToServer(Cell cellUpdated, Socket sock)
 +void run()
 }
 interface Runnable {
 }
 Runnable <|.. Client

 class ThreadServer {
 -int port
 -Cell[][] cells
 -Socket sock
 -Cell cellUpdated
 -ThreadServer()
 +ThreadServer(int port, Cell[][] cells, Socket sock)
 -void send(Cell[][] cells, Socket sock)
 -void receiveUpdates(Cell cellUpdated, Socket cli)
 -void sendAllClients(Cell[][] cells, Socket sock)
 +void run()
 }
 interface Runnable {
 }
 Runnable <|.. ThreadServer

 class CellNetworkListenerClient {
 -boolean flag
 -Cell cell
 +void setFlag(boolean flag)
 +void setCell(Cell cell)
 +Cell getCell()
 +boolean getFlag()
 +void valueChanged(Cell cell)
 +void contentChanged(Cell cell)
 +void dependentsChanged(Cell cell)
 +void cellCleared(Cell cell)
 +void cellCopied(Cell cell, Cell source)
 }
 interface CellListener {
 }
 CellListener <|.. CellNetworkListenerClient

 class ThreadClient {
 -int port
 -Cell cellStart
 -Socket sock
 -ThreadClient()
 +ThreadClient(int port, Cell cellStart, Socket sock)
 -void receiveUpdates(Cell cellStart, Socket cli)
 +void run()
 }
 interface Runnable {
 }
 Runnable <|.. ThreadClient

 class CellNetworkListenerServer {
 -boolean flag
 -Cell cell
 +void setFlag(boolean flag)
 +void setCell(Cell cell)
 +Cell getCell()
 +boolean getFlag()
 +void valueChanged(Cell cell)
 +void contentChanged(Cell cell)
 +void dependentsChanged(Cell cell)
 +void cellCleared(Cell cell)
 +void cellCopied(Cell cell, Cell source)
 }
 interface CellListener {
 }
 CellListener <|.. CellNetworkListenerServer
 class CellNetwork {
 -String content
 -int row
 -int column
 -boolean isCell
 +CellNetwork(String content, int row, int column, boolean isCell)
 +boolean isCell()
 +String getContent()
 +int getRow()
 +int getColumn()
 }
 end package

 package csheets.ext.share.ui
 class ReceiveAction {
 -{static}long serialVersionUID
 #UIController uiController
 -{static}ReceiveAction instance
 #{static}ReceiveAction getInstance()
 +ReceiveAction(UIController uiController)
 +void actionPerformed(ActionEvent event)
 #String getName()
 +void clickOnSidebar(String IP, int port)
 }

 class ReceiveUI {
 +void createUI(Cell cellStart)
 }

 class SendAction {
 -{static}long serialVersionUID
 #UIController uiController
 #{static}SendAction instance
 +{static}SendAction getInstance()
 +SendAction(UIController uiController)
 +void actionPerformed(ActionEvent event)
 +void clickOnSidebar(int port)
 #String getName()
 }
 class SendUI {
 +void createUI(Cell[][] cells)
 }

 class SharingMenu {
 -{static}long serialVersionUID
 +SharingMenu(UIController uiController)
 }
 class UISharingExtension {
 -JComponent sidebar
 -SharingMenu menu
 +UISharingExtension(Extension extension, UIController uiController)
 +JMenu getMenu()
 +JComponent getSideBar()
 }
 end package
 end package
 end package
 end package



 Extension <|-- SharingExtension
 Runnable <|.. Client
 Runnable <|.. Server
 Serializable <|.. CellNetwork
 FocusOwnerAction <|-- ReceiveAction
 FocusOwnerAction <|-- SendAction
 JMenu <|-- SharingMenu
 UIExtension <|-- UISharingExtension

 class ThreadClient {
 -int port
 -Cell cellStart
 -Socket sock
 -ThreadClient()
 +ThreadClient(int port, Cell cellStart, Socket sock)
 -void receiveUpdates(Cell cellStart, Socket cli)
 +void run()
 }
 interface Runnable {
 }
 Runnable <|.. ThreadClient



 @enduml


 @startuml doc-files/sd_cli_discover.png
 actor user as us
 participant "UIExtension : UISharingExtension" as rui
 participant ReceiveController as rc
 participant ClientDiscover as cliDisc

 activate rui
 activate cliDisc
 us -> rui : find Servers
 Create rc
 rui -> rc : create
 activate rc
 rui -> rc : findServers()
 rc -> cliDisc : findServers()
 cliDisc -> cliDisc : search()
 cliDisc --> rc : return connections
 rc --> rui : return connections
 rui -> rui : show

 deactivate rc
 @enduml
 */
package csheets.ext.share;