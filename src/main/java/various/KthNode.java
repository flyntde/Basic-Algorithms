package various;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KthNode {

	List<List<Integer>> main = new ArrayList<List<Integer>>();
	int k;
	int originalSize;

	private int mainPos = 0;

	KthNode(int data[], int k) {
		if (data.length == 0) throw new IllegalArgumentException("empty data");
		if (k > data.length) throw new IllegalArgumentException("k to big");
		for (int i = 0; i < data.length / k + 1; i++) {
			List<Integer> sublist = new LinkedList<Integer>();
			for (int j = i * k; j < k * (i + 1) && j < data.length; j++) {
				sublist.add(j);
			}
			main.add(sublist);
		}
		this.k = k;
		this.originalSize = data.length;
	}

	private List<Integer> resize()
	{
		List<List<Integer>> newMain = new ArrayList<List<Integer>>();
		for (List<Integer> ls: main) {
			if (!ls.isEmpty())
				newMain.add(ls);
		}

		main = newMain;
		mainPos = mainPos >= main.size() ? 0 : mainPos;
		return main.get(mainPos);
	}

	private void borrow (List<Integer> ls, int need) {
		int borrowed = 0;
		for (int i = mainPos; i < main.size() + mainPos; i++) {
			if (borrowed == need) return;
			int j = i % main.size();

			List<Integer> lender = main.get(j);
			while(borrowed < need) {
				if (lender.isEmpty()) break;
				ls.add(lender.remove(0));
				borrowed++;
			}
		}
	}

	public Integer delKth() {
		List<Integer> ls = main.get(mainPos);
		if (ls.isEmpty()) {
			ls = resize();
		}

		++mainPos;
		// wrap back to main list index 0;
		mainPos = mainPos >= main.size() ? 0 : mainPos;

		int need = k - ls.size();
		borrow(ls, need);

		// remove last element
		Integer val = ls.remove(ls.size() - 1);

		return val;
	}

	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (List<Integer> ls: main) {
			for (Integer i: ls)
				sb.append(i).append(" ");
			sb.append('\n');
		}
		return sb.toString();
	}

	int josephus(int n, int k) {
		return josephus(n, k, 1);
	}
	
	int josephus(int n, int k, int startingPoint) {
		if(n == 1)
			return 1;
		int newSp = (startingPoint + k - 2) % n + 1;

		int survivor = josephus(n - 1, k, newSp);
		if (survivor < newSp) {
			return survivor;
		} else
			return survivor + 1;
	}
	
	public static void main(String[] args) {
		int a[] = new int[23];
		for (int i = 0; i < 23; i++) {
			a[i] = i;
		}

		KthNode kn = new KthNode(a, 5);
		System.out.println(kn);

		for (int i = 0; i < 23; i++) {
			Integer kth = kn.delKth();
			System.out.println("*"+kth+"*");
			System.out.println(kn);
		}
		
		System.out.println(kn.josephus(23, 5));
	}
}
