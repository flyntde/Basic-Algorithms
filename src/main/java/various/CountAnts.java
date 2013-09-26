package various;

public class CountAnts {

	private static void checkIntersect(int[] x, int[] y, boolean[] ants) {
		for (int i = 0; i < ants.length - 1; i++)
			for (int j = i + 1; j < ants.length; j++) 
				if (x[i] == x[j] && y[i] == y[j]) 
					ants[i] = ants[j] = false;
	}
	
	public static int 
	countAnts(int[] x, int[] y, String direction) {
		boolean[] ants = new boolean[x.length];
		
		checkIntersect(x, y, ants);
		
		char[] dir = direction.toCharArray();
		for (int j = 1; j <= 2000; j++) {
			for (int i = 0; i < x.length; ++i) {
				int dx = 0;
				int dy = 0;
				if (dir[i] == 'E') dx = 1;
				if (dir[i] == 'W') dx = -1;
				if (dir[i] == 'N') dy = 1;
				if (dir[i] == 'S') dy = -1;
				x[i] += dx;
				y[i] += dy;
			}
			checkIntersect(x, y, ants);
		}
	
		int count = 0;
		for (boolean alive: ants) if (alive) count++;
		return count;
	}
	
	
	public static void main(String[] args) {
		int[] x = {478,-664,759,434,-405,513,565,-396,311,-174,56,993,251,
				-341,993,-112,242,129,383,513,-78,-341,-148,129,423
				,493,434,-405,478,-148,929,251,56,242,929,-78,423,-664,802,251,
				759,383,-112,-591,-591,-248,660,660,735,493};
		int[] y = {-186,98,948,795,289,-678,948,-170,-195,290,-354,-424,289,
				-157,-166,150,706,-678,684,-294,-234,36,36,-294,-216
				,-234,427,945,265,-157,265,715,275,715,-186,337,798,-170,
				427,706,754,961,286,-216,798,286,961,684,-424,337};
		String direction = "WNSNNSSWWWEENWESNSWSWSEWWEWEWWWNWESNSSNNSNNWWWNESE";
		
		System.out.println(countAnts(x, y, direction));

	}

}
