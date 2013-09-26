package various;
import java.util.Comparator;
import java.util.PriorityQueue;


public class PQ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PriorityQueue<Integer> pqLow = 
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
		
		PriorityQueue<Integer> pqHigh = new PriorityQueue<Integer>(11);

		pqLow.offer(1);
		pqLow.offer(9);
		pqLow.offer(4);
		pqLow.offer(37);
		
		pqHigh.offer(1);
		pqHigh.offer(9);
		pqHigh.offer(4);
		pqHigh.offer(37);		
		
		System.out.println(pqLow);
		System.out.println(pqHigh);
		
		
	}

}
