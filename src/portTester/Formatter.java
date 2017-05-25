package portTester;

public interface Formatter {

	void print(String serverName, int port, String portStatus, String issueMessage);

	void printHeader();

}
