package Homework.Project3.Code.ChangChon.cs146.project3;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * IO class for the maze program mainly used to output to a file.
 */
public class MazeIO {

    // Used to output to a file
    private PrintWriter pw;

    /**
     * Creates the PrintWriter to output to a file
     *
     * @throws IOException if file not found
     */
    public MazeIO() throws IOException {
        pw = new PrintWriter(
                new BufferedWriter(new FileWriter("MazeProgram.txt", true)));
    }

    /**
     * Saves the output of the maze program into a file
     *
     * @param maze each output of the maze program
     * @throws IOException if file not found
     */
    public void saveToFile(String maze) throws IOException {
        pw.println(maze);
    }

    /**
     * Clears the file using the PrintWriter. Didn't put inside the saveToFile method because the
     * file will be cleared every time Maze is called in MazeTester.
     *
     * @throws FileNotFoundException if file not found
     */
    public void clearFile() throws FileNotFoundException {
        pw.println("");
    }

    /**
     * Close the PrintWriter
     *
     * @throws FileNotFoundException if file not found
     */
    public void closeFile() throws FileNotFoundException {
        pw.close();
    }
}
