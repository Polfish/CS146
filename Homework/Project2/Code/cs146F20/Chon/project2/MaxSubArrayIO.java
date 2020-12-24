package Homework.Project2.Code.cs146F20.Chon.project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Reads input from a txt file Used to read the data from the maxSumtext.txt file
 */
public class MaxSubArrayIO {

    // Name of file
    private String fileName;
    // Each line of data in the file
    private int[][] dataLine;
    // The test numbers
    private int[][] testNumbers;
    // What the results should be for the testNumbers
    private int[][] expectedResults;

    public MaxSubArrayIO(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads data from file and puts it into testNumbers and expectedResults
     *
     * @throws IOException if file not found
     */
    public void readData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Split with two spaces because the txt files separates the numbers with two spaces
        String[] line = br.readLine().split("  ");
        dataLine = new int[10][line.length];
        testNumbers = new int[10][line.length - 3];
        expectedResults = new int[10][3];

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < line.length; column++) {
                int number = Integer.parseInt(line[column].stripLeading());
                dataLine[row][column] = number;

                if (column < line.length - 3) {
                    testNumbers[row][column] = number;
                } else {
                    expectedResults[row][(column + 3) - dataLine[0].length] = number;
                }
            }
            br.readLine();
        }
    }

    /**
     * Used to create a random array of a specific size
     *
     * @param size the size of the array
     * @return a random array of a specific size
     */
    public int[] createArray(int size) {
        Random gen = new Random();
        int[] randomNumbers = new int[size];
        for (int i = 0; i < size; i++) {
            randomNumbers[i] = gen.nextInt(1000);
        }
        return randomNumbers;
    }

    public int[][] getDataLine() {
        return dataLine;
    }

    public int[][] getTestNumbers() {
        return testNumbers;
    }

    public int[][] getExpectedResults() {
        return expectedResults;
    }
}
