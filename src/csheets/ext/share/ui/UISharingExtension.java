package csheets.ext.share.ui;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

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
public class UISharingExtension extends UIExtension implements Observer {

	/** The sidebar of the extension */
	private JComponent sidebar;

	/** The menu of the extension */
	private SharingMenu menu;

	/** The sharing extension */
	private final UISharingExtension uiShare = this;

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

	@Override
	public JMenu getMenu() {
		if (menu == null)
			menu = new SharingMenu(uiController);

		return menu;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JComponent getSideBar() {
		if (sidebar == null) {
			sidebar = new JPanel(new GridLayout(2, 1));
			sidebar.setName(SharingExtension.NAME);

			// Creates components

			JPanel sendPanel = new JPanel();
			JLabel sendStaticPort = new JLabel("Port");
			final JTextField sendPort = new JTextField(10);
			JLabel sendPass = new JLabel("Pass");
			final JPasswordField pass = new JPasswordField(10);
			String[] propsChoose = new String[] { "Writable", "Read-Only" };
			final JComboBox comboProps = new JComboBox(propsChoose);
			JLabel labelProp = new JLabel("Writable/Read-Only");
			JButton sendAction = new JButton("Send");
			sendAction.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String propsTemp = (String) comboProps.getSelectedItem();
					String props = "r";
					if (propsTemp.equals("Writable")) {
						props = "wr";

					}
					String portTemp = sendPort.getText();
					if (Validate.checkIfANumber(portTemp)) {
						int port = Integer.parseInt(portTemp);
						if (Validate.checkPort(port)) {
							SendAction.getInstance().clickOnSidebar(
									port,
									Validate.encrypt(String.copyValueOf(
											pass.getPassword()).getBytes()),
									props, uiShare);
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
			sendPanel.add(sendPass);
			sendPanel.add(pass);
			sendPanel.add(labelProp);
			sendPanel.add(comboProps);
			sendPanel.add(sendAction);

			JPanel receivePanel = new JPanel();
			JLabel recStaticIP = new JLabel("IP");
			final JTextField recIP = new JTextField(10);
			JLabel recStaticPort = new JLabel("Port");
			final JTextField recPort = new JTextField(10);
			final JPasswordField passReceive = new JPasswordField(10);
			JLabel passLabel = new JLabel("Password");
			JButton recAction = new JButton("Receive");
			recAction.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String portTemp = recPort.getText();
					String passwordReceive;
					if (Validate.checkIfANumber(portTemp)) {
						int port = Integer.parseInt(portTemp);
						if (Validate.checkPort(port)) {
							String IP = recIP.getText();
							if (Validate.checkIFIPIsCorrect(IP)) {
								passwordReceive = new String(passReceive
										.getPassword());
								ReceiveAction.getInstance().clickOnSidebar(
										IP,
										port,
										Validate.encrypt(passwordReceive
												.getBytes()), uiShare);
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
					List<Connections> connections = rec.findServers(uiShare);
					String passwordDiscover = null;

					if (connections.size() > 0) {

						Object[] objs = new Object[connections.size()];
						for (int i = 0; (i < connections.size()); i++) {
							objs[i] = connections.get(i);
						}
						Connections con = (Connections) JOptionPane
								.showInputDialog(sidebar, "Select a server",
										"Server Discover",
										JOptionPane.PLAIN_MESSAGE, null, objs,
										"");

						if (con != null) {
							passwordDiscover = passwordField();
							if (passwordDiscover != null) {
								ReceiveAction.getInstance().clickOnSidebar(
										con,
										uiShare,
										Validate.encrypt(passwordDiscover
												.getBytes()));
							}
						}
					} else {
						JOptionPane.showMessageDialog(sidebar,
								"Servers not found");
					}

				}
			});

			receivePanel.add(recStaticIP);
			receivePanel.add(recIP);
			receivePanel.add(recStaticPort);
			receivePanel.add(recPort);
			receivePanel.add(passLabel);
			receivePanel.add(passReceive);
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

	@Override
	public void update(Observable o, Object arg) {
		JOptionPane.showMessageDialog(null, "Connection Error");
	}

	/**
	 * Creates a new dialog with password
	 * 
	 * @return the password
	 */
	public String passwordField() {
		char[] password = null;
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter a password:");
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[] { "OK", "Cancel" };
		int option = JOptionPane.showOptionDialog(null, panel, "The title",
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[1]);
		if (option == 0) {
			password = pass.getPassword();
		}

		return new String(password);
	}
}
