package various;

public class StringPattern {

	private int index = 0;
	private String s = null;
	
	public boolean match(char c) {
		if (index == s.length()) return false;
		return c == s.charAt(index++);
	}
	
	public boolean matchSubstr(String str) {
		for (char c: str.toCharArray()) {
			if (!match(c)) return false;
		}
		return true;
	}
	
	public boolean pikachu(String str) {
		index = 0;
		this.s = str;
		boolean matches;
		while (index < s.length()) {
			matches = false;
			char c = s.charAt(index);
			if (c == 'p') matches = matchSubstr("pi"); 
			else if (c == 'k') matches = matchSubstr("ka");
			else if (c == 'c') matches = matchSubstr("chu");
			if (!matches) return false;
		}
		return true;
		
		//return s.matches("((pi)|(ka)|(cha))*") ? true: false;
	}
	
	public void inPiKaChu(String s) {
		if (pikachu(s)) System.out.println("YES");
		else System.out.println("NO");		
	}
	
	public static void main(String[] args) {
		StringPattern sp = new StringPattern();
		sp.inPiKaChu("pipipikapi");
		sp.inPiKaChu("foo");
		sp.inPiKaChu("");
		sp.inPiKaChu("chuch");
		sp.inPiKaChu("piip");
		System.out.println(Math.pow(2, 31) - 1);
	}

}
