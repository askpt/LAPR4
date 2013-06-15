package csheets.core.formula.lang;

import static org.junit.Assert.*;

import org.junit.*;

import csheets.core.*;

/**
 * Class that will test the eval function
 * 
 * @author Andre
 * 
 */
public class EvalTest {

	private static Workbook workbook;
	private static Spreadsheet sheetApp;

	/**
	 * Create a new workbook for the program
	 * 
	 * @throws Exception
	 *             any Exception can occur
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		Eval ev = new Eval();
		assertEquals("EVAL", ev.getIdentifier());
	}

	/**
	 * Test the apply to
	 */
	@Test
	public void testApplyTo() {
		try {
			Cell cellOri = sheetApp.getCell(new Address(0, 0));
			Cell cellFim = sheetApp.getCell(new Address(0, 1));
			cellFim.setContent("");
			cellOri.setContent("#eval(\"#a2:=2\")");
			Value resulted = cellOri.getValue();
			assertEquals("#a2:=2", resulted.toString());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception error!");
		}

	}

	/**
	 * Test if is Variable arguments
	 */
	@Test
	public void testIsVarArg() {
		Eval ev = new Eval();
		assertTrue(ev.isVarArg());
	}

}
