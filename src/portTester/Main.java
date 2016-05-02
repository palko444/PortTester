package portTester;

import java.net.Socket;

public class Main {

	public static void main(String[] args) {

		Main m = new Main();
		ParseArgs pr = new ParseArgs(args);
		pr.parseOptions();
		System.out.println(m.IsPortOpen("localhost", 22));
	}

	public boolean IsPortOpen(String host, int port) {

		try {
			Socket ss = new Socket(host, port);
			ss.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}
