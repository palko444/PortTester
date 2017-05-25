package portTester;

public interface Formatter {
	void printOk(int longestName, int longestPort, String serverName, int port, String portStatus, String issueMessage);

	void printFail(int longestName, int longestPort, String serverName, int port, String portStatus,
			String issueMessage);

	void printHeader(int longestName, int longestPort);

}
