package unionFind;

public class WeightedQuickUnionUF {
    private int[] id;
	private int[] sz;
	
	// constructor: creates an array of size N and assigns values equal to the indices of the array
	public WeightedQuickUnionUF(int N) {
	    id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
		    id[i] = i;
			sz[i] = 1;
		}
	}
	
	// root method: private method to find root of a node
	private int root(int i) {
	    while (i != id[i]) {
		    i = id[i];
		}
		return i;
	}
	
	// connected operation: returns true if two elements are connected i.e. if they have the same root
	public boolean connected(int p, int q) {
	    return root(p) == root(q);
	}
	
	//union operation: find the roots of p and q. Change the root of smaller tree to that of the larger tree.
	public void union(int p, int q) {
	    int i = root(p);
		int j = root(q);
		if (i == j) {
		    return;
		}
		System.out.println("Size of tree with element " + p + " is: " + sz[i]);
		System.out.println("Size of tree with element " + q + " is: " + sz[j]);
		// if tree with root i is smaller than tree with root j, then make tree i a child of tree j; else other way around.
		if (sz[i] < sz[j]) {
		    id[i] = j;
			sz[j] += sz[i];
		} else {
		    id[j] = i;
			sz[i] += sz[j];
		}
	}
}