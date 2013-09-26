package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AdjMatrixAlgorithms {
	private static int[][] adjmatrix = null;
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		Set<Integer> explored = new HashSet<Integer>();
		explored.add(start);
		q.offer(start);
		
		while (!q.isEmpty()) {
			int u = q.poll();
			System.out.println(u);
			for (int v = 0; v < 201; ++v)
				if (adjmatrix[u][v] == 1 && !explored.contains(v)) {
					explored.add(v);
					q.offer(v);
				}
		}		
	}
	
	private static Set<Integer> explored = new HashSet<Integer>();

	public static void dfs(int start) {
		explored.add(start);
		System.out.println(start);
		
		for (int v = 0; v < 201; ++v)
			if (adjmatrix[start][v] == 1 && !explored.contains(v)) {
				explored.add(v);
				dfs(v);
			}
	}
	
	
	public static void loadGraph(String path) {
		adjmatrix = new int[201][201];
		
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("(\\s)+");
				int row = Integer.parseInt(split[0]);
				for (int i = 1; i < split.length; ++i) 
					adjmatrix[row][Integer.parseInt(split[i])] = 1;
			}
			if (br != null) br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		loadGraph("smallMinCut.txt");
		bfs(4);
		//dfs(4);
	}
}
