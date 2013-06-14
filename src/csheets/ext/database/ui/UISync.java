package csheets.ext.database.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerSync;
import csheets.ext.database.core.*;

/**
 * Class that will implements the interface of the sync box
 * 
 * @author Andre
 * 
 */
public class UISync extends JFrame implements Observer {
	/** Generated ID */
	private static final long serialVersionUID = 1L;
	/**
	 * database available drivers stored in a string and displayed in a combobox
	 */
	private final String[][] dbDrivers;
	/** drivers name */
	private final String[] driversName;
	/** drivers list */
	@SuppressWarnings("rawtypes")
	private final JComboBox comboDrivers;

	/** controller object for GUI-controller pattern */
	private final ControllerSync ctrlSync;

	/** button of ok */
	private final JButton btnOk = new JButton("OK");
	/** button of cancel */
	private final JButton btnCancel = new JButton("Cancel");
	/** button of get url */
	private final JButton btnUrl = new JButton("Get URL");

	/** selected cells to export in a 2D array */
	private final Cell[][] cells;

	/** textfields for username, passord, database and table name */
	@SuppressWarnings("unused")
	private final JTextField userTxt, dbTxt, tableTxt, urlTxt;
	/** field for the password */
	private final JPasswordField pwd;

	/** label to display system information to the user */
	JLabel sysMsg = new JLabel();

	/** export thread */
	ThreadSync thrSync;

	/** this ui */
	UISync sync = this;

	/** return value for merge */
	private int returnValue = -1;

	/**
	 * Creates a new sync ui
	 * 
	 * @param cells
	 *            cells to be synchronized
	 * @throws Exception
	 *             if any type of exceptions occurs
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UISync(Cell[][] cells) throws Exception {
		/* window title */
		super("Sync information with database");

		/* saving argument of this function is class variable */
		this.cells = cells;

		/* creating a new controller */
		ctrlSync = new ControllerSync();

		/*
		 * getting the list of supported databases and putting it in the combo
		 * box
		 */
		dbDrivers = ctrlSync.getDBlist();
		driversName = new String[dbDrivers.length];
		for (int i = 0; i < dbDrivers.length; i++) {
			driversName[i] = dbDrivers[i][0];
		}
		comboDrivers = new JComboBox(driversName);

		/* main panel */
		JPanel mainPanel = new JPanel(new BorderLayout());

		/* defining labels */
		JLabel lblDBdrivers = new JLabel("Database");
		JLabel lblUser = new JLabel("Username");
		JLabel lblPwd = new JLabel("Password");
		JLabel lblUrl = new JLabel("URL");
		JLabel lblTableName = new JLabel("Table name");

		/* setting default system message text and color */
		sysMsg.setText("Fill the required fields");
		sysMsg.setForeground(Color.BLUE);

		/* defining text fields */
		dbTxt = new JTextField(30);
		userTxt = new JTextField(30);
		pwd = new JPasswordField("");
		tableTxt = new JTextField(30);
		urlTxt = new JTextField(30);

		/* defining another panel */
		JPanel anotherPanel = new JPanel(new GridLayout(7, 1));
		anotherPanel.add(lblDBdrivers);
		anotherPanel.add(comboDrivers);
		anotherPanel.add(lblUser);
		anotherPanel.add(userTxt);
		anotherPanel.add(lblPwd);
		anotherPanel.add(pwd);
		anotherPanel.add(lblUrl);
		anotherPanel.add(urlTxt);
		anotherPanel.add(lblTableName);
		anotherPanel.add(tableTxt);
		anotherPanel.add(sysMsg);

		/* defining panel for buttons */
		JPanel panelBtn = new JPanel();
		panelBtn.add(btnOk);
		panelBtn.add(btnCancel);
		panelBtn.add(btnUrl);

		/* setting up action listeners */
		HandlesEvent t = new HandlesEvent();
		btnOk.addActionListener(t);
		btnCancel.addActionListener(t);
		btnUrl.addActionListener(t);

		/* adding all object to build window */
		Container c = getContentPane();
		mainPanel.add(anotherPanel);
		mainPanel.add(panelBtn, BorderLayout.SOUTH);
		c.add(mainPanel);

