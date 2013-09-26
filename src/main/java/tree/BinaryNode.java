package tree;

//Basic node stored in unbalanced binary search trees
//Note that this class is not accessible outside
//of this package.

public class BinaryNode {
	// Constructors
	BinaryNode(Integer theElement ) {
		element = theElement;
		left = right = null;
	}

	// Friendly data; accessible by other package routines
	Integer element;      // The data in the node
	BinaryNode left;         // Left child
	BinaryNode right;        // Right child
	
	@Override
	public String toString() { return element.toString(); }
}
