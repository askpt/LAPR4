package csheets.ext.share.core;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * The client discover class that will find servers through network
 * 
 * @author Andre
 * 
 */
public class ClientDiscover implements Runnable {
	/** list of connections */
	List<Connections> connections;
	/** instance of client discover */
	private static ClientDiscover instance;

	/**
	 * Creates a new client discover
	 */
	private ClientDiscover() {
		connections = new ArrayList<Connections>();
	}

	/**
	 * The instance of running client discover
	 * 
	 * @return the instance of the class
	 */
	public static ClientDiscover getInstance() {
		if (instance == null) {
			instance = new ClientDiscover();
		}
		return instance;
	}

	/**
	 * Find servers through network
	 * 
	 * @return a list with servers with sharing open
	 */
	public List<Connections> findServers() {
		try {
			connections.clear();
			Thread thr = new Thread(getInstance());
			thr.start();
			// wait 3 seconds
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		return connections;
	}

	/**
	 * Search a servers through network
	 */
	private void search() {
		try {
			DatagramSocket clientSocket;
			clientSocket = new DatagramSocket();
			clientSocket.setBroadcast(true);
			InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
			connections = new ArrayList<Connections>();
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			int i = 5000;
			while (i > 0) {
				String sentence = "18-send me connection";
				sendData = sentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sentence.length(), IPAddress, 9876);
				clientSocket.send(sendPacket);
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());
				modifiedSentence = Validate.removeMessage(modifiedSentence);
				int port = Integer.parseInt(modifiedSentence);
				Connections con = new Connections(port,
						receivePacket.getAddress());
				if (!connections.contains(con)) {
					connections.add(con);
				}
				i--;
			}
			clientSocket.close();
		} catch (IOException e) {
		}

	}

	/**
	 * Running thread
	 */
	@Override
	public void run() {
		search();
	}
}
