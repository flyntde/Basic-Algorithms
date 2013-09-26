package various;

import java.util.ArrayList;
import java.util.List;

public class AreAnagrams {

	public static boolean areAnagrams(String s1, String s2) {
	    if (s1.length() != s2.length()) return false;
	    
	    List<Character> s2Chars = new ArrayList<Character>();
	    
	    for (Character c: s2.toCharArray()) {
	    	s2Chars.add(c);
	    }
	    
	    for (Character c1: s1.toCharArray()) {
	    	if (!s2Chars.remove(c1)) return false;
	    }
	    
	    return true;
	}
	
	public static void main(String[] args) {
		System.out.println(areAnagrams("that", "hatt"));
		System.out.println(areAnagrams("the", "hatt"));
		System.out.println(areAnagrams("tea", "ate"));
		System.out.println(areAnagrams("", ""));
		System.out.println(areAnagrams("orchestra", "carthorse"));
	}
}
