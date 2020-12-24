package Homework.Project1.Code.cs146F20.Chon.project1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the CircularLinkedListGame class
 */
public class CircularLinkedListGameTest {

    // Used to run the program
    private CircularLinkedListGame game;

    /**
     * Three arrays are created: n for the number of prisoners, k for the number of steps, and the
     * expected output. These three arrays are used as test cases. There are also three tests done:
     * After creating the linked, check that it is empty, after adding the prisoners, make sure the
     * linked list is not empty and is the correct number, and after running the program, make sure
     * the size of the linked list is 1 and the winner is correct.
     */
    @Test
    public void playGame() {
        int[] n = new int[]{6, 1, 7, 12, 5};
        int[] k = new int[]{2, 9, 7, 8, 1};
        int[] output = new int[]{1, 1, 4, 2, 3};

        for (int i = 0; i < n.length; i++) {
            // After creating the linked list make sure that the linked list is empty
            // and that the linked list's size is 0
            game = new CircularLinkedListGame(n[i], k[i]);
            assertTrue(game.isEmpty());
            assertEquals(0, game.getCircularLinkedList().getSize());

            // After adding the prisoners, make sure that the linked list is not empty and is
            // equal to the correct number of prisoners
            game.insertPrisoners();
            assertFalse(game.isEmpty());
            assertEquals(n[i], game.getCircularLinkedList().getSize());

            // After calculating the winner, make sure the size is 1, and make sure the
            // winner is correct
            game.freedom();
            assertEquals(1, game.getCircularLinkedList().getSize());
            assertEquals(output[i], game.getCircularLinkedList().getHead().getData());
        }
    }
}
