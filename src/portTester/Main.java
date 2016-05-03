package portTester;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.cli.CommandLine;

public class Main {

	public static void main(String[] args) {

		String[] ports;
		String[] servers;
		CommandLine cl;
		HashMap<String, HashMap<Integer, String>> results;

		Main m = new Main();
		ParseArgs pr = new ParseArgs(args);
		pr.setOptions();

		cl = pr.getParsedArgs();
		ports = cl.getOptionValues("p");
		servers = cl.getOptionValues("s");

		results = m.runCheck(servers, ports);
		
		Iterator<String> it = results.keySet().iterator();
		while(it.hasNext()){
			String str = it.next();
			System.out.println(str + results.get(str));
		}
	}

	public boolean IsPortOpen(String host, int port) {

		try {
			Socket ss = new Socket(host, port);
			ss.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public HashMap<String, HashMap<Integer, String>> runCheck(String[] servers, String[] ports) {
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
}
