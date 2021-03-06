package dynamic;

public class SeqAlign {

	public static String[] align(String a, String b) {
		int[][] T = new int[a.length() + 1][b.length() + 1];

		for (int i = 0; i <= a.length(); i++)
			T[i][0] = i;

		for (int i = 0; i <= b.length(); i++)
			T[0][i] = i;

		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1))
					T[i][j] = T[i - 1][j - 1];
				else
					T[i][j] = Math.min(T[i - 1][j], T[i][j - 1]) + 1;
			}
		}

		StringBuilder aa = new StringBuilder(), bb = new StringBuilder();

		for (int i = a.length(), j = b.length(); i > 0 || j > 0; ) {
			if (i > 0 && T[i][j] == T[i - 1][j] + 1) {
				aa.append(a.charAt(--i));
				bb.append("-");
			} else if (j > 0 && T[i][j] == T[i][j - 1] + 1) {
				bb.append(b.charAt(--j));
				aa.append("-");
			} else if (i > 0 && j > 0 && T[i][j] == T[i - 1][j - 1]) {
				aa.append(a.charAt(--i));
				bb.append(b.charAt(--j));
			}
		}

		return new String[]{aa.reverse().toString(), bb.reverse().toString()};
	}


	public static void main(String[] args) {
		String[] aligned = align("aabbhtt", "fabbcnghtuvhttqzbk");
		System.out.println(aligned[0]);
		System.out.println(aligned[1]);
	}
}
