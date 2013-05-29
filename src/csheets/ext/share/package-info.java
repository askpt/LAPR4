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
 * 
 * 
 * @author Andre Silva
 */
/*

 @startuml doc-files/use_case_diagram.png

 left to right direction

 Actor user

 user --> (Share cells throw network)
 user --> (Receive cells throw network)
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
 */
package csheets.ext.share;