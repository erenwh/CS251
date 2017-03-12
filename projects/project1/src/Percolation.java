import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by HanWang on 2/1/17.
 */
public class Percolation {
    private int[][] grid; // grid map
    private int n; // store size of grid
    private WeightedQuickUnionUF uf;


    /**
     * Create a new n by n grid where all cells are initially blocked
     *
     * @param n
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("grid size error!");
        }
        this.n = n;
        grid = new int[n][n];
        uf = new WeightedQuickUnionUF(n * n+2); //the 2 additional slots are for the virtual top and bottom


    }

    public int getN() {
        return n;
    }

    /**
     * Open the site at coordinate (x,y), where x represents the row number and y the column number. For consistency
     * purposes, (0,0) will be the bottom-left cell of the grid and (n-1,n-1) will be on the top-right. The graphical
     * capabilities discussed later assume a similar convention.
     *
     * @param x
     * @param y
     */
    public void open(int x, int y) {
        //int inverse = n - x - 1;
        grid[x][y]=1;
        if (x == 0) {
            uf.union(y,n*n);
        }
        if (x == n - 1) {
            uf.union((n - 1)*n + y, n*n+1);
        }
        //top
        unionTo(x,y,x-1,y);
        //right
        unionTo(x,y,x,y+1);
        //bottom
        unionTo(x,y,x+1,y);
        //left
        unionTo(x,y,x,y-1);

    }

    private void unionTo(int x1, int y1, int x2, int y2) {
        if (!valid(x2,y2))
            return;
        if (grid[x2][y2] == 0)
            return;
        uf.union(
                x1 * n + y1, x2 * n + y2
        );
    }

    /**
     * //Returns true if cell (x,y) is open due to a previous call to open(int x, int y)
     *
     * @param x
     * @param y
     * @return Returns true if cell (x,y) is open due to a previous call to open(int x, int y)
     */
    public boolean isOpen(int x, int y) {
        /*//int inverse = n - x - 1;
        if (!valid(x,y)) {
            throw new IndexOutOfBoundsException();
        }
        else {*/
            return grid[x][y] == 1;


    }

    /**
     * Returns true if there is a path from cell (x,y) to the surface (i.e. there is percolation up to this cell)
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isFull(int x, int y) {
        //int inverse = n - x - 1;
        return uf.connected(0,x*n+y);
    }

    /*public boolean isFilled(int x, int y) {
        valid(n - x - 1, y);
        return grid[n - x - 1][y] == 2;
    }*/

    /**
     * Analyzes the entire grid and returns true if the whole system percolates
     *
     * @return returns true if the whole system percolates
     */
    public boolean percolates() {
        return uf.connected(n*n+1,0);
    }

    /**
     * check if coordinate x,y is valid and not out of bound
     *
     * @param x
     * @param y
     */
    private boolean valid(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);

    }

    public int[][] getGrid() {
        return grid;
    }

    /**
     * reflect the 2-dimensional coordinate to array index
     *
     * @param
     * @param
     * @return
     */
    /*private int xyTo1D(int x, int y) {
        valid(x, y);
        return ((n - x - 1) * n + y);

    }*/

   /* private boolean ajacent(int x, int y, int x2, int y2) {
        valid(x, y);
        valid(x2, y2);
        return (grid[x][y] == 1 && grid[x2][y2] == 1);
    }*/

   private void print() {
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               System.out.print(grid[i][j] + " ");
           }
           System.out.println();
       }
   }


    /**
     * Create a main method that reads a description of a grid from standard input and validates if the system described
     * percolates or not, printing to standard output a simple "Yes" or "No" answer.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        int size = StdIn.readInt();
        Percolation p = new Percolation(size);

        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            //System.out.println(x);
            int y = StdIn.readInt();
            //System.out.println(y);
            p.open(x, y);
        }


        if (p.percolates()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        //p.print();
    }

}
