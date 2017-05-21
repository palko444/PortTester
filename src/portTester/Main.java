package portTester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import net.sourceforge.argparse4j.inf.Namespace;

public class Main {

    public static void realMain(String[] args) {
	try {
	    main(args);
	} catch (IOException e) {
	    Log.normal(e.getMessage());
	}
    }

    public static void main(String[] args) throws IOException {

	Namespace ns = ArgParser.parse(args);
	int timeout = ns.getInt("timeout");
	List<Integer> ports = ns.getList("port");
	Collections.sort(ports);
	int number = ns.getInt("number");
	final List<String> servers = getServers(ns);
	final int longestName = getLongestName(servers);
	final int longestPort = getLongestPort(ports);
	Log.printHeader(longestName, longestPort);
	// final List<Results> results = getResults(
	// execute(longestName, longestPort, number, servers, ports, timeout));
	getResults(execute(longestName, longestPort, number, servers, ports, timeout));

	// for (Results result : results) {
	// Log.normalFormatted(longestName, longestPort, result.serverName, result.port,
	// result.portStatus, result.issueMessage);
	// }

    }

    public static int getLongestPort(List<Integer> ports) {
	int lenght = 0;
	for (int port : ports) {
	    String portString = port + "";
	    lenght = portString.length() > lenght ? portString.length() : lenght;
	}
	return lenght < 4 ? 4 : lenght;

    }

    public static int getLongestName(List<String> servers) {
	int lenght = 0;
	for (String server : servers) {
	    lenght = server.length() > lenght ? server.length() : lenght;
	}
	return lenght;
    }

    public static List<Results> getResults(
	    HashMap<String, HashMap<Integer, Future<String>>> resultsHolder) {

	List<Results> results = new ArrayList<Results>();
	for (Entry<String, HashMap<Integer, Future<String>>> entryName : resultsHolder.entrySet()) {
	    String serverName = entryName.getKey();
	    for (Entry<Integer, Future<String>> entryFuture : entryName.getValue().entrySet()) {
		int port = entryFuture.getKey();

		try {
		    String status = entryFuture.getValue().get();
		    if (status == null) {
			results.add(new Results(serverName, port, "Closed", ""));
		    } else if (status.contentEquals("Open")) {
			results.add(new Results(serverName, port, "Open", ""));
		    } else {
			results.add(new Results(serverName, port, "Closed", status));
		    }
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (ExecutionException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	return results;
    }

    public static HashMap<String, HashMap<Integer, Future<String>>> execute(int longestname,
	    int longestPort, int number, List<String> servers, List<Integer> ports, int timeout) {
	ExecutorService executor = Executors.newFixedThreadPool(number);
	HashMap<String, HashMap<Integer, Future<String>>> resultsHolder = new HashMap<String, HashMap<Integer, Future<String>>>();
	for (String server : servers) {
	    HashMap<Integer, Future<String>> portFuture = new HashMap<Integer, Future<String>>();
	    for (int port : ports) {
		Callable<String> lala = new PortTest(longestname, longestPort, server, port,
			timeout);
		Future<String> future = executor.submit(lala);
		portFuture.put(port, future);
		resultsHolder.put(server, portFuture);
	    }
	}
	executor.shutdown();
	return resultsHolder;
    }

    public static List<String> getServers(Namespace ns) throws IOException {
	if (ns.getString("file").isEmpty()) {
	    return ns.getList("servers");
	} else {
	    return Fileoperations.getServersFromFile(ns.getString("file"));
	}

    }
}
