package Homework.Project4.Code.cs146F20.Chon.project4;

/**
 * Represents a Node in the Red Black Tree
 * @param <Key> any object that implements Comparable
 */
public class Node<Key extends Comparable<Key>> { //changed to static

    //Key of the node
    Key key;
    // Parent of node
    Node<Key> parent;
    // Left child of node
    Node<Key> leftChild;
    // Right child of node
    Node<Key> rightChild;
    // True if node is red and false if node is not red
    boolean isRed;
    // 0 will be red and 1 will be black
    protected int color;

    /**
     * Initializes the node by setting the key with the data parameter and setting the parent,
     * leftChild, and rightChild as null.
     * @param data sets the key for the node
     */
    public Node(Key data) {
        this.key = data;
        parent = null;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Compare one node with another using their keys
     * @param n the other node
     * @return 0 if the keys are both equal, less than 0 if this key is less than the other key,
     * and greater than 0 if this key is greater than the other key
     */
    public int compareTo(Node<Key> n) {    //this < that  <0
        return key.compareTo(n.key);    //this > that  >0
    }

    /**
     * Checks if the node is a leaf
     * @return true if the node if a leaf otherwise false
     */
    public boolean isLeaf() {
        if (this.leftChild.key == null && this.rightChild.key == null) {
            return true;
        }
        return false;
    }
}
