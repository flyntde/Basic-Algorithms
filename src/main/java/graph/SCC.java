package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Kosaraju's Two-Pass Algorithm for computing strongly connected
 * components in a directed graph.  O(m+n) running time.  
 */
public class SCC {
	private Map<Integer, List<Integer>> diGraph = 
			new HashMap<Integer, List<Integer>>();
	
	private Map<Integer, List<Integer>> revDiGraph =
			new HashMap<Integer, List<Integer>>();

	private Set<Integer> explored = new HashSet<Integer>();
	private Deque<Integer> finishingTimes = new ArrayDeque<Integer>();
	private int numNodesInSCC;
	private PriorityQueue<Integer> biggestSCCs = 
			new PriorityQueue<Integer>();
	
	private SCC() {
		for (int i = 0; i < 5; i++) biggestSCCs.offer(0);
	}
	
	private void updateSccCount(int numNodesInScc) {
		int lowestCount = biggestSCCs.peek();
		
		if (numNodesInScc > lowestCount) {
			biggestSCCs.poll();
			biggestSCCs.offer(numNodesInScc);
		}
	}
	
	private void dfs(Map<Integer, List<Integer>> graph, Integer i) {
		explored.add(i);
		if (graph == diGraph) numNodesInSCC++;
		List<Integer> neighbors = graph.get(i);
		
		if (neighbors != null)
			for (Integer neighbor: neighbors)
				if (!explored.contains(neighbor)) dfs(graph, neighbor);
		
		if (graph == revDiGraph) finishingTimes.push(i);
	}
	
	private void dfsLoop1(Map<Integer, List<Integer>> graph) {
		explored.clear();
		List<Integer> nodes = new ArrayList<Integer>(graph.keySet());
		
		for (Integer node: nodes)
			if (!explored.contains(node))
				dfs(graph, node);
	}
	
	private void dfsLoop2(Map<Integer, List<Integer>> graph) {
		explored.clear();
		
		while (!finishingTimes.isEmpty()) {
			Integer i = finishingTimes.removeFirst();

			numNodesInSCC = 0;
			if (!explored.contains(i)) {
				dfs(graph, i);
				
				updateSccCount(numNodesInSCC);
			}
		}
	}
	
	private PriorityQueue<Integer> findSCCs() {
		// do the first pass with reverted graph to find finishing times
		dfsLoop1(revDiGraph);
		
		// do the second pass with input graph in order of finishing times
		// to find SCCs
		dfsLoop2(diGraph);
		
		// return the largest five SCCs
		return biggestSCCs;
	}
	
	private List<Integer> 
	addNode(Map<Integer, List<Integer>> graph, Integer node) {
		if (graph.containsKey(node)) return graph.get(node);
		
		List<Integer> neighbors = new ArrayList<Integer>();
		graph.put(node, neighbors);
		return neighbors;
	}
	
	public void loadGraph(String path) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				String[] split = line.split("(\\s)+");
				Integer to = Integer.parseInt(split[0]);
				Integer from = Integer.parseInt(split[1]);
				
				List<Integer> neighborsOfTo = addNode(diGraph, to);
				List<Integer> neighborsOfFrom = addNode(revDiGraph, from);

				neighborsOfTo.add(from);
				neighborsOfFrom.add(to);
			}
			
			if (br != null) br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SCC scc = new SCC();
		
		scc.loadGraph("SCC.txt");
		
		System.out.println(scc.findSCCs());
	}

}
