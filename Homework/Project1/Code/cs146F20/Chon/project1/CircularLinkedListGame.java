package Homework.Project1.Code.cs146F20.Chon.project1;

/**
 * Recreates the prisoner game using a Circular Linked List.
 */
public class CircularLinkedListGame {

    // The number of prisoners, or also, n
    private int prisoners;
    // The number of steps, or also, k
    private int step;
    // This variable will be for calculating the time for the operations
    private double time;
    // Used to recreate the prisoner game
    private CircularLinkedList prisonerGame;

    /**
     * Initializes the number of prisoners and step and also creates a new and empty
     * CircularLinkedList.
     *
     * @param prisoners
     */
    public CircularLinkedListGame(int prisoners, int step) {
        this.prisoners = prisoners;
        this.step = step;
        this.prisonerGame = new CircularLinkedList();
    }

    /**
     * Adds the prisoners into the CircularLinkedList, prisonerGame.
     */
    public void insertPrisoners() {
        time = System.nanoTime();
        // Adds the prisoners into the CircularLinked List, prisonerGame,
        // with their position as data
        for (int i = 0; i < prisoners; i++) {
            prisonerGame.add(new Node(i + 1));
        }
        time = System.nanoTime() - time;
        System.out.println("Time to create list (in ns): " + time);
    }

    /**
     * Finds which prisoner will be the last one left by simulating the experiment from creating the
     * linked list and removing prisoners.
     *
     * @return the position of the last prisoner left
     */
    public int freedom() {
        Node eliminatedPrisoner = prisonerGame.getHead();
        
        // removeCounter is for finding the time to delete on node
        int removeCounter = 0;
        time = System.nanoTime();
        while (prisonerGame.getHead() != prisonerGame.getTail()) {
        	// Finds the prisoner to be removed
            for (int i = 1; i <= step; i++) {
                eliminatedPrisoner = eliminatedPrisoner.getNext();
            }

            time = System.nanoTime();
            
            // Removes a prisoner
            prisonerGame.remove(eliminatedPrisoner);
            
            // This condition is for finding the time to delete one node
            if (removeCounter == 0) {
                time = System.nanoTime() - time;
                System.out.println("Time to delete one node (in ns): " + time);
            }
            removeCounter++;
            eliminatedPrisoner = eliminatedPrisoner.getNext();
        }
        time = System.nanoTime() - time;
        System.out.println("Time to find a winner (in ns): " + time);

        return prisonerGame.getHead().getData();
    }

    /**
     * Checks whether the CircularLinkedList is empty.
     *
     * @return whether prisonerGame has a size of 0 or not
     */
    public boolean isEmpty() {
        return prisonerGame.getSize() == 0;
    }

    /**
     * Gets the CircularLinkedList.
     *
     * @return the CircularLinkedList
     */
    public CircularLinkedList getCircularLinkedList() {
        return prisonerGame;
    }
}
