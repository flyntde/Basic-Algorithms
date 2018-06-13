import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * Karger's algorithm for min cut for undirected graphs
 */
public class MinCut {

	private Map<Integer, List<Integer>> adj = 
			new HashMap<Integer, List<Integer>>();

	private List<Edge> edges = new ArrayList<Edge>();

	private Random rnd = new Random();

	/*
	 * read: Edge(u,v) as an edge from u to v
	 */
	private static class Edge {
		public Integer u;
		public Integer v;

		public Edge (Integer u, Integer v) {this.u = u; this.v = v;}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((u == null) ? 0 : u.hashCode());
			result = prime * result + ((v == null) ? 0 : v.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Edge))
				return false;
			Edge other = (Edge) obj;
			if (u == null) {
				if (other.u != null)
					return false;
			} else if (!u.equals(other.u))
				return false;
			if (v == null) {
				if (other.v != null)
					return false;
			} else if (!v.equals(other.v))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "(" + u + "," + v + ")";
		}
	}

	public void replaceEdgeVertex(Integer oldVertex, Integer newVertex) {
		for (Edge e: edges) {
			if (e.v.equals(oldVertex)) e.v = newVertex;
			if (e.u.equals(oldVertex)) e.u = newVertex;
		}
	}

	// precondition -- there should be edges in the input graph
	public Edge pickAndRemoveRandomEdge() {
		if (edges.size() == 0) return null;
		Edge e = edges.remove(rnd.nextInt(edges.size()));
		Edge rev = new Edge(e.v, e.u);
		edges.remove(rev);

		replaceEdgeVertex(e.v, e.u);
		return e;
	}

	private void 
	rerouteEdge(Integer vertex, Integer oldNeighbor, Integer newNeighbor) {
		List<Integer> neighbors = adj.get(vertex);
		List<Integer> newNeighbors = new ArrayList<Integer>();
		for(Integer neighbor: neighbors) {
			if (neighbor.equals(oldNeighbor)) 
				newNeighbors.add(newNeighbor);
			else newNeighbors.add(neighbor);
		}
		adj.put(vertex, newNeighbors);
	}

	public void removeSelfLoops(Integer vertex) {
		List<Integer> neighbors = adj.get(vertex);
		List<Integer> newNeighbors = new ArrayList<Integer>();
		
		for (Integer neighbor: neighbors) 
			if (!neighbor.equals(vertex))
				newNeighbors.add(neighbor);

		adj.put(vertex, newNeighbors);

		List<Edge> newEdges= new ArrayList<Edge>();
		for(Edge e: edges)
			if (!e.u.equals(e.v))
				newEdges.add(e);

		edges = newEdges;
	}

	/*
	 * merge or contract vertices u and v into a single vertex.  
	 * Explicitly:
	 * 1) remove edge (u, v) and (v, u) from the adjacency list 
	 * 2) for all neighbors w of v, such that (v, w) in edges
	      - add edge (u, w) which can result in parallel edges
	 * 3) for each edge (w, v) (!v.equals(u)), replace with (w, u)
	 * 4) remove vertex v from the graph
	 * 5) remove all edge (u, u) created during the merge
	 * 6) keep track of edge v in a separate collection of vertices
	 *    which have been merged.
	 */
	public void mergeVertex(Integer from, Integer to) {

		List<Integer> fromNeighbors = adj.get(from);
		List<Integer> toNeighbors = adj.get(to);
		// 1) remove edge (u, v) and (v, u) from the adjacency list 
		toNeighbors.remove(from);
		fromNeighbors.remove(to);

		for(Integer fromNeighbor: fromNeighbors) {
			// 2) for all neighbors w of v, such that (v, w) in edges
		    //    - add edge (u, w) which can result in parallel edges
			
			toNeighbors.add(new Integer(fromNeighbor));
		}
		
		for(Integer fromNeighbor: fromNeighbors) {
			// 3) for each edge (w, v) (!v.equals(u)), replace with (w, u)
			rerouteEdge(fromNeighbor, from, to);
		}
		// 4) remove vertex v from the graph
		adj.remove(from);

		// 5) remove all edge (u, u) created during the merge
		removeSelfLoops(to);
	}

	public int minCut() {
		while (adj.size() > 2) {
			Edge e = pickAndRemoveRandomEdge();
			mergeVertex(e.v, e.u);
		}
		return edges.size() / 2;
	}

	// shallow copy 
	public void initGraph(Map<Integer, List<Integer>> adjList) {
		adj.clear();
		edges.clear();
		for(Map.Entry<Integer, List<Integer>> entry: adjList.entrySet()) {
			Integer vertex = entry.getKey();
			List<Integer> neighbors = new ArrayList<Integer>(entry.getValue());
			adj.put(vertex, neighbors);
			for(Integer to: neighbors) edges.add(new Edge(vertex, to));
		}
	}

	public void printGraph() {
		System.out.println("Graph: " + adj.size());
		for (Integer u: adj.keySet()) {
			List<Integer> neighbors = adj.get(u);
			StringBuilder sb = new StringBuilder();
			sb.append(u).append("-->");
			for(Integer neighbor: neighbors)
				sb.append(neighbor).append(" ");

			System.out.println(sb.toString());
		}
		
		System.out.println("Edges " + edges.size());
		for (Edge e: edges)
			System.out.println(e);
	}

	public static Map<Integer, List<Integer>> loadGraph(String datafile) {
		BufferedReader br = null;
		Map<Integer, List<Integer>> adjList = 
				new HashMap<Integer, List<Integer>>();
		try {
			br = new BufferedReader(new FileReader(datafile));
			String line = null;

			while ((line = br.readLine()) != null) {
				String[] splits = line.split("(\\s)+");
				Integer vertex = Integer.parseInt(splits[0]);
				List<Integer> neighbors = new ArrayList<Integer>();
				for(int i = 1; i < splits.length; ++i) {
					Integer neighbor = Integer.parseInt(splits[i]);
					neighbors.add(neighbor);
				}

				adjList.put(vertex, neighbors);		
			}

			if (br != null) br.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return adjList;
	}

	public static void main(String[] args) {

		MinCut mc = new MinCut();
		Map<Integer, List<Integer>> graph = MinCut.loadGraph("kargerMinCut.txt");

		int mincut = 10000;
		for (int i = 0; i < 100; i++) {
			mc.initGraph(graph);
			int cut = mc.minCut();
			if (cut < mincut ) mincut = cut;
		}
		System.out.println(mincut);
	}
}
