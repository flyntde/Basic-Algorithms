package various;

public class FizzBuzz {

	/**
	 * Write a program that prints the numbers from 1 to 100. 
	 * But for multiples of three print “Fizz” instead of the number and 
	 * for the multiples of five print “Buzz”. For numbers which are multiples 
	 * of both three and five print “FizzBuzz”."
	 */
	public static void fizzBuzz() {
		for (int i = 1; i <= 100; ++i) {
			if (i % 3 == 0 && i % 5 == 0) {
				System.out.println("FizzBuzz");
				continue;
			}
			if (i % 3 == 0) {
				System.out.println("Fizz");
				continue;
			}
			if (i % 5 == 0) {
				System.out.println("Buzz");
				continue;
			}
			System.out.println(i);
		}
	}
	
	public static void swapWithAdd() {
		int a = 9;
		int b = 23;
		a = a + b; b = a - b; a = a - b;
		System.out.println(a);
		System.out.println(b);
	}
	
	public static void swapWithXor() {
		int a = 9;
		int b = 23;
		
		a = a ^ b; b = a ^ b; a = a ^ b;
		
		System.out.println(a);
		System.out.println(b);		
	}
	
	public static String productSign(int[] a) {
		int neg = 0;
		
		for (int i: a) {
			if (i == 0) return "ZERO";
			if (i < 0) neg++;
		}
		return neg % 2 == 0 ? "POSITIVE" : "NEGATIVE";
	}
	
	public static void main(String[] args) {
//		fizzBuzz();
//		swapWithXor();
		
		
		int[] a = {-100000, 4, 9, 44, -1, -40};
		System.out.println(productSign(a));
		
	}

}
