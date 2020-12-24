package Homework.Project1.Code.cs146F20.Chon.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.*;

/**
 * Tests the DataShuffling program
 */
public class DataShufflingTest {

    // Used to run the DataShuffling class
    private DataShuffling ds;

    /**
     * Before testing, creating a new DataShuffling object, and the input is the ErdosCA.txt file
     *
     * @throws IOException if file not found
     */
    @BeforeEach
    public void setUp() throws IOException {
        ds = new DataShuffling(new File("ErdosCA.txt"));
    }

    /**
     * Tests the shuffle program by comparing the output to the expected output
     *
     * @throws IOException if file not found
     */
    @Test
    public void testShuffle() throws IOException {
        ds.shuffle();
        String expectedLine;
        BufferedReader In = new BufferedReader(new FileReader("Target2.txt"));
        BufferedReader Out = new BufferedReader(
                new FileReader("ChonPaulShuffled.txt"));

        while ((expectedLine = In.readLine()) != null) {
            String actualLine = Out.readLine();
            assertEquals(expectedLine, actualLine);
        }

        Out.close();
        In.close();
    }
}
