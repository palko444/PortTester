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
	CommandLine cl;

	public ParseArgs(String[] args) {
		this.args = args;
	}

	public void setOptions() {
		options.addOption(Option.builder("p").longOpt("port").hasArg(true).hasArgs().required(true).desc("Port to scan").build());
		options.addOption("s", "system", true, "System to scan");
		options.addOption(Option.builder("s").longOpt("system").hasArgs().desc("System to scan").build());
		options.addOption(Option.builder("h").longOpt("help").required(false).desc("Print help").build());
		options.addOption(Option.builder("f").longOpt("file").hasArg().desc("File of hosts").build());
		parseOptions();
	}

	public void printHelp() {
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("Port Tester", options);
		System.exit(1);
	}

	public void parseOptions() {
		parser = new DefaultParser();

		try {
			cl = parser.parse(options, args);
			if (cl.hasOption("h")) {
				printHelp();
			}

			if ((cl.hasOption("s")) && (cl.hasOption("f"))) {
				printHelp();
			}
		}
		catch (ParseException e) {
			printHelp();
			// e.printStackTrace();
		}
	}

	public CommandLine getParsedArgs() {
		return cl;
	}

	public String[] getPorts() {
		return cl.getOptionValues("p");
	}

	public String[] getServers() {
		if (cl.hasOption("s")) {
			return cl.getOptionValues("s");
		} else {
			OpenFile ff = new OpenFile(cl.getOptionValue("f"));
			return ff.getOMs();
		}
	}
}
