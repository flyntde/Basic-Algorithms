package various;

public class AkariDaisukiDiv2 {
	
	public static int countTuples2(String s) {
		int count = 0;
		int len = s.length();
		
		for (int i = 1; i < len - 2; i++)
			for (int j = i + 1; j < len - 1; j++) {
				String x1 = s.substring(i, j);
				for (int k = j + 1; k < len - 1; k++)
					for (int l = k + 1; l < len; l++) {
						String x2 = s.substring(k, l);
						if (x1.equals(x2)) {
							count++;
						}
					}
			}
		
		return count;
	}
	
	
	public static void main(String[] args) {
		System.out.println(countTuples2("waaiusushioakariusushiodaisuki"));		
	}

}
