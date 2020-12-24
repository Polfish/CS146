package Homework.Project2.Code.cs146F20.Chon.project2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Brute Force Algorithm with test cases of 10 and 8, random test cases, and test cases of
 * input sizes: 100, 200, 500, and 1000.
 */
public class BruteForceTest {

    // Used to read data and create random arrays
    private MaxSubArrayIO in;
    // Test cases
    private int[][] testNumbers;
    // Expected results for test cases
    private int[][] expectedResults;
    // The results of the algorithm
    private MaxSubArrayResults results;
    // Used to sum up time
    private double time;
    // Used to find the average time for 10 different runs
    private double elapsedTime;

    /**
     * Sets up the testing by reading the test cases from maxSumtest.txt and inputting them into
     * testNumbers and expectedResults
     *
     * @throws IOException if file not found
     */
    @BeforeEach
    public void setUp() throws IOException {
        in = new MaxSubArrayIO("maxSumtest.txt");
        in.readData();
        testNumbers = in.getTestNumbers();
        expectedResults = in.getExpectedResults();
    }

    /**
     * 10 test cases of 10 different numbers
     */
    @Test
    public void test10() {
        for (int i = 0; i < testNumbers.length; i += 10) {
            BruteForceAlgorithm bfa = new BruteForceAlgorithm(testNumbers[i]);
            results = bfa.findMaximumSubArray();

            assertEquals(expectedResults[i][0], results.getMaxSum());
            assertEquals(expectedResults[i][1], results.getArrive());
            assertEquals(expectedResults[i][2], results.getDepart());
        }
    }


    /**
     * 2 test cases of 8 numbers
     */
    @Test
    public void test8() {
        testNumbers[0] = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        testNumbers[1] = new int[]{-3, -4, -5, -6, -7, -8, -9, -10};

        expectedResults[0] = new int[]{7, 2, 6};
        expectedResults[1] = new int[]{0, 0, -1};
        for (int i = 0; i < 2; i++) {
            BruteForceAlgorithm bfa = new BruteForceAlgorithm(testNumbers[i]);
            results = bfa.findMaximumSubArray();

            assertEquals(expectedResults[i][0], results.getMaxSum());
            assertEquals(expectedResults[i][1], results.getArrive());
            assertEquals(expectedResults[i][2], results.getDepart());
        }
    }

    /**
     * 10 test cases of 10 different arrays
     */
    @Test
    public void moreTest() {
        // Testing 1 big positive number
        testNumbers[0] = new int[]{10293784};
        // Testing 1 negative number
        testNumbers[1] = new int[]{-2};
        // Testing 0
        testNumbers[2] = new int[]{0};
        // Testing 1 positive number
        testNumbers[3] = new int[]{3};
        // Testing 2 negative numbers
        testNumbers[4] = new int[]{-2, -1};
        // Testing 1 negative and 1 positive
        testNumbers[5] = new int[]{-1, 5};
        // Testing 3 negative numbers
        testNumbers[6] = new int[]{-3, -2, -1};
        // Testing 4 big positive numbers
        testNumbers[7] = new int[]{1929, 19239, 848192, 83844};
        // Testing 3 positive and 1 negative number
        testNumbers[8] = new int[]{13, -17, 50, 50};
        // Testing 1 positive and 1 negative number that are a zero pair
        testNumbers[9] = new int[]{10000, -10000};

        expectedResults[0] = new int[]{10293784, 0, 0};
        expectedResults[1] = new int[]{0, 0, -1};
        expectedResults[2] = new int[]{0, 0, -1};
        expectedResults[3] = new int[]{3, 0, 0};
        expectedResults[4] = new int[]{0, 0, -1};
        expectedResults[5] = new int[]{5, 1, 1};
        expectedResults[6] = new int[]{0, 0, -1};
        expectedResults[7] = new int[]{953204, 0, 3};
        expectedResults[8] = new int[]{100, 2, 3};
        expectedResults[9] = new int[]{10000, 0, 0};

        for (int i = 0; i < 10; i++) {
            BruteForceAlgorithm bfa = new BruteForceAlgorithm(testNumbers[i]);
            results = bfa.findMaximumSubArray();

            assertEquals(expectedResults[i][0], results.getMaxSum());
            assertEquals(expectedResults[i][1], results.getArrive());
            assertEquals(expectedResults[i][2], results.getDepart());
        }
    }

    /**
     * Outputs the running times of the input sizes: 100, 200, 500, and 1000
     *
     * @throws IOException if file not found
     */
    @AfterAll
    public static void runningTimeNumbers() throws IOException {
        BruteForceTest bft = new BruteForceTest();
        bft.setUp();

        System.out.println("Input Size 100 (in ns): " + bft.runningTime100());
        System.out.println("Input Size 200 (in ns): " + bft.runningTime200());
        System.out.println("Input Size 500 (in ns): " + bft.runningTime500());
        System.out.println("Input Size 1000 (in ns): " + bft.runningTime1000());
    }

    public double runningTime100() {
        elapsedTime = 0;
        for (int i = 0; i < 10; i++) {
            BruteForceAlgorithm bfa = new BruteForceAlgorithm(in.createArray(100));
            time = System.nanoTime();
            results = bfa.findMaximumSubArray();
            time = System.nanoTime() - time;
            elapsedTime += time;
        }

        return elapsedTime /= 10;
    }

    public double runningTime200() {
        elapsedTime = 0;
        for (int i = 0; i < 10; i++) {
            BruteForceAlgorithm bfa = new BruteForceAlgorithm(in.createArray(200));
            time = System.nanoTime();
            results = bfa.findMaximumSubArray();
            time = System.nanoTime() - time;
            elapsedTime += time;
        }

        return elapsedTime /= 10;
    }

    public double runningTime500() {
        elapsedTime = 0;
        for (int i = 0; i < 10; i++) {
            BruteForceAlgorithm bfa = new BruteForceAlgorithm(in.createArray(500));
            time = System.nanoTime();
            results = bfa.findMaximumSubArray();
            time = System.nanoTime() - time;
            elapsedTime += time;
        }

        return elapsedTime /= 10;
    }

    public double runningTime1000() {
        elapsedTime = 0;
        for (int i = 0; i < 10; i++) {
            BruteForceAlgorithm bfa = new BruteForceAlgorithm(in.createArray(1000));
            time = System.nanoTime();
            results = bfa.findMaximumSubArray();
            time = System.nanoTime() - time;
            elapsedTime += time;
        }

        return elapsedTime /= 10;
    }
}
