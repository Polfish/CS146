package Homework.Project4.Code.cs146F20.Chon.project4;

/**
 * An interface to visit nodes.
 * @param <Key> any object that implements Comparable
 */
public interface Visitor<Key extends Comparable<Key>> {

    /**
     * This method is called at each node.
     *
     * @param n the visited node
     */
    void visit(Node<Key> n);
}

