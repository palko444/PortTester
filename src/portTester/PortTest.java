package portTester;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class PortTest implements Callable<String> {

    final String server;
    final int port;
    final int timeout;
    final int longestName;
    final int longestPort;

    public PortTest(int longestName, int longestPort, String server, int port, int timeout) {

	this.server = server;
	this.port = port;
	this.timeout = timeout;
	this.longestName = longestName;
	this.longestPort = longestPort;

    }

    @Override
    public String call() {
	Socket ss = new Socket();
	try {
	    ss.connect(new InetSocketAddress(this.server, this.port), this.timeout);
	    ss.close();
	    Log.normalFormatted(this.longestName, this.longestPort, this.server, this.port, "Open",
		    "");
	    return "Open";
	} catch (ConnectException e) {
	    Log.normalFormatted(this.longestName, this.longestPort, this.server, this.port,
		    "Closed", e.getMessage());
	    return e.getMessage();
	} catch (SocketTimeoutException e) {
	    Log.normalFormatted(this.longestName, this.longestPort, this.server, this.port,
		    "Closed", e.getMessage());
	    return e.getMessage();
	} catch (UnknownHostException e) {
	    Log.normalFormatted(this.longestName, this.longestPort, this.server, this.port,
		    "Closed", "Cannot resolve fqdn");
	    return "Cannot resolve fqdn";
	} catch (NoRouteToHostException e) {
	    Log.normalFormatted(this.longestName, this.longestPort, this.server, this.port,
		    "Closed", e.getMessage());
	    return e.getMessage();
	    // } catch (IOException e) {
	    // return e.getMessage();
	} catch (Exception e) {
	    Log.normal(this.server + "\n");
	    e.printStackTrace();
	    return e.getMessage();
	}
    }
}
