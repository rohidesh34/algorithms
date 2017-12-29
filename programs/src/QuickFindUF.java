package unionFind;

public class QuickFindUF {
    private int[] id;
	
	// constructor: creates an array of size N and assigns values equal to the indices of the array
	public QuickFindUF(int N) {
	    id = new int[N];
		for (int i = 0; i < N; i++) {
		    id[i] = i;
		}
	}
	
	// connected operation: returns true if two elements are connected i.e. if they have the same value
	public boolean connected(int p, int q) {
	    return id[p] == id[q];
	}
	
	//union operation: traverse the array and find the values equal to that of id[p] and change all of them to id[q]
	public void union(int p, int q) {
	    int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++) {
		    if(id[i] == pid) {
			    id[i] = qid;
			}
		}
	}
}