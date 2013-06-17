import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * Algorithm to compute shortest paths on a n x m grid with a constraint
 * in movement (e.g. only diagonal moves).  This is quick and dirty, and
 * several shortcuts are taken to minimize the code.  NOT a demonstration on
 * bulletproof code and not best practices, e.g equals(), and no attempt
 * to prevent access to object variables. 
 */
public class GridShortestPath {

	public class Position 
	{
		public final int i, j;
		public int pathweight = 0;
		public boolean explored = false;
		
		public Position(int i, int j, int weight) {
			this.i = i; this.j = j; pathweight = weight;
		}
		
		@Override
		public int hashCode() {
			return i * n + j + 1;
		}

		@Override
		public boolean equals(Object obj) {
			Position other = (Position) obj;
			return this.i == other.i && this.j == other.j;
		}

		@Override
		public String toString() {return "(" + i + "," + j + ")";}
	}
	
	private final int n, m;
	private Position[][] board;
	public final int inf;
	private Position start, goal = null;
	
	public Position getStart() {return start;}
	public Position getGoal() {return goal;}
	
	public GridShortestPath(int n, int m) {
		this.n = n; this.m = m;
		inf = n * m; // greater than all path lengths
		board = new Position[n][m];
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) { 
				board[i][j] = new Position(i, j, inf);
			}
		}
	}
	
	/*
	 * determines whether the start and goal positions are both
	 * on the same color position when the board is colored in
	 * a checkered pattern like a chess board.
	 */
	public boolean isMatchingColor(Position a, Position b) {
		return (a.i + a.j) % 2 == (b.i + b.j) % 2;
	}
	
	/*
	 * corner (diagonal) neighbors are those among board[p.i+-1][p.j+-1] and
	 * 0 <= neighbor.i <= n, 0 <= neighbor.j <= m 
	 */
	Set<Position> getDiagNeighbors(Position p) {
		Set<Position> neighbors = new HashSet<Position>();		
		if (p.i+1 < n && p.j+1 < m) neighbors.add(board[p.i+1][p.j+1]);
		if (p.i-1 >= 0 && p.j+1 < m) neighbors.add(board[p.i-1][p.j+1]);
		if (p.i+1 < n && p.j-1 >= 0) neighbors.add(board[p.i+1][p.j-1]);
		if (p.i-1 >=0 && p.j-1 >= 0) neighbors.add(board[p.i-1][p.j-1]);
		return neighbors;
	}
	
	/*
	 * BFS with path weight calculated as each reachable position is explored.
	 * Note that this calculates shortest paths to all reachable grid
	 * positions.  An optimization would be to return immediately once the
	 * goal position is found. 
	 */
	public void pathWeight(Position start) {
		Queue<Position> q = new LinkedList<Position>();
		board[start.i][start.j].explored = true;
		board[start.i][start.j].pathweight = 0;
		q.offer(board[start.i][start.j]);
		
		while(!q.isEmpty()) {
			Position u = q.remove();
			for (Position v: getDiagNeighbors(u)) {
				if (!v.explored) {
					v.explored = true;
					v.pathweight = u.pathweight + 1;
					q.offer(v);
				}
			}
		}
	}
	
	/*
	 * After BFS and path hops calculated, find the shortest path by starting
	 * with the goal position and working backward to the start position.
	 * Return this (shortest) path in reverse.
	 */
	public List<Position> 
	getShortestPath(Position start, Position goal) {
		this.start = start; this.goal = goal;
		List<Position> path = new ArrayList<Position>();
		if (!isMatchingColor(start, goal)) return path;
		
		pathWeight(start);
		Position current = goal;
		while(!current.equals(start)) {
			path.add(current);
			Set<Position> neighbors = getDiagNeighbors(current);
			int min = inf;
			for (Position neighbor: neighbors) {
				if (board[neighbor.i][neighbor.j].pathweight < min) {
					current = neighbor;
					min = board[neighbor.i][neighbor.j].pathweight;
				}
			}	
		}
		path.add(start);
		Collections.reverse(path);
		return path;
	}
	
	public String formatPath(List<Position> path) {
		StringBuilder sb = new StringBuilder();
		int numPairs = 0;
		for (Position p: path) {
			sb.append(p.toString()).append(" ");
			numPairs++;
			if (numPairs % 12 == 0) sb.append('\n');
		}
		if (path.isEmpty()) 
			sb.append("no path from ").append(start)
				.append(" to ").append(goal); 
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		GridShortestPath g = new GridShortestPath(10, 100);
		List<Position> path = 
				g.getShortestPath(g.new Position(2,2,g.inf), 
						g.new Position(9,77,g.inf));
		
		System.out.println(g.formatPath(path));
	}

}
