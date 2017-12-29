package unionFind;

public class QuickUnionUF {
    private int[] id;
	
	// constructor: creates an array of size N and assigns values equal to the indices of the array
	public QuickUnionUF(int N) {
	    id = new int[N];
		for (int i = 0; i < N; i++) {
		    id[i] = i;
		}
	}
	
	// root method: private method to find root of a node
	private int root(int i) {
	    while (i != id[i]) {
		    i = id[i];
		}
		return i;
	}
	
	// connected operation: returns true if two elements are connected i.e. if they have the same value
	public boolean connected(int p, int q) {
	    return root(p) == root(q);
	}
	
	//union operation: traverse the array and find the values equal to that of id[p] and change all of them to id[q]
	public void union(int p, int q) {
	    int root_p = root(p);
		int root_q = root(q);
		id[root_p] = root_q;
	}
}