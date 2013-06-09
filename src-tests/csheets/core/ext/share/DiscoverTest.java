package csheets.core.ext.share;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.util.List;

import org.junit.Test;

import csheets.ext.share.core.*;

/**
 * Tests the discover function
 * 
 * @author Andre
 * 
 */
public class DiscoverTest {

    /**
     * Test the discover function of the application
     */
    @Test
    public void testDiscover() {
	try {
	    // local ip
	    InetAddress ip = InetAddress.getLocalHost();
	    int port = 5000;

	    // start the server broadcast messages
	    ServerDiscover.getInstance().findClients(port);
	    List<Connections> list = ClientDiscover.getInstance().findServers();
	    // get the first resulted
	    Connections resulted = list.get(0);

	    Thread.sleep(100);

	    assertEquals(ip, resulted.getIP());
	    assertEquals(port, resulted.getPort());
	} catch (Exception e) {
	    fail("Exception Error!");
	}
    }

}
