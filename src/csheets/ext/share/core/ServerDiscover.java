package csheets.ext.share.core;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.*;

/**
 * Class that implements a server side of the discover through network
 * 
 * @author Andre
 * 
 */
public class ServerDiscover extends Observable implements Runnable {
	/** Instance of server discover */
	private static ServerDiscover instance = null;
	/** Port of the connection */
	private int port;
	/** the observer class */
	private Observer observer;

	/**
	 * Creates a new client discover
	 */
	private ServerDiscover() {
	}

	/**
	 * The instance of running server discover
	 * 
	 * @return the instance of the class
	 */
	public static ServerDiscover getInstance() {
		if (instance == null) {
			instance = new ServerDiscover();
		}
		return instance;
	}

	/**
	 * Broadcasts a message throw network
	 * 
	 * @param port
	 *            the port of the connection
	 * @param observer
	 *            the observer class
	 */
	public void findClients(int port, Observer observer) {
		this.port = port;
		this.observer = observer;
		addObserver(observer);
		Thread thr = new Thread(getInstance());
		thr.start();
	}

	/**
	 * Broadcasts a message throw network
	 * 
	 */
	@SuppressWarnings("resource")
	private void broadcast() throws IOException {
		MulticastSocket serverSocket;
		serverSocket = new MulticastSocket(9876);

		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		int i = 1;
		while (i > 0) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);

			serverSocket.receive(receivePacket);
			String testReceive = new String(receivePacket.getData());
			testReceive = Validate.removeMessage(testReceive);

			if (testReceive.equals("send me connection")) {
				InetAddress IPAddress = receivePacket.getAddress();
				int portPacket = receivePacket.getPort();

				int portSend = port;

				String portConnect = portSend + "";
				int portLenght = portConnect.length();
				portConnect = portLenght + "-" + portSend;

				sendData = portConnect.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, IPAddress, portPacket);
				serverSocket.send(sendPacket);
			}
		}
		// serverSocket.close();

	}

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		try {
			addObserver(observer);
			broadcast();
		} catch (IOException e) {
			Logger.getLogger(ServerDiscover.class.getName()).log(Level.SEVERE,
					null, e);
			setChanged();
			notifyObservers();
			clearChanged();
		}
	}
}
