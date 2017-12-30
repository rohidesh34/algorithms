/*
** AUTHOR:	rohidesh
** PROBLEM:	http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
** DESC:	Program to model the Percolation system.
*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // grid: n-by-n grid
	private WeightedQuickUnionUF grid;
	private int[][] siteStatus;
	
    // constructor: create n-by-n grid, with all sites blocked
    public Percolation(int n) {
	    // initialize grid with two additional virtual sites: top and bottom
	    grid = new WeightedQuickUnionUF(n*n + 2);
		// initialize site status
		siteStatus = new int[n][n];
		// block all sites
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++) {
			    siteStatus[i][j] = 0;
			}
		}
		printSites(n, "Grid and site status initialized:");
		// connect virtual top site to upper n sites
		for (int i = 1; i <= n; i++) {
		    grid.union(0, i);
		}
		printSites(n, "Top sites connected:");
		// connect virtual bottom site to lower n sites
		for (int i = n*n; i > (n*n - n); i--) {
		    grid.union((n*n + 1), i);
		}
		printSites(n, "Bottom sites connected:");
	}

	// method open: open site (row, col) if it is not already open
	public void open(int row, int col) {
	    if (siteStatus[row - 1][col - 1] == 0) {
		    siteStatus[row - 1][col - 1] = 1;
		}
		// check if there is a site on the left and connect to the it if open
	}

	// method isOpen: is site (row, col) open?
	public boolean isOpen(int row, int col) {
	    return (siteStatus[row - 1][col - 1] == 1);
	}
	/*
	// method isFull: is site (row, col) full?
	public boolean isFull(int row, int col) {
	}
	
	// method numberOfOpenSites: returns number of open sites
	public int numberOfOpenSites() {
	}
	
	// method percolates: does the system percolate?
	public boolean percolates() {
	}
	*/
	// method main: optional to test the code
	public static void main(String[] args) {
/*	    int n = 4;
	    Percolation percTest = new Percolation(n);
		System.out.println("Is (2, 4) open?: " + percTest.isOpen(2, 4));
		percTest.open(2, 4);
		System.out.println("Is (2, 4) open?: " + percTest.isOpen(2, 4));
		percTest.printSites(n, "Result:");
*/
        int count = 0;
		int row = 3;
		int col = 3;
		for (int i = 0; i < row; i++) {
		    for (int j = 0; j < col; j++) {
				count = i * i + j;
			    System.out.println("Count: " + count);
			}
		}
		System.out.println("Index: " + count);
	}
	
	// print sites
	private void printSites(int n, String desc) {
	    // print message
		System.out.println(desc);
		// print top site
		System.out.println("Top site: " + grid.find(0));
		// print all sites
		System.out.print("Sites:\t");
		for (int i = 1; i < (n*n + 1); i++) {
			System.out.print(grid.find(i) + "\t");
		}
		System.out.println();
		// site status
		System.out.print("Status:\t");
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++) {
			    System.out.print(siteStatus[i][j] + "\t");
			}
		}
		System.out.println();
		// print bottom site
		System.out.println("Bottom site: " + grid.find(n*n + 1));
		System.out.println();
	}
}