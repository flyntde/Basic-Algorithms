package various;

public class MaxSum {

	public static int maxSum(int[] arr, int pos, int sum) {
		if (arr.length == 1) return arr[0];
		
		if (pos > arr.length - 1) return sum;

		int sum1 = maxSum(arr, pos + 2, sum + arr[pos]);
		int sum2 = maxSum(arr, pos + 1, sum);

		return Math.max(sum1, sum2);
	}

	
	public static void main(String[] args) {
		
		int[] arr = {4, 5, 15, 1, 3, 12, 3};
		int x = maxSum(arr, 0, 0);
		
		System.out.println(x);
	}
}
