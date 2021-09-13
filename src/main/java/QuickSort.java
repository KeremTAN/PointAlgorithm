//package HW_2;

import java.awt.geom.Point2D;

public class QuickSort {


    /**
     * Default Contructor
     */
    public QuickSort() {
        //Empty constructor --- do nothing
    }

    /**
     * The main function that implements QuickSort
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @param orderBy    --> compareX or compareY
     *                   compareX: sort minimum to maximum arr[] by X point
     *                   compareY: sort minimum to maximum arr[] by Y point
     */
    public void sort(Point2D.Double[] arr, int startIndex, int lastIndex, String orderBy) {
        int index;
        /**  Divide-and-Conquer strategy.*/
        if (lastIndex-startIndex>1) {
            if (orderBy.equalsIgnoreCase("compareX")) {
                index = partitionX(arr, startIndex, lastIndex);
                sort(arr, startIndex, (index-1), "compareX");
                sort(arr, (index), lastIndex, "compareX");
            }
            else if (orderBy.equalsIgnoreCase("compareY")) {
                index = partitionY(arr, startIndex, lastIndex);
                sort(arr, startIndex, (index-1), "compareY");
                sort(arr, (index), lastIndex, "compareY");
            }
        }
        /** ----------------------------- */
    }

    /**
     * A utility function to swap two elements
     *
     * @param arr --> Array to be sorted
     * @param i   --> first index
     * @param j   --> second index
     */
    private void swap(Point2D.Double[] arr, int i, int j) {
        Point2D.Double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Get Median of 3 order by Point.X
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    /**  Doğru çalışıyor */
    private Point2D.Double getMedianX(Point2D.Double[] arr, int left, int right) {
        int center = (left + right) / 2;
        if (arr[left].x > arr[center].x) swap(arr, left, center);
        if (arr[left].x > arr[right].x) swap(arr, left, right);
        if (arr[center].x > arr[right].x) swap(arr, center, right);
        swap(arr, center, (right - 1));
        return arr[(right - 1)];
    }

    /**
     * Get Median of 3 order by Point.Y
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianY(Point2D.Double[] arr, int left, int right) {
        int center = (left + right) / 2;
        if (arr[left].y > arr[center].y) swap(arr, left, center);
        if (arr[left].y > arr[right].y) swap(arr, left, right);
        if (arr[center].y > arr[right].y) swap(arr, center, right);
        swap(arr, center, (right - 1));
        return arr[(right - 1)];
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.X
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionX(Point2D.Double[] arr, int startIndex, int lastIndex) {
        Point2D.Double pivot = getMedianX(arr, startIndex, lastIndex);
        int i = startIndex, j = lastIndex-1;
        while (true) {
            while (arr[++i].x < pivot.x) ;
            while (arr[--j].x > pivot.x) ;
            if (i < j) swap(arr, i, j);
            else break;
        }
        swap(arr, i, lastIndex - 1);
        return i;
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.Y
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionY(Point2D.Double[] arr, int startIndex, int lastIndex) {
        Point2D.Double pivot = getMedianY(arr, startIndex, lastIndex);
        int i = startIndex, j = lastIndex-1;
        while (true) {
            while (arr[++i].y < pivot.y) ;
            while (arr[--j].y > pivot.y) ;
            if (i < j) swap(arr, i, j);
            else break;
        }
        swap(arr, i, lastIndex - 1);
        return i;
    }

}
