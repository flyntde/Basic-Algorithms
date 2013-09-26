package HashMap;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap h = new HashMap();
		
		h.put(9, 23);
		h.put(10, 24);
		h.put(11, 30);

		System.out.println(h);
		
		System.out.println(h.remove(10));
		System.out.println(h.remove(10));
	}
}
