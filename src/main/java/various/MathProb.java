package various;

import java.util.ArrayList;
import java.util.List;

public class MathProb {

	public static int power(int a, int b) {
		if (b == 0) return 1;
		if (b == 1) return a;
		return b % 2 == 0 ? power(a * a, b/2) : a * power(a * a, (b -1)/2);
	}
	
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
	
	public List<Integer> primes(int max) {
		return new ArrayList<Integer>();
	}
	
	public int hexconv(int x) {
		int c = x;
		c <<= 8 | c;
		c <<= 16 | c;
		return c;
	}
	
	public static void dice() {
		int[] d1 = {1, 2, 3, 7, 8, 9}; 
		int[] d2 = {0, 1, 2, 4, 5, 6}; 
		for (int i: d1)
			for (int j: d2)
				System.out.println(i + "," + j);
	}
	public static void main(String[] args) {
		System.out.println(power(5,3));
		System.out.println(gcd(14, 21));
		System.out.println(0x25);
		dice();
	}
}
