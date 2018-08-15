import java.util.Scanner;
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
    // Data should come in the format "n format data"
    // Format 0 will read data in the form grid[0] grid[1]...grid[n-1]
    // Format 1 will read data in the form of an "interesting grid" (m seed)
    public boolean[][] standardInputGrid(String args) {
        Scanner in = new Scanner(args);
        int n = in.nextInt();
        int format = in.nextInt();
        boolean[][] grid = new boolean[n][n];
        if (format == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = in.nextBoolean();
                }
            }
        }
        else {
            int m = in.nextInt();
            int seed = in.nextInt();
            grid = interestingGrid(n, m, seed);
        }
        return grid;
    }
    
    // Build a n x n grid whose starting living cells are confined to a small m x m
    // square sitting in the middle of the bigger grid. The "generator" should be a number
    // from 0 to pow(2, m). This shall be transformed into a binary number. The resulting
    // characters are then plugged into the m x m grid one by one.
    public boolean[][] interestingGrid(int n, int m, int seed) {
        boolean[] seedbool = toBoolean(seed, m*m);
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
                grid[i][j] = seedbool[k];
                k++;
            }
        }
        return grid;
    }
    
    // Helper method to transform an integer into a binary array
    public boolean[] toBoolean(int num, int dim) {
        int numint = num;
        boolean[] numbool = new boolean[dim];
        for (int i = 0; i < dim; i++) {
            if (numint >= 1) {
                if (numint % 2 == 0) { numbool[i] = false; }
                else { numbool[i] = true; }
            }
            else { numbool[i] = false; }
            numint = numint / 2;
        }
        return numbool;
    }
    
    // Run a GoL with a predefined maximum number of state changes. Stop when the grid has
    // stabilized (i.e. when all cells are static or dead). Used for GLStats.
    public int runGL(GLGrid grid, int maxit) {
        int prev_alive = 0;
        int current_alive = 0;
        int count = 0;
        for (int i = 0; i < maxit; i++) {
            current_alive = grid.aliveCells();
            if (prev_alive == current_alive) {
                count++;
                if (count == 9) {
                    return i;
                }
            }
            else { count = 0; }
            prev_alive = current_alive;
            grid.nextState();
        }
        return maxit;
    }    
    
    // Method to check if two grids are equal
    public boolean equalGrid(int n, GLGrid a, GLGrid b) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a.isAlive(i, j) == b.isAlive(i, j)) {
                    continue;
                }
                else { return false; }
            }
        }
        return true;
    }
    
    // Method to copy one grid into another one
    public GLGrid copyGrid(int n, GLGrid glgrid) {
        boolean[][] grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = glgrid.isAlive(i, j);
            }
        }
        return new GLGrid(n, grid);
    }
    
    public int staticPatternSearch (int n, int m, int maxit) {
        int patterndim = (int) Math.pow(2, m*m);
        for (int i = 1; i < patterndim; i++) {
            GLGrid grid = new GLGrid(n, interestingGrid(n, m, i));
            GLGrid prev_grid = copyGrid(n, grid);
            grid.nextState();
            for (int j = 0; j < maxit; j++) {
                if (equalGrid(n, grid, prev_grid)) {
                    //System.out.println("Found static pattern with seed: " + i);
                    System.out.println(n + " " + 1 + " " + m + " " + i);
                    break;
                }
                grid.nextState();
            }   
        }
        return 0;
    }
    
    public static void main(String[] args) {
        GLUtilities utilities = new GLUtilities();
        utilities.staticPatternSearch(20, 4, 10);
    }
}