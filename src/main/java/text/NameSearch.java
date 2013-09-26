package text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NameSearch {

	private static List<String> names = new ArrayList<String>();
	private static Map<String, List<Long>> nameOccurrences =
			new HashMap<String, List<Long>>();

	public static void loadNames(String path) throws IOException {
		BufferedReader br = null;

		br = new BufferedReader(new FileReader(path));
		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.length() == 0) continue;
			names.add(line);
			nameOccurrences.put(line, new ArrayList<Long>());
		}
		if (br != null) br.close();
	}

	public static void match(String name, String text, long lineNumber) {
		if (text.indexOf(name) != -1)
			nameOccurrences.get(name).add(lineNumber);
	}

	public static void findNameOccurrences(String path) {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;

			long lineNumber = 0;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				if (line.length() == 0) continue;
				for (String name: names)
					match(name, line, lineNumber);
			}

			if (br != null) br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printOccurrences() {
		StringBuilder sb = null;
		for (Entry<String, List<Long>> entry: nameOccurrences.entrySet()) {
			sb = new StringBuilder();
			sb.append(entry.getKey()).append(": ");
			
			for (Long i: entry.getValue()) 
				sb.append(i).append(", ");
			
			if (sb.charAt(sb.length() - 2) == ',')
				sb.delete(sb.length() - 2, sb.length());
			
			System.out.println(sb.toString());
		}		
	}
	
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage: NameSearch textfile namefile");
			return;
		}

		loadNames(args[1]);
		findNameOccurrences(args[0]);
		
		printOccurrences();
	}
}
