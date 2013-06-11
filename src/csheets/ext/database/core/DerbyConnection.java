package csheets.ext.database.core;

import java.sql.*;

import javax.swing.JOptionPane;

import csheets.core.Cell;

public class DerbyConnection implements DBConnectionStrategy {

	private final String driverPath = "org.apache.derby.jdbc.EmbeddedDriver";
	private Connection connection;
	private String url, user, pwd;

	@Override
	public void createConnection(String url, String user, String pass)
			throws ClassNotFoundException, SQLException {
		try {
			Class.forName(driverPath);
			connection = DriverManager.getConnection(url + ";create=true",
					user, pass);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: class not found!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error: connection to database!");
		}
	}

	@Override
	public void createTable(String tableName, Cell[][] cells) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void disconnet() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void getTableList() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void getTableContent() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
