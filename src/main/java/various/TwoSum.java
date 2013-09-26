package various;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TwoSum {

	private static Set<Integer> s = new HashSet<Integer>();
	private static List<Integer> l = new ArrayList<Integer>();
	
	private static boolean hasSum(int sum) {
		for (int x : l) {			
			int y = sum - x;
			
			if (x == y) continue;
			
			if (s.contains(y)) {
				System.out.println(sum);
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		FileReader f = new FileReader("hashintred.txt");
		BufferedReader br = new BufferedReader(f);
		
		String line = null;

		
		while ((line = br.readLine()) != null) {
			int i = Integer.parseInt(line);
			s.add(i);
			l.add(i);
		}

		int count = 0;
		for (int t = 60; t < 101; t++) {
			if (hasSum(t)) {
				count++;
			}
		}
		
		System.out.println(count);
		System.out.println(s.size());
		br.close();
	}

}
