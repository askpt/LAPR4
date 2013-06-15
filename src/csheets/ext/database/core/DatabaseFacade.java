package csheets.ext.database.core;

import java.sql.SQLException;
import java.util.*;

import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;

/**
 * A class that deals with all the data going into and from the databases (UML
 * facade pattern)
 * 
 * @author João Carreira
 */
public class DatabaseFacade extends Observable {
	private DBConnectionStrategy adapter;
	private String tableName;
	private Cell[][] cells;
	private int[] pk;

	/**
	 * constructor
	 */
	public DatabaseFacade() {
	}

	/**
	 * creates a connection to a database driver
	 * 
	 * @param url
	 *            path to the driver
	 * @param user
	 *            username
	 * @param pass
	 *            password
	 */
	public void createConnection(String url, String user, String pass,
			String dbName) throws SQLException, ClassNotFoundException,
			Exception {
		/* gets factory instance */
		DBConnectionFactory factory = DBConnectionFactory.getInstance();
		/* gets the corresponding adapter based on the adaptee class name */
		adapter = factory.getDBTechnology(dbName);
		adapter.createConnection(url, user, pass);
	}

	/**
	 * exports data to a database
	 * 
	 * @param cells
	 *            cells to be exported
	 * @param tableName
	 *            table's name in the database
	 */
	public void exportData(Cell[][] cells, String tableName) {
		this.tableName = tableName;
		this.cells = cells;
		adapter.createTable(tableName, cells);
		adapter.disconnet();
	}

	/**
	 * gets the table list of a given database
	 */
	public String[] getTableList() {
		return adapter.getTableList(adapter.queryToArray());
	}

	/**
	 * loads data from a table of the database
	 * 
	 * @param tableName
	 *            name of the table
	 */
	public String[][] loadTable(String tableName) {
		return adapter.getTableContent(tableName);
	}

