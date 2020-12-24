package Homework.Project1.Code.cs146F20.Chon.project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Shuffles a dataset of Erdos numbers, using the Fisher-Yates algorithm.
 */
public class DataShuffling {

    // Each line of the data set will be put in this array
    private String[] data;
    // This variable will be for calculating the time for the operations
    private double time;

    /**
     * Takes in a file and copies each line into a String array
     *
     * @param file the input file
     * @throws IOException if file not found
     */
    public DataShuffling(File file) throws IOException {
        time = System.nanoTime();
        data = new String[7515];
        BufferedReader br = new BufferedReader(new FileReader("ErdosCA.txt"));

        // This readLine() is used to skip the first line, which is just metadata
        br.readLine();
        for (int i = 0; i < data.length; i++) {
            data[i] = br.readLine();
        }

        br.close();
        time = System.nanoTime() - time;
        System.out.println("Time to read from file (in ns): " + time);
    }

    /**
     * Shuffles the dataset using the Fisher-Yates algorithm
     *
     * @throws IOException if file not found
     */
    public void shuffle() throws IOException {
        Random r = new Random(20);
        time = System.nanoTime();

        for (int i = data.length - 1; i > 0; i--) {
            int j = r.nextInt(i);
            String temp = data[j];
            data[j] = data[i];
            data[i] = temp;
        }

        time = System.nanoTime() - time;
        System.out.println("Time of shuffle (in ns): " + time);

        time = System.nanoTime();

        // This PrintWriter will clear the file before writing to it or else the file will
        // append infinitely each time you run a test.
        PrintWriter clear = new PrintWriter("ChonPaulShuffled.txt");

        // I used PrintWriter instead of just using BufferedWriter because PrintWriter can use
        // the print and println, which are much easier methods to use
        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter("ChonPaulShuffled.txt", true)));

        for (String s : data) {
            pw.println(s);
        }

        time = System.nanoTime() - time;
        System.out.println("Time to write to file (in ns): " + time);
        pw.close();
    }
}
