package graph;


public class Edge {
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
	
	public static void main (String[] args) {
		Edge a = new Edge(1, 2);
		Edge b = new Edge(1, 2);
		
		System.out.println(a.equals(b));
	}
}
