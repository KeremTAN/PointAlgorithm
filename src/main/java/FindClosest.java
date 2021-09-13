//package HW_2;

import java.awt.geom.Point2D;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /**
     * Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points) {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");
        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);
        //*********************************do nothing***************************************//
    }

    /**
     * Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair() {
        return this.closestPointPair;
    }

    /**
     * Main method for calculate and return closest point pair
     *
     * @param p          --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex  --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex) {
        PointPair ret, temp;
        /** FFHJSGFŞLHFŞHLKFHJNÇGFKNHGFŞNKMGHLN GKHNMG HNKGHLNMGHLGKH */
        int index;
        ret = getClosestPointPair(p[startIndex], p[startIndex + 1], p[startIndex + 2]);
        /**  Divide-and-Conquer strategy.*/
        if (lastIndex - startIndex > 3) {
            index = (lastIndex + startIndex) / 2;
            temp = calculateClosestPointPair(p, startIndex, index - 1);
            ret = ret.getDistance() < temp.getDistance() ? ret : temp;
            ret = stripClosest(p, (index - 1 - startIndex), ret);
            //--------------------------------------------------------------------
            temp = calculateClosestPointPair(p, index, lastIndex);
            ret = ret.getDistance() < temp.getDistance() ? ret : temp;
            ret = stripClosest(p, (lastIndex - index), ret);
        }
        /** ----------------------------- */
        else if (lastIndex - startIndex == 3) {
            temp = getClosestPointPair(p[startIndex], p[startIndex + 1], p[lastIndex]);
            ret = ret.getDistance() < temp.getDistance() ? ret : temp;
            ret = stripClosest(p, lastIndex - startIndex, ret);
        }
        else {
            double maxDistance = Double.MAX_VALUE;
            Point2D.Double p1 = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);
            Point2D.Double p2 = new Point2D.Double(0, 0);
            ret = new PointPair(p1, p2);
            for (int i = 0; i < lastIndex; i++) {
                PointPair pointPair = new PointPair(p[i], p[i + 1]);
                if (maxDistance > pointPair.getDistance()) {
                    maxDistance = pointPair.getDistance();
                    ret = pointPair;
                }
            }
            temp = stripClosest(p, lastIndex - startIndex, ret);
            return ret.getDistance() < temp.getDistance() ? ret : temp;
        }


        return ret;
    }

    /**
     * calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        PointPair coupled1 = new PointPair(p1, p2);
        PointPair coupled2 = new PointPair(p2, p3);
        PointPair coupled3 = new PointPair(p1, p3);
        PointPair ret;
        ret = getClosestPointPair(coupled1, coupled2);
        ret = getClosestPointPair(coupled3, ret);
        return ret;

    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2) {
        if (p1.getDistance() < p2.getDistance())
            return p1;
        else return p2;
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip        --> point array
     * @param size         --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double strip[], int size, PointPair shortestLine) {
        PointPair pair;
        quicksort.sort(strip, 0, size, "compareY");

        for (int i = 0; i < size - 1; ++i)
            if ((strip[i].getY() - strip[i + 1].getY()) < shortestLine.getDistance()) {
                pair = new PointPair(strip[i], strip[i + 1]);
                if (pair.getDistance() < shortestLine.getDistance())
                    shortestLine = pair;
            }
        return shortestLine;
    }

}