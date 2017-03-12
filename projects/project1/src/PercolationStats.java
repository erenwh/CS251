import java.util.ArrayList;

/**
 * Created by HanWang on 2/7/17.
 */
public class PercolationStats {
    private int N;
    private int T;
    private String type;
    private double threshold;
    private double[] time, thresholds;

    public PercolationStats(int n, int t, String ty) {
        N = n;
        T = t;
        type = ty;
        time = new double[T];
        thresholds = new double[T];
        for (int k = 0; k < T; k++) {
            long start = System.currentTimeMillis();
            if (type.equals("slow")) {
                PercolationQuick p = new PercolationQuick(N);
                threshold = 0;

                while (!p.percolates()) {
                    int i = (int)StdRandom.uniform(0, N);
                    int j = (int)StdRandom.uniform(0, N);

                    if (!p.isOpen(i, j)) {
                        p.open(i, j);
                        threshold++;
                    }
                }

            } else if (type.equals("fast")) {
                Percolation p = new Percolation(N);
                threshold = 0;

                while (!p.percolates()) {
                    int i = (int)StdRandom.uniform(0, N);
                    int j = (int)StdRandom.uniform(0, N);

                    if (!p.isOpen(i, j)) {
                        p.open(i, j);
                        threshold++;
                    }
                }

            }


            long now = System.currentTimeMillis();
            this.time[k] = (now - start) / 1000.0;
            this.thresholds[k] = (threshold) / (N * N);
        }
    }
    public void stats() {
        double meanTime = mean(time);
        double meanThreshold = mean(thresholds);
        double stdDevThreshold = stddev(thresholds);
        double stdDevTime = stddev(time);
        double totalTime = 0;
        for (int i = 0; i < T; i++) {
            totalTime += time[i];
        }
        System.out.println("mean threshold=" + meanThreshold);
        System.out.println("std dev=" + stdDevThreshold);
        System.out.println("time=" + totalTime);
        System.out.println("mean time=" + meanTime);
        System.out.println("stddev time=" + stdDevTime);
    }
    public double mean(double results[]) {
        return StdStats.mean(results);
    }

    public double stddev(double results[]) {
        return StdStats.stddev(results);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        String typ = args[2];
        PercolationStats ps = new PercolationStats(n,t,typ);
        ps.stats();
    }

}
