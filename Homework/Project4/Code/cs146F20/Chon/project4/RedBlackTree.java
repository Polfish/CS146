package Homework.Project4.Code.cs146F20.Chon.project4;

/**
 * This class models a Red Black Tree and is generic, so it should take in any object that
 * implements Comparable.
 *
 * @param <Key> any object that implements Comparable
 */
public class RedBlackTree<Key extends Comparable<Key>> {

    // The root Node
    private Node<Key> root;
    // This NULL node represents any NULL leaves
    private final Node<Key> NULL = new Node<>(null);

    /**
     * Initializes the root of the Red Black Tree with everything as NULL or else a
     * NullPointerException will happen later on.
     */
    public RedBlackTree() {
        root = NULL;
        root.parent = NULL;
        root.leftChild = NULL;
        root.rightChild = NULL;

        // Null leaves are all black, so set NULL's color to black, which is 1, and NULL's isRed
        // to false.
        NULL.color = 1;
        NULL.isRed = false;
    }

    /**
     * Check if a specific node is a leaf
     *
     * @param n the node to check
     * @return true if the node is a leaf false otherwise
     */
    public boolean isLeaf(Node<Key> n) {
        if (n.equals(root) && n.leftChild == null && n.rightChild == null) {
            return true;
        }
        if (n.equals(root)) {
            return false;
        }
        if (n.leftChild == null && n.rightChild == null) {
            return true;
        }
        return false;
    }

    /**
     * Visits a node.
     *
     * @param n node to visit
     */
    public void visit(Node<Key> n) {
        if (n != NULL) {
            System.out.println(n.key);
        }
    }

    /**
     * Prints the Red Black Tree without any parameters
     */
    public void printTree() {  //preorder: visit, go left, go right
        Node<Key> currentNode = root;
        printTree(currentNode);
    }

    /**
     * Prints the Red Black Tree with parameters
     *
     * @param node node to visit
     */
    public void printTree(Node<Key> node) {
        if (node != NULL) {
            System.out.print(node.key);
        }

        if (node.isLeaf()) {
            return;
        }
        printTree(node.leftChild);
        printTree(node.rightChild);
    }

    /**
     * Adds a new node into the Red Black Tree with its key as the parameter and will be colored red
     * and fixed later on.
     *
     * @param data key for new node
     */
    public void addNode(Key data) {    //this < that  <0.  this > that  >0
        Node<Key> newNode = new Node<>(data);
        Node<Key> leafNode = NULL;
        Node<Key> rootNode = root;

        // Finds the parent of the new node.
        while (rootNode != NULL) {
            leafNode = rootNode;
            if (data.compareTo(rootNode.key) < 0) {
                rootNode = rootNode.leftChild;
            } else {
                rootNode = rootNode.rightChild;
            }
        }

        newNode.parent = leafNode;

        // Sets new node as root, leafNode's left child, or leafNode's right child.
        if (leafNode == NULL) {
            root = newNode;
        } else if (data.compareTo(leafNode.key) < 0) {
            leafNode.leftChild = newNode;
        } else {
            leafNode.rightChild = newNode;
        }

        // Set new node's left and right child as NULL because the new node will be a leaf node.
        // Also, set new node's color to red (0) and isRed to true, and will be fixed with the
        // fixTree method.
        newNode.leftChild = NULL;
        newNode.rightChild = NULL;
        newNode.isRed = true;
        newNode.color = 0;

        fixTree(newNode);
    }

    /**
     * Inserts a node with its key as the data parameter. Uses the addNode method to actually
     * insert.
     *
     * @param data key of the new node
     */
    public void insert(Key data) {
        addNode(data);
    }

    /**
     * Look up a node with a specific key.
     *
     * @param k the key to look for
     * @return the node with the same key
     */
    public Node<Key> lookup(Key k) {
        Node<Key> findMe = root;

        // Starting at the root node it will go down comparing keys until it finds the correct
        // node.
        int compare;
        while (findMe != NULL && (compare = k.compareTo(findMe.key)) != 0) {
            if (compare < 0) {
                findMe = findMe.leftChild;
            } else if (compare > 0) {
                findMe = findMe.rightChild;
            }
        }

        // Instead of just returning null, this method will just return "Element not found".
        if (findMe == NULL) {
            return new Node<>((Key) "Element not found");
        } else {
            return findMe;
        }
    }

    /**
     * Gets the sibling of a node.
     *
     * @param n node to find sibling of
     * @return sibling of a node
     */
    public Node<Key> getSibling(Node<Key> n) {
        if (n == n.parent.leftChild) {
            return n.parent.rightChild;
        } else if (n == n.parent.rightChild) {
            return n.parent.leftChild;
        } else {
            // For some reason doesn't like it when I don't have an else statement
            return null;
        }
    }

    /**
     * Gets the aunt of a node.
     *
     * @param n node to get aunt of
     * @return aunt of a node
     */
    public Node<Key> getAunt(Node<Key> n) {
        return getSibling(n.parent);
    }

