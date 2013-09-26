package various;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hashing {

	public static int[] loadData(String path) {
		List<Integer> ints = new ArrayList<Integer>();
		
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) != null)
				ints.add(Integer.parseInt(line));
			if (br != null) br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] a = new int[ints.size()];
		int i = 0;
		for (Integer j: ints) a[i++] = j;
		return a;
	}
	
	public static int twoSum(int[] a) {
		Set<Integer> s = new HashSet<Integer>();
		for (int i: a) s.add(i);
		int twosum = 0;
		for (int t = 2500; t <= 4000; t++) {
			for (int x: a) {
				if (x > 4000) continue;
				if (t - x == x) continue;
				if (s.contains(t - x)) {
					twosum++;
					break;
				}
			}
		}
		return twosum;
	}
	
	public static void main(String[] args) {
		int[] a = loadData("HashInt.txt");
		System.out.println(twoSum(a));
		

	}

}
