import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Han Wang
 * @Date 2/17/17
 */
public class Brute {
    public static void main(String[] args) throws IOException {
        File out = new File("visualPoints.txt");

        FileWriter fw = new FileWriter(out);

        int n = StdIn.readInt();
        //System.out.println(n);
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            //System.out.println("x is " + x);
            int y = StdIn.readInt();
            //System.out.println("y is " + y);
            Point p = new Point(x, y);
            points[i] = p;
        }
        /*for (int i = 0; i < n; i++) {
            System.out.println("Point[" + i + "] is (" + points[i].getX() + "," +
                    points[i].getY() + ")");
        }*/
        //long start = System.currentTimeMillis();
        Arrays.sort(points);
        int p1, p2, p3, p4;
        for (p1 = 0; p1 < n; p1++) {
            for (p2 = p1 + 1; p2 < n; p2++) {
                for (p3 = p2 + 1; p3 < n; p3++) {
                    for (p4 = p3 + 1; p4 < n; p4++) {
                        if (Point.areCollinear(points[p1], points[p2], points[p3], points[p4])) {
                            System.out.print("4:");
                            fw.write("4:");
                            System.out.println(points[p1].toString() + " -> " +
                                    points[p2].toString() + " -> " +
                                    points[p3].toString() + " -> " +
                                    points[p4].toString());
                            fw.write(points[p1].toString() + " -> " +
                                    points[p2].toString() + " -> " +
                                    points[p3].toString() + " -> " +
                                    points[p4].toString() + "\n");
                        }
                    }
                }
            }
        }
        fw.close();
        //long now = System.currentTimeMillis();
        //System.out.println("Elapsed Time = " + (now - start) / 1000.0);

    }
}
