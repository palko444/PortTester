package portTester;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		ParseArgs pr = new ParseArgs(args);
		int timeout = pr.getTimeout();
		int [] ports = pr.getPorts();
		Arrays.sort(ports);
		String header = getHeader(ports);
		String[] servers = pr.getServers();

		HashMap<String, HashMap<Integer, Boolean>> results = PortCheck.runCheck(servers, ports, timeout);

		System.out.println(header);
//		Iterator<String> it = results.keySet().iterator();
//		while (it.hasNext()) {
//			String str = it.next();
//			String line = (str + ", " + getPortsSorted(results.get(str)));
//			System.out.println(line);
//		}
		
		
		for (Map.Entry<String, HashMap<Integer, Boolean>> entry : results.entrySet()) {
		    String server = entry.getKey();
		    HashMap<Integer, Boolean> portResults = entry.getValue();
		    for (int port : ports) {
		    	portResults.get(port);
		    }
		}
		
	}

	// private boolean IsPortOpen(String host, int port, int timeout) {
	//
	// try {
	// Socket ss = new Socket();
	// ss.connect(new InetSocketAddress(host, port), timeout);
	// ss.close();
	// return true;
	// }
	// catch (Exception e) {
	// return false;
	// }
	// }

//	private static HashMap<String, HashMap<Integer, Boolean>> runCheck(String[] servers, int[] ports, int timeout) {
//		boolean status;
//		HashMap<String, HashMap<Integer, Boolean>> results = new HashMap<String, HashMap<Integer, Boolean>>();
//		PortCheck pc;
//
//		ExecutorService executor = Executors.newFixedThreadPool(30);
//		for (String server : servers) {
//			HashMap<Integer, Boolean> pp = new HashMap<Integer, Boolean>();
//			results.put(server, pp);
//			for (int port : ports) {
//				Runnable worker = new PortCheck(server, port, timeout);
//				// pc = new PortCheck(server, p, timeout);
//				// status = IsPortOpen(server, p, timeout);
//				executor.execute(worker);
////				String value = status ? "Open" : "Closed";
//				pp.put(port, status);
//			}
//		}
//		executor.shutdown();
//		try {
//			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//		}
//		return results;
//	}

//	private static String getPortsSorted(HashMap<Integer, String> map) {
//		StringBuilder tline = new StringBuilder();
//		String line;
//		SortedMap<Integer, String> smap = new TreeMap<Integer, String>(map);
//		Iterator<Integer> it = smap.keySet().iterator();
//		String prefix = "";
//		while (it.hasNext()) {
//			int im = it.next();
//			tline.append(prefix);
//			prefix = ", ";
//			tline.append(smap.get(im));
//		}
//		line = tline.toString();
//		return line;
//	}

	private static String getHeader(int[] ports) {
		String header;
		StringBuilder hh = new StringBuilder();
		hh.append("Host, ");
		for (int port : ports) {
			hh.append("Port " + port + ", ");
		}
		header = hh.toString();
		return header;

	}
}
