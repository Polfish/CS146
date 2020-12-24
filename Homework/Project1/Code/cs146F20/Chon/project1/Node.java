package Homework.Project1.Code.cs146F20.Chon.project1;

/**
 * Represents a prisoner
 */
public class Node {

    // Represents what position a prisoner is
    private int data;
    // The next node, or prisoner
    private Node next;

    /**
     * Input data into the node
     * Used to put in the position of the prisoner
     *
     * @param data data to put into node
     */
    public Node(int data) {
        this.data = data;
    }

    /**
     * Gets data of the node
     * Used to get the position of the prisoner
     *
     * @return node's data
     */
    public int getData() {
        return data;
    }

    /**
     * Get the next node
     * Used to get the next prisoner
     *
     * @return the next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets inputted node as the next node
     *
     * @param next node to set next as
     */
    public void setNext(Node next) {
        this.next = next;
    }

}
