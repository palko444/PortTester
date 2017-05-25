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
	final Formatter formatter;

	public PortTest(Formatter formatter, int longestName, int longestPort, String server, int port, int timeout) {

		this.formatter = formatter;
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
			formatter.printOk(this.longestName, this.longestPort, this.server, this.port, "Open", "");
			return "Open";
		} catch (ConnectException e) {
			formatter.printFail(this.longestName, this.longestPort, this.server, this.port, "Closed", e.getMessage());
			return e.getMessage();
		} catch (SocketTimeoutException e) {
			formatter.printFail(this.longestName, this.longestPort, this.server, this.port, "Closed", e.getMessage());
			return e.getMessage();
		} catch (UnknownHostException e) {
			formatter.printFail(this.longestName, this.longestPort, this.server, this.port, "Closed", "Cannot resolve fqdn");
			return "Cannot resolve fqdn";
		} catch (NoRouteToHostException e) {
			formatter.printFail(this.longestName, this.longestPort, this.server, this.port, "Closed", e.getMessage());
			return e.getMessage();
		} catch (Exception e) {
			Log.normal(this.server + "\n");
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
