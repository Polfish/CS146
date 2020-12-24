package Notes.BinarySearchActivity;

/**
 * A class for executing binary searches in an array.
 */
public class BinarySearcher {

    /**
     * Finds a value in a range of a sorted array, using the binary search algorithm.
     *
     * @param a     the array in which to search
     * @param low   the low index of the range
     * @param high  the high index of the range
     * @param value the value to find
     * @return the index at which the value occurs, or -1 if it does not occur in the array
     */
    public static int search(int[] a, int low, int high, int value) {
        if (high >= 1) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                search(a, low, mid - 1, value);
            } else {
                search(a, mid + 1, high, value);
            }
        }
        return -1;

//        Non-recursive solution
//
//        while (low <= high) {
//            int mid = (low + high + 1) / 2;
//            if (a[mid] == value) {
//                return mid;
//            } else if (a[mid] > value) {
//                high = mid - 1;
//            } else {
//                low = mid + 1;
//            }
//        }
//        return -1;
    }
}