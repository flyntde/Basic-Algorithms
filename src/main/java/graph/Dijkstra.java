package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
	
	// (u, v) = weight
	private static Map<Integer, Map<Integer, Integer>> edgeWeights = 
			new HashMap<Integer, Map<Integer, Integer>>();
	private static Map<Integer, List<Integer>> adj = 
			new HashMap<Integer, List<Integer>>();
	
	private static Set<Integer> unexplored = new HashSet<Integer>();
	private static Set<Integer> explored = new HashSet<Integer>();
	private static Map<Integer, Integer> shortestPaths = 
			new HashMap<Integer, Integer>();

	
	public static Map<Integer, Integer> dijkstra(Integer start)
	{
		unexplored.remove(start);
		explored.add(start);
		shortestPaths.put(start, 0);
		
		while (!unexplored.isEmpty()) {
			// among all edges (v, w) in E with v in explored and w in
			// unexplored, find w such that shortestPaths[v] + len(v,w) is min
			
			int minWeight = 1000000;
			Integer bestUnexplored = null;
			for (Integer v: explored) {
				List<Integer> neighbors = adj.get(v);
				int shortestToV = shortestPaths.get(v);
				
				Map<Integer, Integer> edgeWeightsToV = edgeWeights.get(v);
				
				for (Integer w: neighbors) {
					if (explored.contains(w)) continue;
					int weightFromVtoW = shortestToV + edgeWeightsToV.get(w);
					if (weightFromVtoW < minWeight) {
						bestUnexplored = w;
						minWeight = weightFromVtoW;
					}
				}
			}
			
			unexplored.remove(bestUnexplored);
			explored.add(bestUnexplored);
			shortestPaths.put(bestUnexplored, minWeight);
		}
		return shortestPaths;
	}
	
	public static void loadGraph(String datafile) {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(datafile));
			String line = null;

			while ((line = br.readLine()) != null) {
				String[] splits = line.split("(\\s)+");
				Integer vertex = Integer.parseInt(splits[0]);
				List<Integer> neighbors = new ArrayList<Integer>();
				Map<Integer, Integer> weights = new HashMap<Integer, Integer>();
				for(String split: splits) {
					String[] node = split.split(",");
					if (node.length != 2) continue;
					Integer neighbor = Integer.parseInt(node[0]);
					Integer weight = Integer.parseInt(node[1]); 
					neighbors.add(neighbor);
					weights.put(neighbor, weight);
				}

				adj.put(vertex, neighbors);
				edgeWeights.put(vertex, weights);
				unexplored.add(vertex);
			}

			if (br != null) br.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Dijkstra.loadGraph("dijkstraData.txt");
		Map<Integer, Integer> paths = Dijkstra.dijkstra(1);
		
		for (Integer node: paths.keySet())
			System.out.println(node + " " + paths.get(node));
		
	}

}
