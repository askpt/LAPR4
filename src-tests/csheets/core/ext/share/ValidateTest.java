package csheets.core.ext.share;

import static org.junit.Assert.*;

import org.junit.Test;

import csheets.ext.share.core.Validate;

/**
 * Class that will test the class Validate
 * 
 * @see Validate
 * @author Andre
 * 
 */
public class ValidateTest {

	/**
	 * Test if the introduced ip is correct
	 */
	@Test
	public void testCheckIFIPIsCorrect() {
		String ipTestValid1 = "localhost";
		String ipTestInvalid1 = "localehoste";
		String ipTestValid2 = "127.0.0.1";
		String ipTestInvalid2 = "256.256.256.0";

		assertFalse(Validate.checkIFIPIsCorrect(ipTestInvalid1));
		assertFalse(Validate.checkIFIPIsCorrect(ipTestInvalid2));
		assertTrue(Validate.checkIFIPIsCorrect(ipTestValid1));
		assertTrue(Validate.checkIFIPIsCorrect(ipTestValid2));
	}

	/**
	 * Test if the introduced port is allowed. Must be between 49152 and 65535
	 */
	@Test
	public void testCheckPort() {
		int portValid1 = 50000;
		int portValid2 = 65535;
		int portValid3 = 49152;
		int portInvalid1 = 1;
		int portInvalid2 = 49151;
		int portInvalid3 = 65536;

		assertFalse(Validate.checkPort(portInvalid1));
		assertFalse(Validate.checkPort(portInvalid2));
		assertFalse(Validate.checkPort(portInvalid3));
		assertTrue(Validate.checkPort(portValid1));
		assertTrue(Validate.checkPort(portValid2));
		assertTrue(Validate.checkPort(portValid3));
	}

	/**
	 * Test if the introduced port is a number
	 */
	@Test
	public void testCheckIfANumber() {
		String portValid1 = "12312";
		String portValid2 = "11";
		String portValid3 = "123456789";
		String portInvalid1 = "a";
		String portInvalid2 = "a1";
		String portInvalid3 = "aadasd123";

		assertFalse(Validate.checkIfANumber(portInvalid1));
		assertFalse(Validate.checkIfANumber(portInvalid2));
		assertFalse(Validate.checkIfANumber(portInvalid3));
		assertTrue(Validate.checkIfANumber(portValid1));
		assertTrue(Validate.checkIfANumber(portValid2));
		assertTrue(Validate.checkIfANumber(portValid3));
	}

	/**
	 * Test if the method to remove a message from datagram works
	 */
	@Test
	public void testRemoveMessage() {
		String ori1 = "3-ola";
		String esp1 = "ola";
		String ori2 = "7-benfica";
		String esp2 = "benfica";

		assertEquals(esp1, Validate.removeMessage(ori1));
		assertEquals(esp2, Validate.removeMessage(ori2));
	}

	/**
     * Test the encrypt method
     */
	@Test
	public void testEncrypt() {
		String pass1 = "secret";
		String hash1 = "4p4t4u0b4n0e410a4s0f0123x4u070f01701301c054o";

		String pass2 = "benfica";
		String hash2 = "0901c4m0d0d3o3u054l4w3s090a0133t4l500190123p";

		String pass3 = "it051S8X)33%y7s";
		String hash3 = "0f0184s0f3o0d3w3r403v040174u3u3s0w3q4r0c4u";

		assertEquals(Validate.encrypt(pass1.getBytes()), hash1);
		assertEquals(Validate.encrypt(pass2.getBytes()), hash2);
		assertEquals(Validate.encrypt(pass3.getBytes()), hash3);
	}
}