    /**
     * Gets grandparent of a node. Essentially, a node's parent's parent.
     *
     * @param n node to get grandparent of
     * @return grandparent of a node
     */
    public Node<Key> getGrandparent(Node<Key> n) {
        return n.parent.parent;
    }

    /**
     * Rotates a node to the left.
     *
     * @param n node to rotate to the left
     */
    public void rotateLeft(Node<Key> n) {
        Node<Key> rightChild = n.rightChild;
        n.rightChild = rightChild.leftChild;

        // Have to set new parent if not null
        if (rightChild.leftChild != NULL) {
            rightChild.leftChild.parent = n;
        }

        rightChild.parent = n.parent;

        if (n.parent == NULL) {
            root = rightChild;
        } else if (n == n.parent.leftChild) {
            n.parent.leftChild = rightChild;
        } else {
            n.parent.rightChild = rightChild;
        }

        rightChild.leftChild = n;
        n.parent = rightChild;
    }

    /**
     * Rotates a node to the right.
     *
     * @param n node to rotate to the right
     */
    public void rotateRight(Node<Key> n) {
        Node<Key> leftChild = n.leftChild;
        n.leftChild = leftChild.rightChild;

        // Have to set new parent if not null
        if (leftChild.rightChild != NULL) {
            leftChild.rightChild.parent = n;
        }

        leftChild.parent = n.parent;

        if (n.parent == NULL) {
            root = leftChild;
        } else if (n == n.parent.rightChild) {
            n.parent.rightChild = leftChild;
        } else {
            n.parent.leftChild = leftChild;
        }

        leftChild.rightChild = n;
        n.parent = leftChild;
    }

    /**
     * Fixes Red Black Tree if it does not match properties of a red-black tree.
     *
     * @param current current node
     */
    public void fixTree(Node<Key> current) {
        // Temporary variable to hold another Node.
        Node<Key> temp;

        // If the current node's parent's color is red (0), then need to fix tree because the
        // current node will be 0 because of the addNode method.
        while (current.parent.color == 0) {
            temp = getAunt(current);
            if (isLeftChild(getGrandparent(current), current.parent)) {
                if (temp.color == 0) {
                    // Case 1: Current's aunt is red
                    current.parent.color = 1;
                    current.parent.isRed = false;
                    temp.color = 1;
                    temp.isRed = false;
                    getGrandparent(current).color = 0;
                    getGrandparent(current).isRed = true;
                    current = getGrandparent(current);
                } else if (current == current.parent.rightChild) {
                    // Case 2: Aunt is black and current node is a right child
                    current = current.parent;
                    rotateLeft(current);
                } else {
                    // Case 3: Aunt is black and current node is a left child
                    current.parent.color = 1;
                    current.parent.isRed = false;
                    getGrandparent(current).color = 0;
                    getGrandparent(current).isRed = true;
                    rotateRight(getGrandparent(current));
                }
            } else {
                if (temp.color == 0) {
                    // Case 1 symmetrical: Current's aunt is red
                    current.parent.color = 1;
                    current.parent.isRed = false;
                    temp.color = 1;
                    temp.isRed = false;
                    getGrandparent(current).color = 0;
                    getGrandparent(current).isRed = true;
                    current = getGrandparent(current);
                } else if (current == current.parent.leftChild) {
                    // Case 2 symmetrical: Aunt is black and current node is a left child
                    current = current.parent;
                    rotateRight(current);
                } else {
                    // Case 3 symmetrical: Aunt is black and current node is a right child
                    current.parent.color = 1;
                    current.parent.isRed = false;
                    getGrandparent(current).color = 0;
                    getGrandparent(current).isRed = true;
                    rotateLeft(getGrandparent(current));
                }
            }
        }

        // Always set root to black.
        root.color = 1;
    }

    /**
     * Checks if node is empty.
     *
     * @param n node to check
     * @return true if key equals null otherwise false
     */
    public boolean isEmpty(Node<Key> n) {
        if (n.key == null) {
            return true;
        }
        return false;
    }

    /**
     * Check if one node is the left child of a node
     *
     * @param parent potential parent node
     * @param child  potential child node
     * @return true if child is left child of parent otherwise false
     */
    public boolean isLeftChild(Node<Key> parent, Node<Key> child) {
        if (child.compareTo(parent) < 0) {//child is less than parent
            return true;
        }
        return false;
    }

    /**
     * Preorder visit of the Red Black Tree. Uses another preOrderVisit method below this one.
     *
     * @param v used to visit nodes
     */
    public void preOrderVisit(Visitor<Key> v) {
        preOrderVisit(root, v);
    }

    /**
     * Preorder visit of the Red Black Tree and it is a recursive implementation.
     *
     * @param n node to visit
     * @param v used to visit node
     */
    private void preOrderVisit(Node<Key> n, Visitor<Key> v) {
        if (n == NULL) {
            return;
        }
        v.visit(n);
        preOrderVisit(n.leftChild, v);
        preOrderVisit(n.rightChild, v);
    }
}

