package csheets.ext.share.core;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.*;

/**
 * The client discover class that will find servers through network
 * 
 * @author Andre
 * 
 */
public class ClientDiscover extends Observable implements Runnable {
	/** list of connections */
	List<Connections> connections;
	/** instance of client discover */
	private static ClientDiscover instance;
	/** the observer class */
	private Observer observer;

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
	 * @param observer
	 * 
	 * @return a list with servers with sharing open
	 */
	public List<Connections> findServers(Observer observer) {
		connections.clear();
		this.observer = observer;
		addObserver(observer);
		try {
			Thread thr = new Thread(getInstance());
			thr.start();
			// wait 3 seconds
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Logger.getLogger(ClientDiscover.class.getName()).log(Level.SEVERE,
					null, e);
			setChanged();
			notifyObservers();
			clearChanged();
		}
		return connections;
	}

	/**
	 * Search a servers through network
	 * 
	 * @throws IOException
	 */
	private void search() throws IOException {
		MulticastSocket clientSocket;
		clientSocket = new MulticastSocket();
		clientSocket.setBroadcast(true);
		InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
		connections = new ArrayList<Connections>();
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		int i = 5000;
		String sentence = "18-send me connection";
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sentence.length(), IPAddress, 9876);
		clientSocket.send(sendPacket);
		clientSocket.setSoTimeout(3000);
		while (i > 0) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			modifiedSentence = Validate.removeMessage(modifiedSentence);
			int port = Integer.parseInt(modifiedSentence);
			Connections con = new Connections(port, receivePacket.getAddress());
			if (!connections.contains(con)) {
				connections.add(con);
			}
			i--;
		}
		clientSocket.close();
	}

	@Override
	public void run() {
		addObserver(observer);
		try {
			search();
		} catch (SocketTimeoutException e) {

		}

		catch (IOException e) {
			Logger.getLogger(ClientDiscover.class.getName()).log(Level.SEVERE,
					null, e);
			setChanged();
			notifyObservers();
			clearChanged();
		}
	}
}
