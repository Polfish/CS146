package Homework.Project2.Code.cs146F20.Chon.project2;

/**
 * Finds the maximum subarray using the Divide and Conquer Algorithm
 */
public class DivideAndConquerAlgorithm {

    // The test case
    private int[] data;

    public DivideAndConquerAlgorithm(int[] data) {
        this.data = data;
    }

    /**
     * Use the Divide and Conquer Algorithm to find the maximum subarray by: Splitting the array
     * into two halves, then Find the max sum of the left and right and the max sum of an array that
     * crosses the midpoint
     *
     * @param low  first index
     * @param high last index
     * @return max subarray of an array
     */
    public MaxSubArrayResults findMaximumSubArray(int low, int high) {
        if (high == low) {
            // Accounts for when the maximum sum is 0 and less
            if (data[low] <= 0) {
                return new MaxSubArrayResults(0, 0, -1);
            }

            return new MaxSubArrayResults(data[low], low, high);
        }

        int mid = (low + high) / 2;
        // Max subarray of left half
        MaxSubArrayResults left = findMaximumSubArray(low, mid);
        // Max subarray of right half
        MaxSubArrayResults right = findMaximumSubArray(mid + 1, high);
        // Max crossing subarray that goes over midpoint
        MaxSubArrayResults crossing = findMaxCrossingSubArray(low, mid, high);

        if (left.getMaxSum() < 0 && right.getMaxSum() < 0 && crossing.getMaxSum() < 0) {
            return new MaxSubArrayResults(0, 0, -1);
        }

        if (left.getMaxSum() >= right.getMaxSum() && left.getMaxSum() >= crossing.getMaxSum()) {
            return left.getMaxSum() < 0 ? new MaxSubArrayResults(0, 0, -1) : left;
        } else if (right.getMaxSum() >= left.getMaxSum() && right.getMaxSum() >= crossing
                .getMaxSum()) {
            return right.getMaxSum() < 0 ? new MaxSubArrayResults(0, 0, -1) : right;
        } else {
            return crossing.getMaxSum() < 0 ? new MaxSubArrayResults(0, 0, -1) : crossing;
        }
    }

    /**
     * Used to find the max subarray that crosses the midpoint by adding the max sum of the left and
     * right half
     *
     * @param low  first index
     * @param mid  mid index
     * @param high last index
     * @return max subarray that crosses the midpoint
     */
    public MaxSubArrayResults findMaxCrossingSubArray(int low, int mid, int high) {
        // Max of left half
        int leftSum = Integer.MIN_VALUE;
        // Used to add up each element
        int sum = 0;
        // Starting index of the crossing subarray
        int maxLeft = 0;

        for (int i = mid; i >= low; i--) {
            sum += data[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        // Max of right half
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        // Ending index of the crossing subarray
        int maxRight = 0;

        for (int i = mid + 1; i <= high; i++) {
            sum += data[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new MaxSubArrayResults(leftSum + rightSum, maxLeft, maxRight);
    }
}
