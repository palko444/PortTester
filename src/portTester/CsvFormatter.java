package portTester;

public class CsvFormatter implements Formatter {

	@Override
	public void printOk(int longestName, int longestPort, String serverName, int port, String portStatus,
			String issueMessage) {
		System.out.printf("%s,%d,%s\n", serverName, port, portStatus);

	}

	@Override
	public void printFail(int longestName, int longestPort, String serverName, int port, String portStatus,
			String issueMessage) {
		System.out.printf("%s,%d,%s,%s\n", serverName, port, portStatus, issueMessage);

	}

	@Override
	public void printHeader(int longestName, int longestPort) {
		System.out.printf("%s,%s,%s,%s\n", "Name", "Port", "Status", "Issue");

	}
}
