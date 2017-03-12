import java.io.*;
import java.util.Scanner;

/**
 * Created by HanWang on 2/6/17.
 */
public class PercolationVisualizer extends Percolation {
    /**
     * Create a new n by n grid where all cells are initially blocked
     *
     * @param n
     */
    private PercolationVisualizer(int n) {
        super(n);
    }

    public static void main(String[] args) throws IOException {
        //Scanner s = new Scanner(new File("/Users/HanWang/IdeaProjects/cs251/project1/src/DemoVisYes.txt"));

        File output = new File("visualMatrix.txt");
        output.createNewFile();

        FileWriter fw = new FileWriter(output);

        int n = StdIn.readInt();
        PercolationVisualizer pv = new PercolationVisualizer(n);
        fw.write(n + "\n");
        System.out.print(n + "\n");
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            pv.open(x, y);


            //Formating result
            for (int i = n -1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    if (pv.getGrid()[i][j] == 1) {
                        if (pv.isFull(i, j)) {
                            fw.write("2");
                            System.out.print("2");
                        } else {
                            fw.write("1");
                            System.out.print("1");
                        }
                    } else {
                        fw.write("0");
                        System.out.print("0");
                    }
                    if (j != n - 1) {
                        fw.write(" ");
                        System.out.print(" ");
                    }
                }
                fw.write("\n");
                System.out.print("\n");
                fw.flush();
            }
            fw.write("\n");
            System.out.print("\n");

        }

        fw.close();
    }
}
