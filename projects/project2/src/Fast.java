
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Han Wang
 * @Date 2/13/17
 */
public class Fast {
    public static void main(String[] args) throws IOException {
        File out = new File("visualPoints.txt");

        FileWriter fw = new FileWriter(out);
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        Point[] copy = new Point[n]; // store target array to the current compare point;
        Point[] result = new Point[n]; // store 4 collinear points
        boolean coll = false; // boolean for determine whether 4 points are collinear
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            copy[i] = p;
        }
        //long start = System.currentTimeMillis();
        //System.out.println(Arrays.toString(points));
        Arrays.sort(points);
        //System.out.println(Arrays.toString(points));
        int pointCount;
        int inc;
        //Think of p as the origin.
        //For each other point q , determine the angle it makes with p.
        for (int i = 0; i < n; i++) {
            //System.arraycopy(points,1,copy,0,n-1);
            //Sort the points according to the angle they makes with p.
            Arrays.sort(copy, points[i].BY_SLOPE_ORDER);
            //Check if any 3 (or more) adjacent points in the sorted order have equal angles with p.
            for (int j = 1; j < n - 2; j += inc) {
                pointCount = 0;
                inc = 1;
                while ((j + pointCount < n - 2) &&
                        (Point.areCollinear(points[i], copy[j + pointCount],
                                copy[j + pointCount + 1], copy[j + pointCount + 2]))) {
                    result[pointCount] = copy[j + pointCount];
                    pointCount++;
                }
                if (pointCount > 0) {// If so, these points, together with p, are collinear.*/
                    result[pointCount] = copy[j + pointCount];
                    pointCount++;
                    result[pointCount] = copy[j + pointCount];
                    pointCount++;
                    Arrays.sort(result, 0, pointCount);
                    if (points[i].compareTo(result[0]) < 0) {
                        System.out.print((pointCount + 1) + ":" + points[i].toString());
                        fw.write((pointCount + 1) + ":" + points[i].toString());
                        for (int k = 0; k < pointCount; k++) {
                            System.out.print(" -> " + result[k].toString());
                            fw.write(" -> " + result[k].toString());
                        }
                        System.out.println();
                        fw.write("\n");
                    }
                    inc = pointCount;
                }
            }
        }
        fw.close();
        //long now = System.currentTimeMillis();
        //System.out.println("Elapsed Time = " + (now - start) / 1000.0);
    }
}
