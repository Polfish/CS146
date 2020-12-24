package Homework.Project2.Code.cs146F20.Chon.project2;

/**
 * Finds the maximum subarray using the Dynamic Programming Algorithm or Kadane's algorithm
 */
public class DynamicProgrammingAlgorithm {

    // The test case
    private int[] data;

    public DynamicProgrammingAlgorithm(int[] data) {
        this.data = data;
    }

    /**
     * Finds the maximum subarray using Kadane's Algorithm by:
     * using a for loop that will add each element of the array to sum and in the end keeping
     * the highest number in maxSum
     *
     * @return the maximum subarray
     */
    public MaxSubArrayResults findMaximumSubArray() {
        // Maximum sum of array
        int maxSum = 0;
        // Used to add up elements in array
        int sum = 0;
        // Arriving date changed from -1 to 0 because when the test case is all positive numbers
        // the arrive date is wrong
        int arrive = 0;
        // Departing date
        int depart = -1;
        // Used to correct the arriving date
        int tempArrive = 0;

        for (int i = 0; i < data.length; i++) {
            sum += data[i];

            if (sum < 0) {
                sum = 0;
                arrive = i + 1;
            }

            if (maxSum < sum) {
                maxSum = sum;
                depart = i;
                tempArrive = arrive;
            }
        }

        arrive = tempArrive;
        return new MaxSubArrayResults(maxSum, arrive, depart);
    }
}
