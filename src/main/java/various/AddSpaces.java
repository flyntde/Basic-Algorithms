package various;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AddSpaces {

	private static Set<String> dictionary;
	
	public static List<String> splitIntoWords(String s) {
		List<String> words = new ArrayList<String>();
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				String substr = s.substring(i, j);
				if (dictionary.contains(substr)) {
					words.add(substr);
				}
			}
		}
		return words;
	}
	
	/*
	 * input: several words compressed into one string with no spaces
	 * output: all possible collections of words that match input string,
	 * such that the words are in the exact same order as the original 
	 * sentence.
	 */
	public static List<List<String>> addSpacesToSentence(String sentence) {
		List<String> words = splitIntoWords(sentence);
		List<List<String>> combos = combinations(words);
		return filter(combos, sentence);		
	}
	
	public static List<List<String>> filter(List<List<String>> combos, String orig) {
		List<List<String>> matchingSentences = new ArrayList<List<String>>();
		for (List<String> ls: combos) {
			StringBuilder sb = new StringBuilder();
			for (String s: ls) {
				sb.append(s);
			}
			if (orig.equals(sb.toString())) matchingSentences.add(ls);
		}
		return matchingSentences;
	}
	
	public static List<List<String>> combinations(List<String> words) {
		if (words.isEmpty()) {
			List<List<String>> lls = new ArrayList<List<String>>();
			List<String> ls = new ArrayList<String>();
			ls.add("");
			lls.add(ls);
			return lls;
		}
		
		List<String> copy = new ArrayList<String>(words);
		
		String firstWord = copy.remove(0);
		
		List<List<String>> subcombos = combinations(copy);
		
		// for each List<String> in subcombos, create new list with firstWord
		// at front of list
		List<List<String>> combos = new ArrayList<List<String>>();
		for (List<String> ws: subcombos) {
			List<String> wordList = new ArrayList<String>();
			wordList.add(firstWord);
			wordList.addAll(ws);
			combos.add(wordList);
		}
		
		// this adds all of the sentence possibilities without firstWord
		combos.addAll(subcombos);
		return combos;
	}
	
	
	public static Set<String> loadWords() {
		Set<String> words = new HashSet<String>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("linuxwords.txt"));
			String word;
			while ((word = br.readLine()) != null) {
				words.add(word.toLowerCase());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return words;
	}
	
	public static void displayWords(List<String> words) {
		StringBuilder sb = new StringBuilder();
		for (String word: words) {
			sb.append(word).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static void displayMatching(List<List<String>> matching) {
		for (List<String> ws: matching) displayWords(ws);
	}
	
	public static void main(String[] args) {		
		dictionary = loadWords();
		
		AddSpaces.displayMatching(AddSpaces.addSpacesToSentence("carrottreerunfastdog"));
		
		AddSpaces.displayMatching(AddSpaces.addSpacesToSentence("haveahappyfathersday"));
		
		AddSpaces.displayMatching(AddSpaces.addSpacesToSentence("winstonlikestobarksoftlynow"));
	}
}
