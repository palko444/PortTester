package portTester;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
//		HashMap<String, HashMap<Integer, Boolean>> results = new HashMap<String, HashMap<Integer, Boolean>>();

		PortCheck[] workers = new PortCheck[servers.length * ports.length];
		ExecutorService executor = Executors.newFixedThreadPool(30);
		int i = 0;
		for (String server : servers) {
//			HashMap<Integer, Boolean> pp = new HashMap<Integer, Boolean>();
//			results.put(server, pp);
			for (int port : ports) {
				Runnable pc = workers[i] = new PortCheck(server, port, timeout);
				i++;
				executor.execute(pc);
			}
		}
		executor.shutdown();
		HashMap<String, HashMap<Integer, Boolean>> results = new HashMap<String, HashMap<Integer, Boolean>>();
		for (PortCheck pcc : workers) {
			results.get(pcc.host).get(pcc.port).
		}

		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}
		return results;
	}

	public static boolean IsPortOpen(String host, int port, int timeout) {

		PortCheck pc = new PortCheck(host, port, timeout);
		
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
//		IsPortOpen(this.host, this.port, this.timeout);
		this.result = IsPortOpen(host, port, timeout);
		// TODO Auto-generated method stub

	}

}
