package portTester;

public class TableFormatter implements Formatter {

	private int longestName;
	private int longestPort;

	public TableFormatter(int longestName, int longestPort) {
		this.longestName = longestName;
		this.longestPort = longestPort;
	}

	@Override
	public void print(String serverName, int port, String portStatus, String issueMessage) {
		System.out.printf("%-" + this.longestName + "s %-" + this.longestPort + "d %-6s \n", serverName, port,
				portStatus);

	}

	@Override
	public void printHeader() {
		// TODO Auto-generated method stub
		System.out.printf("%-" + this.longestName + "s %-" + this.longestPort + "s %-6s %s\n", "Name", "Port", "Status",
				"Issue");
	}
}
