import java.awt.Font;
import edu.princeton.cs.algs4.StdDraw;

public class GLVisualizer {
    
    private static final int DELAY = 200;
    
    public static void draw(int n, GLGrid grid) {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setXscale(-0.05*n, 1.05*n);
        StdDraw.setYscale(-0.05*n, 1.05*n);   // leave a border to write text
        StdDraw.filledSquare(n/2.0, n/2.0, n/2.0);
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (grid.isAlive(row-1, col-1)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                }
                else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
            }
        }
    }
    
    public static void updateDraw(int n, GLGrid grid) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (grid.cellChange(row-1, col-1)) {
                    if (grid.isAlive(row-1, col-1)) {
                        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                    }
                    StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
                }
            }
        }
    }
    
    public static void main (String[] args) {
        int dim = 50;
        double prob = 0.1;
        GLUtilities utilities = new GLUtilities();
        GLGrid glgrid = new GLGrid(dim, utilities.randomGrid(dim, prob));
        draw(dim, glgrid);
        StdDraw.show();
        while (true) {
            updateDraw(dim, glgrid);
            StdDraw.show();
            System.out.println(glgrid.aliveCells());
            StdDraw.pause(DELAY);
            glgrid.nextState();
        }
    }
}