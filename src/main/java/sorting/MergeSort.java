package sorting;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeSort {

	public static long numArrayInversions;
	
	private static int[] merge(int[] leftArr, int[] rightArr) {
		int[] arr = new int[leftArr.length + rightArr.length];
		int l = 0; int r = 0;
		for (int i = 0; i < arr.length; i++) {
			// if all of leftArr has been added to arr, then just
			// add the remainder of rightArr to arr and increment r
			if (l == leftArr.length) {
				arr[i] = rightArr[r++];
				continue;
			}
			
			// if all of rightArr has been added to arr, then just
			// add the remainder of leftArr to arr and increment l
			if (r == rightArr.length) {
				arr[i] = leftArr[l++];
				continue;
			}
			
			// if leftArr[l] < rightArr[r], add leftArr[l] to arr 
			// and increment l
			if (leftArr[l] < rightArr[r]) {
				arr[i] = leftArr[l++];
				continue;
			}
			
			// if leftArr[l] > rightArr[r], add rightArr[r] to arr
			// and increment r
			if (leftArr[l] > rightArr[r]) {
				arr[i] = rightArr[r++];
				numArrayInversions += leftArr.length - l;
			}
		}
		
		return arr;
	}
	
	public static int[] mergeSort(int[] arr) {
		if (arr.length == 1) return arr;
		
		int[] leftArr = new int[arr.length/2];
		int[] rightArr = (arr.length % 2 == 0) ? 
				new int[arr.length/2] : new int[arr.length/2 + 1];
				
		System.arraycopy(arr, 0, leftArr, 0, leftArr.length);
		System.arraycopy(arr, leftArr.length, rightArr, 0, rightArr.length);
		
		int[] sortedLeftArr = mergeSort(leftArr);
		int[] sortedRightArr = mergeSort(rightArr);
		
		int[] sortedArr = merge(sortedLeftArr, sortedRightArr);
		return sortedArr;
	}
	
	public static int[] loadInputArray(String datafile) {
		numArrayInversions = 0;
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
		int[] arr = new int[input.size()];
		for (int i = 0; i < arr.length; ++i) arr[i] = input.get(i);
		return arr;
	}
	
	public static void main(String[] args) {
		int[] input = loadInputArray("IntegerArray.txt");
		
		int[] sorted = mergeSort(input);
		for (int i: sorted) System.out.println(i);
		System.out.println(numArrayInversions);
	}

}