		/* other window settings */
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	/**
	 * 
	 * Class that will handle the events occured in the user interface
	 * 
	 * @author Andre
	 * 
	 */
	private class HandlesEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/* default url button */
			if (e.getSource() == btnUrl) {
				urlTxt.setText(dbDrivers[comboDrivers.getSelectedIndex()][1]);
			}

			/* button OK */
			else if (e.getSource() == btnOk) {
				/* checks if there's at least two rows to proceed with sync */
				if (cells.length < 2) {
					JOptionPane
							.showMessageDialog(null,
									"Error: you must select at least\ntwo rows to sync!");
					dispose();
				}

				/* checks if all fields are filled */
				if ((userTxt.getText().trim().length() == 0)
						|| (pwd.getPassword().length == 0)
						|| (tableTxt.getText().trim().length() == 0)) {
					sysMsg.setText("Username/password/tablename required!");
					sysMsg.setForeground(Color.RED);
				}
				/* if all fields are filled tries to connect */
				else {
					/* the combo index indicates which database will be used */
					int index = comboDrivers.getSelectedIndex();

					thrSync = new ThreadSync(cells, dbDrivers[index][1],
							userTxt.getText(), pwd.getSelectedText(),
							tableTxt.getText(), dbDrivers[index][0], sync);
					Thread thr = new Thread(thrSync);
					thr.start();
					dispose();

				}
			}
			/* button cancel */
			else if (e.getSource() == btnCancel) {
				dispose();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {

		MergeWindowSync ui = new MergeWindowSync(sync, true);
		ObserverMessages obm = (ObserverMessages) arg;
		String message = "Database:" + obm.getDatabaseValue()
				+ "\nApplication:" + obm.getApplicationValue()
				+ "\nWhat do you wanna persist?";
		ui.createWindow(message);
		ui.setVisible(true);
		obm.setDecision(returnValue);
	}

	/**
	 * Creates a new merge window error
	 * 
	 * @author Andre
	 * 
	 */
	public class MergeWindowSync extends JDialog {
		/** generated id */
		private static final long serialVersionUID = 1L;
		/** application button */
		private JButton jButton1;
		/** database button */
		private JButton jButton2;
		/** text area */
		private JTextArea jLabel1;

		/**
		 * Creates new MergeWindowSync frame
		 */
		public MergeWindowSync(Frame parent, boolean modal) {
			super(parent, modal);
		}

		/**
		 * Creates a new window to choose between application and database
		 * 
		 * @param text
		 *            the text in label
		 * @return the choosed method
		 */
		public void createWindow(String text) {
			jLabel1 = new JTextArea(text);
			jLabel1.setEditable(false);
			jButton1 = new JButton("Application");
			jButton1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					returnValue = 0;

					dispose();
				}
			});
			jButton2 = new JButton("Database");
			jButton2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					returnValue = 1;

					dispose();
				}
			});
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(
							layout.createSequentialGroup()
									.addGap(35, 35, 35)
									.addGroup(
											layout.createParallelGroup(
													GroupLayout.Alignment.LEADING,
													false)
													.addGroup(
															layout.createSequentialGroup()
																	.addComponent(
																			jButton1)
																	.addPreferredGap(
																			ComponentPlacement.RELATED,
																			GroupLayout.DEFAULT_SIZE,
																			Short.MAX_VALUE)
																	.addComponent(
																			jButton2))
													.addComponent(jLabel1))
									.addContainerGap(51, Short.MAX_VALUE)));
			layout.setVerticalGroup(layout.createParallelGroup(
					Alignment.LEADING).addGroup(
					layout.createSequentialGroup()
							.addGap(28, 28, 28)
							.addComponent(jLabel1)
							.addPreferredGap(ComponentPlacement.RELATED, 55,
									Short.MAX_VALUE)
							.addGroup(
									layout.createParallelGroup(
											Alignment.BASELINE)
											.addComponent(jButton1)
											.addComponent(jButton2))
							.addGap(22, 22, 22)));
			pack();

		}
	}
}
