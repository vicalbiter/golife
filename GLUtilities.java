import edu.princeton.cs.algs4.StdRandom; 

public class GLUtilities {
    
    public static boolean[][] randomGrid(int n, double prob) {
        boolean[][] grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double x = StdRandom.uniform(0.0, 1.0);
                if (x < prob) { grid[i][j] = true; }
                else { grid[i][j] = false; }
            }
        }
        return grid;
    }
    
    public static int runGL(GLGrid grid, int maxit) {
        int prev_alive = 0;
        int current_alive = 0;
        int count = 0;
        for (int i = 0; i < maxit; i++) {
            current_alive = grid.aliveCells();
            if (prev_alive == current_alive) {
                count++;
                if (count == 3) {
                    return i;
                }
            }
            else { count = 0; }
            prev_alive = current_alive;
            grid.nextState();
        }
        return maxit;
    }
    
    public static void main(String[] args) {
        int dim = 50;
        double prob = 0.1;
        GLGrid grid = new GLGrid(dim, randomGrid(dim, prob));
        System.out.println(runGL(grid, 1000));
    }
}