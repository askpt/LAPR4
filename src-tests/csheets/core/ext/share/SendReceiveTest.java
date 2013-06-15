package csheets.core.ext.share;

import static org.junit.Assert.*;

import org.junit.*;

import csheets.core.*;
import csheets.ext.share.core.*;

/**
 * Test the connectivity of the spreadsheet
 * 
 * @author Andre
 * 
 */
public class SendReceiveTest {

	private static Workbook workbook;
	private static Spreadsheet sheet;

	/**
	 * Create a new workbook for the program
	 * 
	 * @throws Exception
	 *             any Exception can occur
	 */
	@BeforeClass
	public static void setUpClass() throws Exception {

		/** create a new instance of workbook */
		workbook = new Workbook(3);

		/** Change to the first spreadsheet */
		sheet = workbook.getSpreadsheet(0);

	}

	/**
	 * Method that will test the connectivity
	 */
	@Test
	public void testConnection() {

		try {
			String password = "test";

			// Initializes cells
			Cell cellOri = sheet.getCell(new Address(1, 1));
			cellOri.setContent("56");
			Cell[][] cells = new Cell[1][1];
			cells[0][0] = cellOri;
			Cell cellFim = sheet.getCell(new Address(0, 0));

			// Create a new instance of server and client
			Server svr = Server.getInstance();
			Client cli = new Client();

			// Start client and server
			svr.startServer(50000, cells, Validate.encrypt(password.getBytes()));
			cli.startClient("localhost", 50000, cellFim,
					Validate.encrypt(password.getBytes()));

			// To wait to the system transmition
			Thread.sleep(1000);

			assertEquals(cellOri.getContent(), cellFim.getContent());
		} catch (Exception e) {
			fail("Exception Error!");
		}
	}
}
