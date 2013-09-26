package various;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;


public class MedMaint {
	
	private static PriorityQueue<Integer> pqLow = 
			new PriorityQueue<Integer>(11, new Comparator<Integer>() {
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
	
	private static PriorityQueue<Integer> pqHigh = new PriorityQueue<Integer>(11);	
	
	private static void rebalance() {
		if (pqLow.size() == pqHigh.size()) return;
		
		if (pqLow.size() > pqHigh.size()) {
			pqHigh.offer(pqLow.remove());
			return;
		}
		
		pqLow.offer(pqHigh.remove());
	}
	
	private static int findMedian(boolean odd) {
		if (!odd) {
			return pqLow.peek();
		}
		
		if (pqLow.size() > pqHigh.size()) return pqLow.peek();
		return pqHigh.peek();
	}
	
	public static void main(String[] args) throws IOException {
		
		FileReader f = new FileReader("foo.txt");
		BufferedReader br = new BufferedReader(f);
		
		int medianSum = 0;
		int i = Integer.parseInt(br.readLine());
		pqLow.offer(i);
		boolean odd = true;		
		medianSum += findMedian(odd);
		
		String line = null;
		while ((line = br.readLine()) != null) {
			i = Integer.parseInt(line);
			
			if (i < pqLow.peek()) {
				pqLow.offer(i);
			}
			else {
				pqHigh.offer(i);
			}
			
			odd = !odd;
			if (!odd) {
				rebalance();
			}
			
			medianSum += findMedian(odd);
			
			
		}
		System.out.println(medianSum % 10000);	

		br.close();
	}
}
