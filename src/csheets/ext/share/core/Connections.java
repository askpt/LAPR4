package csheets.ext.share.core;

import java.net.InetAddress;

/**
 * Class with IP and port, that will serve the server and client discover
 * 
 * @author Andre
 * 
 */
public class Connections {

	/** port of the server */
	private final int port;
	/** IP of the server */
	private final InetAddress IP;

	/**
	 * Create a new connection class
	 * 
	 * @param port
	 *            por of the server
	 * @param inetAddress
	 *            ip of the server
	 */
	public Connections(int port, InetAddress ip) {
		this.port = port;
		this.IP = ip;
	}

	/**
	 * Get the ip address of the connection
	 * 
	 * @return the ip address
	 */
	public InetAddress getIP() {
		return IP;
	}

	/**
	 * Get the port of the connection
	 * 
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return IP + ":" + port;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Connections) {
			return (IP.equals(((Connections) obj).IP) && (port == ((Connections) obj).port));
		}
		return false;
	}
}
