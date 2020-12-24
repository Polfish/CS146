package Homework.Project2.Code.cs146F20.Chon.project2;

/**
 * Holds the results of each algorithm: the max sum, the arriving date, and the departing date
 */
public class MaxSubArrayResults {

    // The max sum of the array
    private int maxSum;
    // The arrival date for the max sum
    private int arrive;
    // The departing date for the max sum
    private int depart;

    public MaxSubArrayResults(int maxSum, int arrive, int depart) {
        this.maxSum = maxSum;
        this.arrive = arrive;
        this.depart = depart;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public int getArrive() {
        return arrive;
    }

    public int getDepart() {
        return depart;
    }
}
