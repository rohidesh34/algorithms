/*
** AUTHOR:    rohidesh
** PROBLEM:    http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
** DESC:    Program to get the PercolationStats.
*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    // percolation thresholds
    private final double[] pt;
    private double mean = 0.0;
    private double stddev = 0.0;
	public static final double CONFIDENCE_95 = 1.96;
    
    // constructor: perform trial experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // check if n and trials are valid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Value of n, trials " + n + ", " + trials + " not acceptable.");
        }
        this.trials = trials;
        double numerator;
        double denominator = (double) n*n;
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
        return StdStats.mean(pt);
    }
    
    // method stddev: sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(pt);
    }
    
    // method confidenceLo: low endpoint of 95% confidence interval
    public double confidenceLo() {
        double confidenceLo = 0.0;
        confidenceLo = this.mean() - ((CONFIDENCE_95 * this.stddev())/(Math.sqrt(this.trials)));
        return confidenceLo;
    }
    
    // method confidenceHi: high endpoint of 95% confidence interval
    public double confidenceHi() {
        double confidenceHi = 0.0;
        confidenceHi = this.mean() + ((CONFIDENCE_95 * this.stddev())/(Math.sqrt(this.trials)));
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