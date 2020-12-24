package Homework.Project3.Code.ChangChon.cs146.project3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

// This allows me to use the @BeforeAll annotation without making it static
@TestInstance(Lifecycle.PER_CLASS)

/**
 * Tests the maze program
 */
public class MazeTester {

    // A String array of elapsed times
    private String[][] times;

    /**
     * Solves each maze size from 0 to 10 and outputs it into a file. Using @BeforeAll, so that it
     * only runs once and so that it can edit times, which is a private instance variable. This
     * method helps sets up for the rest of the tests.
     *
     * @return a 2d array of times for each maze size
     * @throws IOException if file not found
     */
    @BeforeAll
    public void setUp() throws IOException {
        int[] sizes = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        times = new String[11][5];

        // Clears the file
        PrintWriter pw = new PrintWriter("MazeProgram.txt");

        int index = 0;
        for (int size : sizes) {
            Maze maze = new Maze(size);
            // Maze should never be null
            assertNotNull(maze);
            maze.solveMazeDFS();
            maze.solveMazeBFS();
            maze.saveMazes(false);
            times[index++] = maze.getTimes();
        }
    }

    /**
     * Tests the actual output saved in a file to the expected output saved in another file.
     *
     * @throws IOException if file not found
     */
    @Test
    public void testFileOutput() throws IOException {
        BufferedReader actualFileReader = new BufferedReader(
                new FileReader("MazeProgram.txt"));
        BufferedReader expectedFileReader = new BufferedReader(
                new FileReader("ExpectedMazeProgram.txt"));

        String expectedLine;
        while ((expectedLine = expectedFileReader.readLine()) != null) {
            String actualLine = actualFileReader.readLine();

            // The actual file and expected file should be of the same length, so actualLine should
            // never return null.
            assertNotEquals(actualLine, null);

            // Checks whether each line from the expected file and actual file are equal.
            assertEquals(expectedLine, actualLine);
        }
    }

    /**
     * Returns the running time of generating maze, running depth-first search, finding the shortest
     * path after depth-first search, running breadth-first search, and finding the shortest path
     * after breadth-first search. Used @AfterAll to print running time after testing maze output.
     */
    @AfterAll
    public void printRunningTime() {
        // stringRow is basically one row of times for one maze size.
        for (String[] stringRow : times) {
            // stringRow is null when the size of the maze is 0.
            if (stringRow != null) {
                for (String elapsedTimes : stringRow) {
                    System.out.println(elapsedTimes);
                }
                System.out.println();
            }
        }
    }
}
