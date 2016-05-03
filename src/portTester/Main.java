package portTester;

import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {

		String[] ports;
		String[] servers;
		String header;
		String line;
		HashMap<String, HashMap<Integer, String>> results;

		Main m = new Main();
		ParseArgs pr = new ParseArgs(args);
		pr.setOptions();

		ports = pr.getPorts();
		Arrays.sort(ports);
		header = m.getHeader(ports);
		servers = pr.getServers();

		results = m.runCheck(servers, ports);

		System.out.println(header);
		Iterator<String> it = results.keySet().iterator();
		while (it.hasNext()) {
			String str = it.next();
			line = (str + ", " + m.getPortsSorted(results.get(str)));
			System.out.println(line);
		}
	}

	private boolean IsPortOpen(String host, int port) {

		try {
			Socket ss = new Socket(host, port);
			ss.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	private HashMap<String, HashMap<Integer, String>> runCheck(String[] servers, String[] ports) {
		boolean status;
		HashMap<String, HashMap<Integer, String>> results = new HashMap<String, HashMap<Integer, String>>();

		for (String server : servers) {
			HashMap<Integer, String> pp = new HashMap<Integer, String>();
			results.put(server, pp);
			for (String port : ports) {
				int p = Integer.parseInt(port);
				status = IsPortOpen(server, p);
				String value = status ? "Open" : "Closed";
				pp.put(p, value);
			}
		}
		return results;
	}

	private String getPortsSorted(HashMap<Integer, String> map) {
		StringBuilder tline = new StringBuilder();
		String line;
		SortedMap<Integer, String> smap = new TreeMap<Integer, String>(map);
		Iterator<Integer> it = smap.keySet().iterator();
		String prefix = "";
		while (it.hasNext()) {
			int im = it.next();
			tline.append(prefix);
			prefix = ", ";
			tline.append(smap.get(im));
		}
		line = tline.toString();
		return line;
	}

	private String getHeader(String[] ports) {
		String header;
		StringBuilder hh = new StringBuilder();
		hh.append("Host, ");
		for (String port : ports) {
			hh.append("Port " + port + ", ");
		}
		header = hh.toString();
		return header;

	}
}
