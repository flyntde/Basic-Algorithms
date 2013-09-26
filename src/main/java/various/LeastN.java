package various;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LeastN {

	public static int[] loadFile(String path) throws IOException {
		BufferedReader br = null;
		int[] a = new int[100000];
		br = new BufferedReader(new FileReader(path));
		String line = null;
		int i = 0;
		while((line = br.readLine()) != null) {
			a[i++] = Integer.parseInt(line);
		}
				
		if (br != null) br.close();
		return a;
	}
	
	public static List<Integer> findLeast(int[] a, int n) {
		
		PriorityQueue<Integer> least = 
				new PriorityQueue<Integer>(n, new Comparator<Integer>() {
					public int compare(Integer x, Integer y) { 
						if (x.equals(y)) { 
							return 0;
						}
						if (x < y) {
							return 1;
						}
						return -1;
					}
				});
		
		for (Integer i: a) {
			if (least.size() < n) {
				least.offer(i);
				continue;
			}
			
			if (i < least.peek()) {
				least.poll();
				least.offer(i);
			}
		}

		return new ArrayList<Integer>(least);
	}
	
	
	public static void main(String[] args) throws IOException {
		int[] a = loadFile("IntegerArray.txt");
		
		List<Integer> ls = findLeast(a, 1000);
		Collections.sort(ls);
		for (Integer i: ls)
			System.out.println(i);

	}

}
