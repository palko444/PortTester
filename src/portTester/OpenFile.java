package portTester;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile {
	String file;

	public OpenFile(String file) {
		this.file = file;
	}

	public String[] getOMs() {
		String[] oms;
		StringBuilder om = new StringBuilder();
		String line = null;
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				om.append(line);
				om.append("\n");
			}
			br.close();
		} catch (FileNotFoundException fe) {
			System.out.println("File does not exist " + file);
			System.exit(1);
		} catch (IOException ie) {
			System.out.println("There is issue with reading a file " + file);
			System.exit(1);

		}
		oms = om.toString().split("\n");
		return oms;
	}

}
