package unionFind;
import java.util.*;
import java.io.*;

public class WeightedQuickUnionUFClient {
    // client to test the algorithm implementations
	public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of nodes: ");
	    int N = input.nextInt(); // input the number of elements
		WeightedQuickUnionUF qf = new WeightedQuickUnionUF(N);
		int count = 0;
		while (input != null) {
		    count++;
			System.out.println("Enter p: ");
		    int p = input.nextInt();
			System.out.println("Enter q: ");
			int q = input.nextInt();
			System.out.println("Are " + p + " and " + q + " connected?: " + qf.connected(p, q));
			if (!qf.connected(p, q)) {
			    qf.union(p, q);
				System.out.println(p + " and " + q + " are now connected.");
			}
		}
	}
}