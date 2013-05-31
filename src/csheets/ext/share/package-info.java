/**
 * An extension to share cells throw network.
 * 
 * <p>
 * <b>Use Case Diagram</b>
 * </p>
 * <img src="doc-files/use_case_diagram.png">
 * 
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
 sys -> sys : startServer()
 sys -> sys : send()
 sys --> us : return confirmation 
 @enduml

 @startuml doc-files/sd_system_rec.png
 Actor User as us
 participant System as sys

 us -> sys : startSharing()
 us -> sys : selectSharingMode()
 us -> sys : sendPort(port)
 us -> sys : sendIP (IP)
 sys -> sys : receive()
 sys --> us : return confirmation
 @enduml

 @startuml doc-files/sd_send_server.png
 Actor User as us
 participant SendAction as sa
 participant "UIExtension : SendUI" as sui
 participant SendController as sc
 participant Server as svr

 activate sui
 us -> sa : ActionPerformed(ActionEvent event)
 activate sa
 sa -> sui : startUI()
 us -> sui : insert(port, cells)
 Create sc
 sui -> sc : new
 activate sc
 sui -> sc : startServer(port, cells)
 Create svr
 sc -> svr : new
 activate svr
 sc -> svr : startServer(port, cells)
 note right svr
 Using TCP sockets
 end note
 svr -> svr : send(cells, svr)
 deactivate svr
 deactivate sc
 deactivate sa

 @enduml

 @startuml doc-files/sd_receive_client.png

 actor user as us
 participant ReceiveAction as ra
 participant ReceiveUI as rui
 participant ReceiveController as rc
 participant Client as cli

 activate rui
 us -> ra : ActionPerformed(ActionEvent event)
 activate ra
 ra -> rui : startUI()
 us -> rui : insert(IP, port, cell)
 Create rc
 rui -> rc : new
 activate rc
 rui -> rc : startClient(IP, port, cell)

 Create cli

 rc -> cli : new
 activate cli
 rc -> cli : startClient(IP, port, cell)
 note right cli
 Using TCP sockets
 end note
 cli -> cli : receive(cell, cli)
 deactivate cli
 deactivate rc
 deactivate ra

 @enduml

 */
package csheets.ext.share;