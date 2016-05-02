package portTester;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ParseArgs {

	Options options = new Options();
	CommandLineParser parser;
	String[] args;

	public ParseArgs(String[] args) {
		this.args = args;
		setOptions();
	}

	public void setOptions() {
		char comma = ",".charAt(44);
		options.addOption(Option.builder("p")
								.longOpt("port")
								.hasArgs()
								.required()
								.valueSeparator(comma)
								.desc("Port(s) to scan, delimeted by \",\"")
								.build());
		options.addOption("s", "system", true, "System to scan");
		options.addOption("h", "help", false, "Print help");
		options.addOption(Option.builder("f")
								.longOpt("file")
								.desc("File of hosts")
								.build());
	}

	public void printHelp() {
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("Port Tester", options);

	}

	public void parseOptions() {
		parser = new DefaultParser();

		try {
			CommandLine cl = parser.parse(options, args);
			System.out.println("Ports: " + cl.getOptionValue("p"));
			System.out.println("System: " + cl.getOptionValue("s"));
		}
		catch (ParseException e) {
			printHelp();
//			e.printStackTrace();
		}

	}
}
