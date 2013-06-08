package csheets.ext.share.ui;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import csheets.ext.Extension;
import csheets.ext.share.SharingExtension;
import csheets.ext.share.controller.ReceiveController;
import csheets.ext.share.core.*;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * This class implements the UI interface extension for the sharing extension.
 * 
 * @see UIExtension
 * @author Andre
 * 
 */
public class UISharingExtension extends UIExtension {

    /** The sidebar of the extension */
    private JComponent sidebar;

    /** The menu of the extension */
    private SharingMenu menu;

    /**
     * Creates a new UI for sharing extension
     * 
     * @param extension
     *            the sharing extension
     * @param uiController
     *            the user interface controller
     */
    public UISharingExtension(Extension extension, UIController uiController) {
	super(extension, uiController);
    }

    /**
     * Returns an instance of the SharingMenu
     * 
     * @see SharingMenu
     * @return a JMenu component
     */
    @Override
    public JMenu getMenu() {
	if (menu == null)
	    menu = new SharingMenu(uiController);

	return menu;
    }

    /**
     * Creates a sidebar for the extension
     * 
     * @return the created sidebar
     */
    @Override
    public JComponent getSideBar() {
	if (sidebar == null) {
	    sidebar = new JPanel(new GridLayout(2, 1));
	    sidebar.setName(SharingExtension.NAME);

	    // Creates components

	    JPanel sendPanel = new JPanel();
	    JLabel sendStaticPort = new JLabel("Port");
	    final JTextField sendPort = new JTextField(10);
	    JButton sendAction = new JButton("Send");
	    sendAction.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    String portTemp = sendPort.getText();
		    if (Validate.checkIfANumber(portTemp)) {
			int port = Integer.parseInt(portTemp);
			if (Validate.checkPort(port)) {
			    SendAction.getInstance().clickOnSidebar(port);
			} else {
			    JOptionPane.showMessageDialog(null,
				    "Check if port is between 49152 and 65535",
				    "Port Error", JOptionPane.ERROR_MESSAGE);
			}
		    } else {
			JOptionPane.showMessageDialog(null,
				"Inserted port is not a number", "Port Error",
				JOptionPane.ERROR_MESSAGE);
		    }
		}
	    });
	    sendPanel.add(sendStaticPort);
	    sendPanel.add(sendPort);
	    sendPanel.add(sendAction);

	    JPanel receivePanel = new JPanel();
	    JLabel recStaticIP = new JLabel("IP");
	    final JTextField recIP = new JTextField(10);
	    JLabel recStaticPort = new JLabel("Port");
	    final JTextField recPort = new JTextField(10);
	    JButton recAction = new JButton("Receive");
	    recAction.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    String portTemp = recPort.getText();
		    if (Validate.checkIfANumber(portTemp)) {
			int port = Integer.parseInt(portTemp);
			if (Validate.checkPort(port)) {
			    String IP = recIP.getText();
			    if (Validate.checkIFIPIsCorrect(IP)) {
				ReceiveAction.getInstance().clickOnSidebar(IP,
					port);
			    } else {
				JOptionPane
					.showMessageDialog(
						null,
						"Insert in IPv4 format, or use localhost",
						"IP Error",
						JOptionPane.ERROR_MESSAGE);
			    }

			} else {
			    JOptionPane.showMessageDialog(null,
				    "Check if port is between 49152 and 65535",
				    "Port Error", JOptionPane.ERROR_MESSAGE);
			}

		    } else {
			JOptionPane.showMessageDialog(null,
				"Inserted port is not a number", "Port Error",
				JOptionPane.ERROR_MESSAGE);
		    }
		}
	    });

	    JButton recDiscoverServer = new JButton("Discover Servers");
	    recDiscoverServer.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

		    ReceiveController rec = new ReceiveController();
		    List<Connections> connections = rec.findServers();
		    // TODO add a JComboBox
		}
	    });

	    receivePanel.add(recStaticIP);
	    receivePanel.add(recIP);
	    receivePanel.add(recStaticPort);
	    receivePanel.add(recPort);
	    receivePanel.add(recAction);
	    receivePanel.add(recDiscoverServer);

	    // Adds borders
	    TitledBorder border = BorderFactory.createTitledBorder("Receiving");
	    border.setTitleJustification(TitledBorder.CENTER);
	    receivePanel.setBorder(border);
	    border = BorderFactory.createTitledBorder("Sending");
	    border.setTitleJustification(TitledBorder.CENTER);
	    sendPanel.setBorder(border);

	    // Creates side bar
	    sidebar.add(sendPanel);
	    sidebar.add(receivePanel);
	}
	return sidebar;
    }
}
