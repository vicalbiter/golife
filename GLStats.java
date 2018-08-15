import edu.princeton.cs.algs4.StdStats;

public class GLStats {
    
    private int n;
    private int trials;
    private double density;
    private int max_gridit = 1000;
    
    public GLStats(int n, int mc_trials, int pmin, int pmax, int step) {
        GLUtilities utilities = new GLUtilities();
        double density = pmin*0.1;
        int sumt = 0;
        for (int i = pmin; i <= pmax; i = i + step) {
            for (int j = 0; j < mc_trials; j++) {
                GLGrid grid = new GLGrid(n, utilities.randomGrid(n, density));
                int time = utilities.runGL(grid, max_gridit);
                sumt = sumt + time;
                System.out.println(density + "," + time);
            }
            double talive = sumt/mc_trials;
            //System.out.println("Density: " + density + " Average t: " + talive);
            talive = 0;
            sumt = 0;
            density = density + (step/100.0);
        }
    }
    
    public static void main(String[] args) {
        GLStats stats = new GLStats(100, 100, 1, 100, 10);
    }
}