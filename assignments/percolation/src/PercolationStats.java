/*
** AUTHOR:	rohidesh
** PROBLEM:	http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
** DESC:	Program to get the PercolationStats.
*/

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int n;
	private int trials;
	// percolation thresholds
	private double[] pt;
	private double numerator;
	private double denominator;
	private double mean = 0.0;
	private double stddev = 0.0;
	private double confidenceLo = 0.0;
	private double confidenceHi = 0.0;
	
    // constructor: perform trial experiments on an n-by-n grid
	public PercolationStats(int n, int trials) {
	    // check if n and trials are valid
		if (n <= 0 || trials <= 0) {
		    throw new IllegalArgumentException("Value of n, trials " + n + ", " + trials + " not acceptable.");
		}
		this.n = n;
		this.trials = trials;
		denominator = (double) n*n;
		// initialize pt
		pt = new double[trials];
		// run trials
		for (int i = 0; i < trials; i++) {
		    Percolation p = new Percolation(n);
		    // keep opening sites and checking if the system percolates
		    while (!p.percolates()) {
		        p.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
		    }
			numerator = (double) p.numberOfOpenSites();
			pt[i] = numerator/denominator;
		}
	}
	
	// method mean: sample mean of percolation threshold
	public double mean() {
	    for (int i = 0; i < pt.length; i++) {
		    mean += pt[i];
		}
		mean /= this.trials;
	    return mean;
	}
	
	// method stddev: sample standard deviation of percolation threshold
	public double stddev() {
	    for (int i = 0; i < pt.length; i++) {
		    stddev += (pt[i] - this.mean())*(pt[i] - this.mean());
		}
	    stddev /= (this.trials - 1);
		stddev = Math.sqrt(stddev);
	    return stddev;
	}
	
	// method confidenceLo: low endpoint of 95% confidence interval
	public double confidenceLo() {
	    confidenceLo = this.mean() - ((1.96 * this.stddev())/(Math.sqrt(this.trials)));
	    return confidenceLo;
	}
	
	// method confidenceHi: high endpoint of 95% confidence interval
	public double confidenceHi() {
	    confidenceHi = this.mean() + ((1.96 * this.stddev())/(Math.sqrt(this.trials)));
	    return confidenceHi;
	}
	
	// method main: for testing
	public static void main(String[] args) {
	    int n, trials;
		n = Integer.parseInt(args[0]);
		trials = Integer.parseInt(args[1]);
	    PercolationStats percStats = new PercolationStats(n, trials);
		System.out.println("mean\t\t\t\t=\t" + percStats.mean());
		System.out.println("stddev\t\t\t\t=\t" + percStats.stddev());
		System.out.println("95% confidence interval\t\t=\t[" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
	}
}