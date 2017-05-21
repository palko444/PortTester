package portTester;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fileoperations {

    public static List<String> getServersFromFile(String file) throws IOException {
	List<String> servers = new ArrayList<String>();
	String line = null;
	BufferedReader br = null;

	br = new BufferedReader(new FileReader(file));
	while ((line = br.readLine()) != null) {
	    line.trim();
	    if (!line.startsWith("#") || !line.isEmpty()) {
		servers.add(line);
	    }
	}
	br.close();
	return servers;
    }
}
