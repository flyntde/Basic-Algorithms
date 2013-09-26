package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqSets {
	private static Set<Character> uniq_a = new HashSet<Character>();
	private static Set<Character> uniq_b = new HashSet<Character>();
	
	public static void uniq(Set<Character> a, Set<Character> b) {
		uniq_a.clear();
		uniq_b.clear();
		
		List<Character> alist = new ArrayList<Character>(a);
		List<Character> blist = new ArrayList<Character>(b);
		Collections.sort(alist);
		Collections.sort(blist);
		
		
		int len = Math.max(alist.size(), blist.size());
		System.out.println(len);
		
		int aindex = 0; int bindex = 0;
		for (int i = 0; i < alist.size() + blist.size(); ++i) {
			if (aindex > alist.size() - 1 && bindex > blist.size() - 1) break;
			if (aindex > alist.size() - 1) {
				uniq_b.add(blist.get(bindex++));
				continue;
			}
			if (bindex > blist.size() - 1) {
				uniq_a.add(alist.get(aindex++));
				continue;
			}
			int cmp = alist.get(aindex).compareTo(blist.get(bindex));
			if (cmp < 0) {
				uniq_a.add(alist.get(aindex++));
			}
			else if (cmp > 0) {
				uniq_b.add(blist.get(bindex++));
			}
			else {
				aindex++;
				bindex++;
			}
		}
	}
	
	public static void main(String[] args) {
		Character[] arra = new Character[] {'a', 'b', 'e', 'w', 'z'};
		Character[] arrb = new Character[] {'b', 'c', 'u', 'v', 'w', 'z'};
		Set<Character> a = new HashSet<Character>(Arrays.asList(arra));
		Set<Character> b = new HashSet<Character>(Arrays.asList(arrb));
		
		
		uniq(a, b);
		
		System.out.println(uniq_a);
		System.out.println(uniq_b);
	}

}
