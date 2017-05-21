package portTester;

public class Log {

    public static void normal(String message) {
	System.out.print(message);
    }

    public static void info(String message) {
	System.out.print(message);
    }

    public static void normalFormatted(int longestName, int longestPort, String serverName,
	    int port, String portStatus, String issueMessage) {
	System.out.printf("%-" + longestName + "s %-" + longestPort + "d %-6s %s\n", serverName,
		port, portStatus, issueMessage);
    }

    public static void printHeader(int longestName, int longestPort) {
	System.out.printf("%-" + longestName + "s %-" + longestPort + "s %-6s %s\n", "Name", "Port",
		"Status", "Issue");
    }
}
