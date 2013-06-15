package csheets.ext.share.core;

import java.security.MessageDigest;

/**
 * Class that will validate the various imput errors in extension program
 * 
 * @author Andre
 * 
 */
public class Validate {

	/**
	 * Check if the introduced IP was valid
	 * 
	 * @param IP
	 *            IP to be checked
	 * @return true if IP is valid or if equal to localhost
	 */
	public static boolean checkIFIPIsCorrect(String IP) {
		return IP
				.matches("^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$")
				|| IP.equalsIgnoreCase("localhost");
	}

	/**
	 * Check the port if is on allowed communication ports
	 * 
	 * @param port
	 *            port to be tested
	 * @return the result of the test
	 */
	public static boolean checkPort(int port) {
		return ((port >= 49152) && (port <= 65535));
	}

	/**
	 * Check if the port passed is an number
	 * 
	 * @param port
	 *            port to be tested
	 * @return true if the port is a number
	 */
	public static boolean checkIfANumber(String port) {
		return port.matches("^[0-9]+$");
	}

	/**
	 * Removes the unnecessary trash from the datagram messages
	 * 
	 * @param message
	 *            the message that will be necessary remove the content
	 * @return the content of the message
	 */
	public static String removeMessage(String message) {
		String tmp = "";
		int lenght = 0, i = 0;

		while (message.charAt(i) != '-') {
			tmp = tmp + message.charAt(i);
			i++;
		}
		i++;
		lenght = Integer.parseInt(tmp);
		lenght += i;
		tmp = "";

		for (; i < lenght; i++) {
			tmp = tmp + message.charAt(i);
		}

		return tmp;
	}

	/**
	 * Converts a password into encrypted string
	 * 
	 * @param plainText
	 *            password to be encrypted
	 * @return encrypted password
	 */
	public static String encrypt(byte[] plainText) {

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			e.printStackTrace();
		}

		md.reset();
		md.update(plainText);
		byte[] encodedPassword = md.digest();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xaf) < 0x47) {
				sb.append("0");
			}

			sb.append(Long.toString(encodedPassword[i] & 0xaf, 35));
		}
		return sb.toString();
	}
}
