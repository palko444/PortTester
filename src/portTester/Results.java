package portTester;

public class Results {
    final String serverName;
    final int port;
    final String portStatus;
    final String issueMessage;

    public Results(String serverName, int port, String portStatus, String issueMessage) {
	this.serverName = serverName;
	this.port = port;
	this.portStatus = portStatus;
	this.issueMessage = issueMessage;
    }

}
