package portTester;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortCheck implements Runnable {

	String host;
	int port;
	int timeout;
	boolean result = false;

	public PortCheck(String host, int port, int timeout) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
	}

	public static HashMap<String, HashMap<Integer, Boolean>> runCheck(String[] servers, int[] ports, int timeout) {

		PortCheck[] workers = new PortCheck[servers.length * ports.length];
		ExecutorService executor = Executors.newFixedThreadPool(30);
		int i = 0;
		for (String server : servers) {
			for (int port : ports) {
				Runnable pc = workers[i] = new PortCheck(server, port, timeout);
				i++;
				executor.execute(pc);
			}
		}
		executor.shutdown();
		
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}

		HashMap<String, HashMap<Integer, Boolean>> results = new HashMap<String, HashMap<Integer, Boolean>>();
		for (PortCheck pcc : workers) {
			if (!results.containsKey(pcc.host)) {
				HashMap<Integer, Boolean> innerMap = new HashMap<Integer, Boolean>();
				results.put(pcc.host, innerMap);
			}
			HashMap<Integer, Boolean> innerMap = results.get(pcc.host);
			innerMap.put(pcc.port, pcc.result);
		}
		return results;
	}

	public static boolean IsPortOpen(String host, int port, int timeout) {

//		PortCheck pc = new PortCheck(host, port, timeout);

		try {
			Socket ss = new Socket();
			ss.connect(new InetSocketAddress(host, port), timeout);
			ss.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void run() {
		// IsPortOpen(this.host, this.port, this.timeout);
		this.result = IsPortOpen(host, port, timeout);
		// TODO Auto-generated method stub

	}

}
