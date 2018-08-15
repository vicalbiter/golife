import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom; 

public class GLUtilities {
    
    // Build a random n x n grid, with a predefined density, i.e the
    // probability (ranging from 0.0 to 1.0) of a cell being alive at initialization
    public boolean[][] randomGrid(int n, double density) {
        boolean[][] grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double x = StdRandom.uniform(0.0, 1.0);
                if (x < density) { grid[i][j] = true; }
                else { grid[i][j] = false; }
            }
        }
        return grid;
    }
    
    // Build a n x n grid with data coming from standard input (e.g. a text file) 
    // Data should come in the format "n grid[0] grid[1]...grid[n-1]"
    public boolean[][] standardInputGrid(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        boolean[][] grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = in.readBoolean();
            }
        }
        return grid;
    }
    
    // Build a n x n grid whose starting living cells are confined to a small m x m
    // square sitting in the middle of the bigger grid. The "generator" should be a number
    // from 0 to pow(2, m). This shall be transformed into a binary number. The resulting
    // characters are then plugged into the m x m grid one by one.
    public boolean[][] interestingGrid(int n, int m, int generator) {
        boolean[] genbool = toBoolean(generator, m*m);
        boolean[][] grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        int lower = (n - m) / 2;
        int upper = (n + m) / 2;
        int k = 0;
        for (int i = lower; i < upper; i++) {
            for (int j = lower; j < upper; j++) {
                grid[i][j] = genbool[k];
                k++;
            }
        }
        return grid;
    }
    
    // Helper method to transform an integer into a binary array
    private static boolean[] toBoolean(int num, int dim) {
        int numint = num;
        boolean[] numbool = new boolean[dim];
        for (int i = 0; i < dim; i++) {
            if (numint >= 1) {
                if (numint % 2 == 0) { numbool[i] = false; }
                else { numbool[i] = true; }
            }
            else { numbool[i] = false; }
            numint = numint / 2;
            System.out.println(numint);
        }
        return numbool;
    }
    
    // Run a GoL within a predefined maximum number of state changes. Stop when the grid has
    // stabilized (i.e. when all cells are static or dead).
    public int runGL(GLGrid grid, int maxit) {
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
        boolean[] test = toBoolean(7, 5);
        for (int i = 0; i < test.length; i++) {
            if(test[i]) { System.out.print("1"); }
            else { System.out.print("0"); }
        }
    }
}