package csheets.core.formula.lang;

import static org.junit.Assert.*;

import org.junit.*;

import csheets.core.*;

/**
 * Class that will test the whiledo function
 * 
 * @author Andre
 * 
 */
public class WhiledoTest {

	private static Workbook workbook;
	private static Spreadsheet sheetApp;

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
		sheetApp = workbook.getSpreadsheet(0);

	}

	/**
	 * Test the get identifier
	 */
	@Test
	public void testGetIdentifier() {
		Whiledo wd = new Whiledo();
		assertEquals("WHILEDO", wd.getIdentifier());
	}

	/**
	 * Test the apply to
	 */
	@Test
	public void testApplyTo() {
		try {
			Cell cellOri = sheetApp.getCell(new Address(0, 0));
			Cell cellFim = sheetApp.getCell(new Address(0, 1));
			cellFim.setContent("1");
			cellOri.setContent("#whiledo{a2<10;a2:=a2+1}");
			Value resulted = cellOri.getValue();
			assertEquals("11", resulted.toString());

		} catch (Exception e) {
			fail("Exception error!");
		}

	}

	/**
	 * Test if is Variable arguments
	 */
	@Test
	public void testIsVarArg() {
		Whiledo wd = new Whiledo();
		assertTrue(wd.isVarArg());
	}

}
