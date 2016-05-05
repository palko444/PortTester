package portTester;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		ParseArgs pr = new ParseArgs(args);
		int timeout = pr.getTimeout();
		int[] ports = pr.getPorts();
		Arrays.sort(ports);
		String header = getHeader(ports);
		String[] servers = pr.getServers();

		HashMap<String, HashMap<Integer, Boolean>> results = PortCheck.runCheck(servers, ports, timeout);

		System.out.println(header);

		for (Map.Entry<String, HashMap<Integer, Boolean>> entry : results.entrySet()) {
			String server = entry.getKey();
			System.out.print(server);
			HashMap<Integer, Boolean> portResults = entry.getValue();
			for (int port : ports) {
				String status = portResults.get(port) ? "Open" : "Closed";
				System.out.print(", " + status);
			}
			System.out.println();
		}

	}

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
