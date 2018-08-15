import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class GLGrid {
    
    private boolean[][] grid;
    private boolean[][] nextgrid;
    private boolean[][] changes;
    private int n;
    private int alive = 0;
    
    public GLGrid(int n, boolean[][] grid) {
        this.n = n;
        this.grid = new boolean[n][n];
        this.nextgrid = new boolean[n][n];
        this.changes = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]) { alive++; }
                this.grid[i][j] = grid[i][j];
                this.nextgrid[i][j] = false;
                this.changes[i][j] = false;
            }
        }
    }
    
    public boolean isAlive(int row, int col) {
        return grid[row][col];
    }
    
    public int aliveCells() {
        return alive;
    }
    
    private int aliveNeighbors(int row, int col) {
        int neighbors = 0;
        if (isAlive(validate(row - 1), validate(col))) { neighbors++; }
        if (isAlive(validate(row - 1), validate(col + 1))) { neighbors++; }
        if (isAlive(validate(row), validate(col + 1))) { neighbors++; }
        if (isAlive(validate(row + 1), validate(col + 1))) { neighbors++; }
        if (isAlive(validate(row + 1), validate(col))) { neighbors++; }
        if (isAlive(validate(row + 1), validate(col - 1))) { neighbors++; }
        if (isAlive(validate(row), validate(col - 1))) { neighbors++; }
        if (isAlive(validate(row - 1), validate(col - 1))) { neighbors++; }
        return neighbors;
    }
    
    private int validate(int value) {
        if (value == -1) { return n - 1; }
        else if (value == n) { return 0; }
        else { return value; }
    }
    
    public void nextState() {
        alive = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isAlive(i, j)) {
                    int neighbors = aliveNeighbors(i, j);
                    if (neighbors < 2 || neighbors > 3) {
                        nextgrid[i][j] = false;
                    }
                }
                else {
                    int neighbors = aliveNeighbors(i, j);
                    if (neighbors == 3) {
                        nextgrid [i][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                changes[i][j] = grid[i][j] != nextgrid[i][j];
                grid[i][j] = nextgrid[i][j];
                if (grid[i][j]) { alive++; }
            }
        }
    }
    
    public boolean cellChange(int row, int col) {
        return changes[row][col];
    }
    
    public void printGrid(int n, boolean[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]) { System.out.print("1 "); }
                else { System.out.print("0 "); }
            }
            System.out.println("");
        }
    }
    
    public static void main(String[] args) {
        //GLGrid grid = new GLGrid(10);
    }
}