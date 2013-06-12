package csheets.ext.database.controller;

import java.io.FileNotFoundException;
import java.util.*;

import csheets.ext.database.core.*;
import csheets.ext.database.ui.UISync;

public class ControllerSync {

	List<Database> dbList;

	public ControllerSync(UISync uiSync) {
		// TODO Auto-generated constructor stub
	}

	public String[][] getDBlist() throws FileNotFoundException {
		/* new DBCsvReader */
		DBCsvReader reader = new DBCsvReader();
		/* instantiating a new arraylist and getting all databases */
		dbList = new ArrayList<Database>();
		dbList = reader.getDBList();

		/* String array to store only the name of the databases */
		String[][] driversName = new String[dbList.size()][2];
		int i = 0;
		for (; i < dbList.size(); i++) {
			driversName[i][0] = dbList.get(i).getName();
			driversName[i][1] = dbList.get(i).getUrl();
		}
		/* returns all names of supported databases */
		return driversName;
	}

}
