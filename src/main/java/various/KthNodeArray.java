package various;


public class KthNodeArray {
	
	Integer[] arr = null;
	int iter = 0;
	int size;
	
	public KthNodeArray(Integer[] arr) {
		if (arr.length == 0) throw new IllegalArgumentException("a empty");
		
		this.arr = new Integer[arr.length];
		System.arraycopy(arr, 0, this.arr, 0, arr.length);
		size = this.arr.length;
	}
	
	public Integer delKthNode(int  k) {

		int index = (++iter * k) % arr.length - 1;
		System.out.println("index: " + index);
		if (index < 0) index = 0;
		
		while(arr[index] == null) ++index;
		
		if (size > 1) {
			--size;
			arr[index] = null;
		}
		
		return(arr[index]);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Integer i: arr) {
			if (i != null)
				sb.append(i + " ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Integer[] a = new Integer[21];
		for (int i = 0; i < 21; i++)
			a[i] = i;
		
		KthNodeArray kn = new KthNodeArray(a);
		System.out.println(kn);
		
		for (int i = 0; i < 21; i++) {
			kn.delKthNode(5);
			System.out.println(kn);
		}
	}

}
