package Homework.Project2.Code.cs146F20.Chon.project2;

/**
 * Finds the maximum subarray using the Brute Force Algorithm
 */
public class BruteForceAlgorithm {

    // The test case
    private int[] data;

    public BruteForceAlgorithm(int[] data) {
        this.data = data;
    }

    /**
     * Uses the Brute Force Algorithm to find the maximum subarray by:
     * using a nested for loop where the outer for loop picks the arriving date and
     * the inner loop goes from the arriving date to the end of the array while also adding
     * each element and checking to see if the sum is greater than the current max sum.
     *
     * @return the results of finding the max subarray: maxSum, arrive, and depart
     */
    public MaxSubArrayResults findMaximumSubArray() {
        // The max sum of array
        int maxSum = 0;
        // The temp sum
        int sum = 0;
        // The arrival date
        int arrive = 0;
        // The departure date
        int depart = -1;

        for (int i = 0; i < data.length; i++) {
            sum = 0;
            for (int j = i; j < data.length; j++) {
                sum += data[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    arrive = i;
                    depart = j;
                }
            }
        }

        return new MaxSubArrayResults(maxSum, arrive, depart);
    }
}
