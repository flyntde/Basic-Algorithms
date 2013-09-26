package euler;

public class Triangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int a = 1; a < 1000; a++) 
			for (int b = a; b < 1000; b++)
				for (int c = b; c < 1000; c++)
					if (a + b + c == 1000 && a*a + b*b == c*c) 
						System.out.println(a * b * c);

	}

}
