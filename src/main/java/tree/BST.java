package tree;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	private class Node {
		private Key key;
		private Value val;
		private Node left = null;
		private Node right = null;
		private int size;
		
		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}
	
	public boolean isEmpty() { return size(root) == 0; }
	
	public int size() { return size(root); }
	
	private int size(Node x) {return (x == null) ? 0 : x.size; }
	
	public boolean contains(Key key) { return get(root, key) != null; }
	
	public Value get(Key key) { return get(root, key); }
	
	private Value get(Node x, Key key) { 
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return get(x.left, key);
		if (cmp > 0) return get(x.right, key);
		return x.val;
	}
	
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
		check();
	}
	
	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(key);
		if (cmp < 0) x.left = put(x.left, key, val);
		else if (cmp > 0) x.left = put(x.right, key, val);
		else x.val = val;
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("tree underflow");
		root = deleteMin(root);
		check();
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("tree underflow");
		root = deleteMax(root);
		check();
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.size = size(x.left)+ size(x.right) + 1;
		return x;
	}
	
	private void delete(Key key) { root = delete(root, key); check(); }
	
	private Node delete(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public Key min() { return (isEmpty()) ? null : min(root).key; }
	
	private Node min(Node x) { return (x.left == null) ? x : min(x.left); }
	
	public Key max() { return (isEmpty()) ? null : max(root).key; }
	
	private Node max(Node x) { return (x.right == null) ? x : max(x.right); }
	
	public Key floor(Key key) {
		Node x = floor(root, key);
		return (x == null) ? null : x.key;
	}
	
	private Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		return (t != null) ? t : x;
	}
	
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		return (x == null) ? null : x.key;
	}
	
	private Node ceiling(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) {
			Node t = ceiling(x.left, key);
			return (t != null) ? t : x;
		}
		return ceiling(x.right, key);
	}
	
	public Key select(int k) {
		if (k < 0 || k >= size()) return null;
		return select(root, k).key;
	}
	
	private Node select(Node x, int k) {
		if (x == null) return null;
		int size = size(x.left);
		if (size > k) return select(x.left, k);
		if (size < k) return select(x.right, k-size-1);
		return x;
	}
	
	public int rank(Key key) { return rank(root, key); }
	
	// number of keys in the subtree less than x.key
	private int rank(Node x, Key key) {
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return rank(x.left, key);
		if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
		return size(x.left);
	}
	
	private boolean check() {
		return isBST(root, null, null) && isSizeConsistent(root) 
				&& isRangeConsistent();
	}

	private boolean isBST(Node x, Key min, Key max) {
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	}
	
	private boolean isSizeConsistent(Node x) {
		if (x == null) return true;
		if (x.size != size(x.left) + size(x.right) + 1) return false;
		return isSizeConsistent(x.left) && isSizeConsistent(x.right);
	}
	
	private boolean isRangeConsistent() {
		return true;
	}
}
