package various;

public class MaxEnergy {
	/* 
	 * precondition weight.length >= 3
	 */
	public static int getEnergy(int[] weight, int sumEnergy, int index) {
		int energy = weight[index - 1] * weight [index + 1] + sumEnergy;

		int[] newWeight = new int[weight.length - 1];
		int j = 0;
		for (int i = 0; i < weight.length; ++i) {
			if (i == index) continue;
			newWeight[j++] = weight[i];
		}
		return maxEnergy(newWeight, energy);
	}

	/*
	 * 3 <= weight.length <= 10
	 * 1 <= weight[i] <= 1000
	 */
	public static int maxEnergy(int[] weight, int sumEnergy) {
		if (weight.length == 2) return sumEnergy;

		int maxEnergy = 0;
		for (int i = 1; i < weight.length - 1; ++i) {
			int res = getEnergy(weight, sumEnergy, i);
			maxEnergy = (res > maxEnergy) ? res : maxEnergy;
		}

		return maxEnergy;
	}

	public static int func(int[] a){
		int len=a.length, i, j,ans=0;

		for(i = 1; i < len - 1; i++){ 
			int b[] = new int[len-1]; 
			for(j=0; j<len-1; j++) b[j] = a[j + ((j >= i) ? 1 : 0)];
			int res = func(b) + a[i-1] * a[i+1];
			ans = Math.max(ans,res);
		}

		return ans;
	}
	public static int maxEnergy(int[] weight)
	{
		int n = weight.length;
		// DP[i][j] is the answer to subsequence weight[i...j].
		int dp[][] = new int[n][n];
		
		// If DP[a][b] dependes on DP[c][d], we have: |c-d| < |a-b|, 
		// so we do DP in the order of (i-j).
		for(int d = 1 ; d < n ; d++) 
			for(int i = 0 ; i+d < n ; i++)
			{
				if(d == 1)
					// {W[i], W[i+1]} => {W[i], W[i+1]} needs not any 
					// operation, so the answer is 0.
					dp[i][i+d] = 0; 
				else
				{
					dp[i][i+d] = 0;
					for(int j = i+1 ; j < i+d ; j++)
						if(dp[i][j] + dp[j][i+d] + weight[i] * weight[i+d] > dp[i][i+d])
							// We try every element as the first one we insert.
							dp[i][i+d] = dp[i][j] + dp[j][i+d] + weight[i] * weight[i+d]; 
				}
			}
		return dp[0][n-1];
	}	
	public static void main(String[] args) {
		int[] weight = {477,744,474,777,447,747,777,474};
		System.out.println(maxEnergy(weight));
	}

}
