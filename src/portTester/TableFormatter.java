package portTester;

public class TableFormatter implements Formatter {

	@Override
	public void printOk(int longestName, int longestPort, String serverName, int port, String portStatus,
			String issueMessage) {
		System.out.printf("%-" + longestName + "s %-" + longestPort + "d %-6s \n", serverName, port, portStatus);

	}

	@Override
	public void printFail(int longestName, int longestPort, String serverName, int port, String portStatus,
			String issueMessage) {
		System.out.printf("%-" + longestName + "s %-" + longestPort + "d %-6s %s\n", serverName, port, portStatus,
				issueMessage);

	}

	@Override
	public void printHeader(int longestName, int longestPort){
		// TODO Auto-generated method stub
		System.out.printf("%-" + longestName + "s %-" + longestPort + "s %-6s %s\n", "Name", "Port", "Status", "Issue");

	}
}