	/**
	 * Starts a new sync with database
	 * 
	 * @param user
	 *            username
	 * @param pass
	 *            username's password
	 * @param cells
	 *            cells to be sync
	 * @param tableName
	 *            name of the table
	 * @param url
	 *            database url
	 * @param observer
	 *            the observer object
	 */
	public void startSync(String user, String pass, Cell[][] cells,
			String tableName, String url, Observer observer) {
		addObserver(observer);
		adapter.createTable(tableName, cells);

		CellDatabase[][] cellsTemp = new CellDatabase[cells.length - 1][cells[0].length];
		while (true) {
			try {
				temporaryStructure(cells, cellsTemp);
				adapter.disconnet();

				Thread.sleep(30000);
				adapter.createConnection(url, user, pass);
				String[][] serverCells = loadTable(tableName);
				for (int i = 0; i < cellsTemp.length; i++) {
					int indexServ = findLine(cellsTemp[i][0].getRow(),
							serverCells);
					int indexApp = findLine(cellsTemp[i][0].getRow(), cells);
					checkLine(serverCells[indexServ], cells[indexApp],
							cellsTemp[i]);
				}

			} catch (InterruptedException e) {
			} catch (FormulaCompilationException e) {
			} catch (ClassNotFoundException e) {
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * Creates a temporary structure of sync cells
	 * 
	 * @param cells
	 *            cells of application
	 * @param cellsTemp
	 *            temporary cells
	 */
	private void temporaryStructure(Cell[][] cells, CellDatabase[][] cellsTemp) {
		for (int i = 1; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cellsTemp[i - 1][j] = new CellDatabase(
						cells[i][j].getContent(), cells[i][j].getAddress()
								.getRow(), cells[i][j].getAddress().getColumn());
			}
		}
	}

	/**
	 * Method that will check if the line changes
	 * 
	 * @param lineServer
	 *            line in the server
	 * @param cellApp
	 *            line in the app
	 * @param cellTemp
	 *            line in the temporary cell
	 * @throws FormulaCompilationException
	 *             throws if the wrong formula was entered
	 */
	private void checkLine(String[] lineServer, Cell[] cellApp,
			CellDatabase[] cellTemp) throws FormulaCompilationException {
		boolean dbNeedChange = false;
		for (int i = 0; i < cellApp.length; i++) {
			if (cellApp[i].getContent().equals(cellTemp[i].getContent())
					&& !cellTemp[i].getContent().equals(lineServer[i + 1])) {
				cellApp[i].setContent(lineServer[i + 1]);
			}
			if (!cellApp[i].getContent().equals(cellTemp[i].getContent())
					&& cellTemp[i].getContent().equals(lineServer[i + 1])) {
				lineServer[i + 1] = cellApp[i].getContent();
				dbNeedChange = true;
			}
			if (!cellApp[i].getContent().equals(cellTemp[i].getContent())
					&& !cellTemp[i].getContent().equals(lineServer[i + 1])) {
				ObserverMessages obs = new ObserverMessages(lineServer[i + 1],
						cellApp[i].getContent());
				setChanged();
				notifyObservers(obs);
				clearChanged();
				if (obs.getDecision() == 0) {
					cellApp[i].setContent(lineServer[i + 1]);
				} else if (obs.getDecision() == 1) {
					lineServer[i + 1] = cellApp[i].getContent();
					dbNeedChange = true;
				}
			}
		}

		if (dbNeedChange) {
			// TODO add database update
		}
	}

	/**
	 * Find the index of the line in the server cells
	 * 
	 * @param row
	 *            the row of the line to be sync
	 * @param serverCells
	 *            the server cells
	 * @return the index of the line of the server
	 */
	private int findLine(int row, String[][] serverCells) {
		for (int i = 1; i < serverCells.length; i++) {
			if (Integer.parseInt(serverCells[i][0]) == row) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Find the index of the line in the application cells
	 * 
	 * @param row
	 *            the row of the line to be sync
	 * @param cells
	 *            the application cells
	 * @return the index of the line of the client
	 */
	private int findLine(int row, Cell[][] cells) {
		for (int i = 0; i < cells.length; i++) {
			if (cells[i][0].getAddress().getRow() == row) {
				return i;
			}
		}
		return 0;
	}

    /**
     * converts selected spreadsheet content in a 2D String array
     * @param cells selected cells in spreadsheet
     * @return 2D array with content in selected cells
     */    
    public String[][] cellsTo2dArray(Cell[][] cells) 
    {
        /* we add an additional row to temp so we can store the row number */
        String [][] temp = new String[cells.length][cells[0].length + 1];
        
        /* position [0][0] must always have LINHA */
        temp[0][0] = "LINHA";
        
        for(int i = 0; i < temp.length; i++)
        {
            /* if it's [i][0] other than [0][0] then we go get the row */
            if(i > 0)
            {
                temp[i][0] = Integer.toString(cells[i][0].getAddress().getRow() + 1);
            }
            /* the rest of the columns are filled with cells conent */
            for(int j = 1; j < temp[0].length; j++)
            {
                temp[i][j] = cells[i][j - 1].getContent().toString();
            }
        }
        return temp;
    }

    /**
     * compares if there's any difference in content between the selected cells
     * in the spreadsheet and the ones imported from the DB
     * @param tableData content imported from BD
     * @param selectedCells cells selected in the spreadsheet
     * @return true if there's any difference, false if they're equal
     */
    public boolean compareCellsWithDB(String[][] tableData, String[][] selectedCells) 
    {
        /* to avoid getting the array out of bounds index in need to strict 
         the comparison only in the range of the smaller array (we won't have
         trouble with columns as we only get here if both arrays have the same 
         number of cols) */
        
        int lessCols;
        if(tableData.length < selectedCells.length)
        {
            lessCols = tableData.length;
            /* is this case we're certain we can return true because having one more
             row implies that we have different information when compared to the DB */
            return true;
        }
        else
        {
            lessCols = selectedCells.length;
        }
        
        for(int i = 0; i < lessCols; i++)
        {
            for(int j = 0; j < selectedCells[0].length; j++)
            {
                if(!tableData[i][j].equals(selectedCells[i][j]))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
