package sorting;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {

	public long numComparisons = 0;
	
	public  static int[] loadInputArray(String datafile) {
		List<Integer> input = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(datafile));
			String line = null;
			while((line = br.readLine()) != null)
				input.add(Integer.parseInt(line));
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int arr[] = new int[input.size()];
		for (int i = 0; i < input.size(); i++) {
			arr[i] = input.get(i);
		}
		return arr;		
	}
	
	// invariant: 0 <= i <= j < arr.length
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
//	private int chooseLeftPivot(int[] arr, int left, int right) 
//	{
//		return arr[left];
//	}
//	
//	private int chooseRightPivot(int[] arr, int left, int right) {
//		swap(arr, left, right);
//		return arr[left];
//	}
	
	int median(int a, int b, int c) {
	    if ((a - b) * (c - a) >= 0) // a >= b and a <= c OR a <= b and a >= c
	        return a;
	    if ((b - a) * (c - b) >= 0) // b >= a and b <= c OR b <= a and b >= c
	        return b;
	    
	    return c;
	}
	
	private int chooseMedianPivot(int[] arr, int left, int right) {
		int leftElem = arr[left];
		int rightElem = arr[right];
		int len = right - left + 1;
		int middleIndex = (len %2 == 0) ? len/2 + left - 1: len/2 + left; 
		int middleElem = arr[middleIndex];
		int medianPivot = median(leftElem, rightElem, middleElem);
		
		if (rightElem == medianPivot) swap(arr, left, right);
		else if (middleElem == medianPivot) swap(arr, left, middleIndex);
		return medianPivot;
	}
	
	// partition into left and right sub arrays around pivot and 
	// return index of pivot location in arr
	private int partition (int arr[], int left, int right) {
		int pivot = chooseMedianPivot (arr, left, right);
		
		int i = left + 1;
		for (int j = left + 1; j <= right; ++j) {
			if (!(arr[j] < pivot)) continue;
			swap(arr, i, j);
			++i;
		}
		
		swap(arr, left, i - 1);
		return i - 1;
	}
	
	public void quicksort(int[] arr, int left, int right) {
		numComparisons += right - left;
		
		// subarray length = 1
		if (left == right) return;
		
		// partition arr around pivot
		int pivotIndex = partition(arr, left, right);
		
		// if left part contains at least one element, sort it
		if ((pivotIndex - left) > 0) {
			quicksort(arr, left, pivotIndex - 1);
		}
		// if right part contains at least one element, sort it
		if ((right - pivotIndex) > 0) {
			quicksort(arr, pivotIndex + 1, right);
		}
		return;
	}
	
	public static void main(String[] args) {
		int[] input = loadInputArray("QuickSort.txt");
	
		QuickSort qs = new QuickSort();
		qs.quicksort(input, 0, input.length - 1);
		//for (int i: input) 
			
		System.out.println(qs.numComparisons);
	}
}
