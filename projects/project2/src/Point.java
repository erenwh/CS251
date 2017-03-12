/*************************************************************************
 * Compilation:  javac Point.java
 * <p>
 * Description: An immutable data type for points in the plane.
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {
    // compare points by slope
    public final Comparator<Point> BY_SLOPE_ORDER = new BySlopeComparator();    // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public class BySlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point a, Point b) {
            //edge case

            if (x == a.x && y == a.y)//same point
                return -1;
            if (x == b.x && y == b.y)
                return 1;

            //infinite slope
            if (x - a.x == 0) {
                if (x - b.x == 0) {
                    return 0;
                } else
                    return 1;
            } else if (x - b.x == 0) {
                return -1;
            }
            if ((x - a.x < 0 || x - b.x < 0) && !(x - a.x < 0 && x - b.x < 0))
                return (y - b.y) * (x - a.x) - (y - a.y) * (x - b.x);
            else
                return (y - a.y) * (x - b.x) - (y - b.y) * (x - a.x);

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // are the 3 points p, q, and r collinear?
    public static boolean areCollinear(Point p, Point q, Point r) {
        return (q.y - p.y) * (q.x - r.x) == (q.y - r.y) * (q.x - p.x);
    }


    // are the 4 points p, q, r, and s collinear?
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        return ((q.y - p.y) * (q.x - r.x) == (q.y - r.y) * (q.x - p.x))
                && ((q.y - p.y) * (q.x - s.x) == (q.y - s.y) * (q.x - p.x));
    }

    // is this point lexicographically smaller than that one?
    public int compareTo(Point that) {
        if (this.x == that.x)
            return (this.y - that.y);
        else
            return (this.x - that.x);
    }


    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    // unit test
    /*public static void main(String[] args) {
        Point p1 = new Point (0,0);
        Point p2 = new Point (1,1);
        Point p3 = new Point (2,2);
        Point p4 = new Point (0,5);
        Point p5 = new Point (1,0);

        System.out.println(p2.compareTo(p3));
        System.out.println(p3.compareTo(p2));
        System.out.println(p2.compareTo(p2));
        System.out.println(p1.compareTo(p5));
        System.out.println("----");
        System.out.println(p2.BY_SLOPE_ORDER.compare(p1, p3));
        System.out.println("----");
    }*/

}
