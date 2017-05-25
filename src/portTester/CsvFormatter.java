package portTester;

public class CsvFormatter implements Formatter {

	@Override
	public void print(String serverName, int port, String portStatus, String issueMessage) {
		System.out.printf("%s,%d,%s,%s\n", serverName, port, portStatus, issueMessage);
	}

	@Override
	public void printHeader() {
		System.out.printf("%s,%s,%s,%s\n", "Name", "Port", "Status", "Issue");
	}
}
