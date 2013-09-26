package sorting;

public class RSelect {

	public static int middleIndex(int[] arr) {
		return (arr.length % 2 == 0) ? arr.length / 2 - 1 : arr.length / 2; 
	}
	public static void main(String[] args) {
		int[] arrOdd = new int[11];
		int[] arrEven = new int[100];
		
		System.out.println("middle of int[11] is " + middleIndex(arrOdd));
		System.out.println("middle of int[100] is " + middleIndex(arrEven));
	}
}
