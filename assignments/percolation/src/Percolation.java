/*
** AUTHOR:    rohidesh
** PROBLEM:    http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
** DESC:    Program to model the Percolation system.
*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // variables
    private final WeightedQuickUnionUF grid;
    private final int[][] siteMap;
    private boolean[] siteStatus;
    private int numberOfOpenSites = 0;
    private final int n;
    // constructor: create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        // check if n is > 0
        if (n <= 0) {
            throw new IllegalArgumentException("Value of n: " + n + " not acceptable.");
        }
        this.n = n;
        // initialize grid with two additional virtual sites: top and bottom
        grid = new WeightedQuickUnionUF(n*n + 2);
        // initialize site map and status
        siteMap = new int[n][n];
        siteStatus = new boolean[n*n];
        // populate siteMap
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                siteMap[i][j] = counter;
                counter++;
            }
        }
        // block all sites
        for (int i = 0; i < n*n; i++) {
            siteStatus[i] = false;
        }
/*
        // connect virtual top site to upper n sites
        for (int i = 1; i <= n; i++) {
            grid.union(0, i);
        }

        // connect virtual bottom site to lower n sites
        for (int i = n*n; i > (n*n - n); i--) {
            grid.union((n*n + 1), i);
        }
*/
    }

    // method open: open site (row, col) if it is not already open
    public void open(int row, int col) {
        // check if row and col values are valid
        checkRowCol(row, col);
        if (!siteStatus[siteMap[row - 1][col - 1]]) {
            siteStatus[siteMap[row - 1][col - 1]] = true;
            numberOfOpenSites++;
        }
        // connect to the virtual top site if the site being opened is in the uppermost row
        if (row == 1) {
            grid.union(0, siteMap[row - 1][col - 1] + 1);
        }
        // connect to the virtual bottom site if the site being opened is in the lowermost row
        if (row == this.n) {
            grid.union((this.n * this.n + 1), siteMap[row - 1][col - 1] + 1);
        }
        // check if there is a site on the left and connect to the it if that is open
        if ((col - 2) >= 0 && siteStatus[siteMap[row - 1][col - 2]]) {
            grid.union(siteMap[row - 1][col - 1] + 1, siteMap[row - 1][col - 2] + 1);
        }
        // check if there is a site on the right and connect to the it if that is open
        if (col < siteMap[0].length && siteStatus[siteMap[row - 1][col]]) {
            grid.union(siteMap[row - 1][col - 1] + 1, siteMap[row - 1][col] + 1);
        }
        // check if there is a site above this and connect to the it if that is open
        if ((row - 2) >= 0 && siteStatus[siteMap[row - 2][col - 1]]) {
            grid.union(siteMap[row - 1][col - 1] + 1, siteMap[row - 2][col - 1] + 1);
        }
        // check if there is a site below this and connect to the it if that is open
        if (row < siteMap.length && siteStatus[siteMap[row][col - 1]]) {
            grid.union(siteMap[row - 1][col - 1] + 1, siteMap[row][col - 1] + 1);
        }
    }
    
    // method isOpen: is site (row, col) open?
    public boolean isOpen(int row, int col) {
        // check if row and col values are valid
        checkRowCol(row, col);
        return siteStatus[siteMap[row - 1][col - 1]];
    }

    // method isFull: is site (row, col) full?
    public boolean isFull(int row, int col) {
        // check if row and col values are valid
        checkRowCol(row, col);
        // a site can be considered to be full if it is connected to the virtual top site
        return grid.connected(0, siteMap[row - 1][col - 1] + 1);
    }

    // method numberOfOpenSites: returns number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // method percolates: does the system percolate?
    public boolean percolates() {
        // a system can be said to percolate if the virtual top site is connected to the virtual bottom site
        return grid.connected(0, (this.n * this.n) + 1);
    }

    private void checkRowCol(int row, int col) {
        // check if row and col values are valid
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException("Value of (row, col): (" + row + ", " + col + ") not acceptable.");
        }
    }
    // method main: optional to test the code
    public static void main(String[] args) {
        int n = 0;
        n = Integer.parseInt(args[0]);
        Percolation percTest = new Percolation(n);
        // keep opening sites and checking if the system percolates
        while (!percTest.percolates()) {
            percTest.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
        }
        double numerator = (double) percTest.numberOfOpenSites();
        double denominator = (double) (n*n);
        double percThres = numerator/denominator;
        System.out.println("Percolation threshold i.e. numberOfOpenSites/totalNumberOfSites:\t" + percThres);
    }

}