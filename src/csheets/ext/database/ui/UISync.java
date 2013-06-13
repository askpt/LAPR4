package csheets.ext.database.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerSync;
import csheets.ext.database.core.ThreadSync;

//TODO Change the table text to the list after db choosed
/**
 * Class that will implements the interface of the sync box
 * 
 * @author Andre
 * 
 */
public class UISync extends JFrame {
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
				/* checks if there's at least two rows to proceed with export */
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

					/*
					 * the following comented code DOES NOT use a thread to
					 * export data
					 */
					/* connects to a database */
					// ctrlExp.connect(dbDrivers[index][1], userTxt.getText(),
					// pwd.getText(), dbDrivers[index][0]);
					/* setting data to be exported */
					// ctrlExp.setDataToExport(cells, userTxt.getText(),
					// pwd.getText(), tableTxt.getText());

					/* creating a new thread to export data */
					thrSync = new ThreadSync(cells, dbDrivers[index][1],
							userTxt.getText(), pwd.getSelectedText(),
							tableTxt.getText(), dbDrivers[index][0]);
					thrSync.run();
					dispose();

				}
			}
			/* button cancel */
			else if (e.getSource() == btnCancel) {
				dispose();
			}
		}
	}
}
