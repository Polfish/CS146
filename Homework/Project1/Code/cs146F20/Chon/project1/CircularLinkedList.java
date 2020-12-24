package Homework.Project1.Code.cs146F20.Chon.project1;

/**
 * Recreates a line of prisoners for the prisoner game
 */
public class CircularLinkedList {

    // The head, or start, of the linked list
    private Node head;
    // The tail, or end, of the linked list
    private Node tail;
    // The size of the circular linked list
    private int size;

    /**
     * Adds a node to the end of the linked list
     *
     * @param addMe node added to the end of the linked list
     */
    public void add(Node addMe) {
        // If the linked list is empty
        if (head == null) {
            head = addMe;
            tail = addMe;
            head.setNext(tail);
            tail.setNext(head);
            size++;
        }

        tail.setNext(addMe);
        addMe.setNext(head);
        tail = addMe;
        size++;
    }

    /**
     * Removes a node in the linked list
     *
     * @param removeMe node to remove
     */
    public void remove(Node removeMe) {
        // Starting from the tail because you need less moves to get to the head
        Node prev = tail;

        while (prev.getNext().getData() != removeMe.getData()) {
            prev = prev.getNext();
        }

        // These three conditions account for the three cases of Circular Singly-Linked Lists:
        // removing at the tail, head, or in the middle
        if (prev.getNext() == tail) {
            prev.setNext(head);
            tail = prev;
        } else if (prev.getNext() == head) {
            head = head.getNext();
            tail.setNext(head);
        } else {
            prev.setNext(prev.getNext().getNext());
        }
    }

    /**
     * Return the head of the linked list
     *
     * @return head of the linked list
     */
    public Node getHead() {
        return head;
    }

    /**
     * Return the tail of the linked list
     *
     * @return tail of the linked list
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Gets the size of the linked list
     *
     * @return the size of the linked list
     */
    public int getSize() {
        size = 0;

        if (head == null || tail == null) {
            return 0;
        } else if (head == tail) {
            return 1;
        } else {
            Node temp = head;
            // Used to get the correct size or else it will stop and not add the last node
            do {
                temp = temp.getNext();
                size++;
            }
            while (temp != head);
            return size;
        }
    }
}
