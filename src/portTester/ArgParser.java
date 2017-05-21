package portTester;

import java.util.ArrayList;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.MutuallyExclusiveGroup;
import net.sourceforge.argparse4j.inf.Namespace;

public class ArgParser {

    public static Namespace parse(String[] args) {
	ArgumentParser parser = ArgumentParsers.newArgumentParser("PortTester")
		.description("Tool to test if port is open").version("00.03");
	parser.addArgument("-V", "--version").help("print version of PortTester")
		.action(Arguments.version());

	parser.addArgument("-n", "--number").help("Number of parallel executions, default value 20")
		.type(Integer.class).setDefault(new Integer(20));
	parser.addArgument("-t", "--timeout").help("Timeout for port check, default 5s")
		.type(Integer.class).setDefault(new Integer(5));
	parser.addArgument("-p", "--port").help("Test defined port(s)").type(Integer.class).nargs("+")
		.setDefault(new ArrayList<Integer>()).required(true);
	parser.addArgument("-c", "--csv").help("Print csv output").action(Arguments.storeTrue())
		.setDefault(false);

	MutuallyExclusiveGroup eGroup = parser.addMutuallyExclusiveGroup().required(true);
	eGroup.addArgument("-S", "--servers").help("Test defined server(s)").type(String.class)
		.nargs("+").setDefault(new ArrayList<String>());
	eGroup.addArgument("-F", "--file").help("Test servers from file").type(String.class)
		.setDefault("");

	try {
	    return parser.parseArgs(args);
	} catch (ArgumentParserException e) {
	    e.getParser().printHelp();
	    System.exit(1);
	    return null;
	}
    }
}
