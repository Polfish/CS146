package Homework.Project4.Code.cs146F20.Chon.project4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Tests the RedBlackTree class with a manual test of adding letters and
 * another set of tests to see if spell checking using a dictionary works.
 */
public class RBTTester {

    private double time;

    @Test
    //Test the Red Black Tree
    public void test() {
        RedBlackTree<String> rbt = new RedBlackTree<>();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str = "Color: 1, Key:D Parent: \n" +
                "Color: 1, Key:B Parent: D\n" +
                "Color: 1, Key:A Parent: B\n" +
                "Color: 1, Key:C Parent: B\n" +
                "Color: 1, Key:F Parent: D\n" +
                "Color: 1, Key:E Parent: F\n" +
                "Color: 0, Key:H Parent: F\n" +
                "Color: 1, Key:G Parent: H\n" +
                "Color: 1, Key:I Parent: H\n" +
                "Color: 0, Key:J Parent: I\n";
        assertEquals(str, makeStringDetails(rbt));
    }

    /**
     * Uses the RedBlackTree class as a dictionary/spell checker for two documents: noErrorDocument
     * and exampleDocument. The first document, noErrorDocument, should have no errors because I
     * just copied about 10,000 of last lines of the dictionary into this document. The second
     * document, exampleDocument, is a poem (Do Not Go Gentle into that Good Night by Dylan Thomas).
     *
     * @throws IOException if file not found
     */
    @Test
    public void spellChecker() throws IOException {
        BufferedReader dictionary = new BufferedReader(
                new FileReader("dictionary.txt"));
        // Took about 10,000 of the last words of dictionary.txt and put it into this document, so
        // this document should have no errors.
        BufferedReader noErrorDocument = new BufferedReader(
                new FileReader("noErrorDocument.txt"));
        // This document might have some errors because the dictionary might not cover all of its
        // words.
        BufferedReader exampleDocument = new BufferedReader(
                new FileReader("exampleDocument.txt"));

        RedBlackTree<String> rbt = new RedBlackTree<>();

        time = System.nanoTime();
        String line;
        while ((line = dictionary.readLine()) != null) {
            rbt.insert(line);
        }
        time = System.nanoTime() - time;
        System.out.println("Time to create dictionary (in ns): " + time);

        int counter = 0;
        double averageTime = 0;
        while ((line = noErrorDocument.readLine()) != null) {
            String[] split = line.split(" ");
            for (String s : split) {
                time = System.nanoTime();
                // Should never have an word that isn't found
                assertNotEquals("Element not found", rbt.lookup(s).key);
                time = System.nanoTime() - time;
                averageTime += time;
                counter++;
            }
        }
        averageTime /= counter;
        System.out.println("Average time for lookup (in ns): " + averageTime);

        System.out.println("Found Words:");
        // Counting the number of words not found
        int notFound = 0;
        while ((line = exampleDocument.readLine()) != null) {
            String[] split = line.split("\\s+");
            for (String s : split) {
                // Replaces anything in a string that is not part of the alphabet, such as
                // a comma or a period, and also turns the string lowercase because the dictionary
                // is all lowercase.
                s = s.replaceAll("[^A-Za-z]", "").toLowerCase();

                String key = rbt.lookup(s).key;
                // The key should never return null because when the key is null it gets switched
                // to "Element not found". This test is assertNotNull and not assertNotEquals
                // because some of the words are not found in the dictionary as the sample
                // dictionary does not have words 3 letters or less and also does not contain
                // every word
                assertNotNull(key);

                // Printing out the element if it is found to show that spell checking works on
                // regular documents.
                if (!key.equals("Element not found")) {
                    System.out.print(key + " ");
                } else {
                    notFound++;
                }
            }
            System.out.println();
        }
        System.out.println("Number of not found elements: " + notFound);
    }

    /**
     * Tests an Integer version of RedBlackTree to show that this class is generic. A similar test
     * to the string version of RedBlackTree in the first test.
     */
    @Test
    public void intTest() {
        RedBlackTree<Integer> rbtInt = new RedBlackTree<>();
        rbtInt.insert(6);
        rbtInt.insert(2);
        rbtInt.insert(9);
        rbtInt.insert(8);
        rbtInt.insert(7);
        rbtInt.insert(3);
        rbtInt.insert(4);
        rbtInt.insert(1);
        rbtInt.insert(10);

        rbtInt.insert(5);

        assertEquals("63214587910", makeString(rbtInt));

        String str = "Color: 1, Key:6 Parent: \n" +
                "Color: 0, Key:3 Parent: 6\n" +
                "Color: 1, Key:2 Parent: 3\n" +
                "Color: 0, Key:1 Parent: 2\n" +
                "Color: 1, Key:4 Parent: 3\n" +
                "Color: 0, Key:5 Parent: 4\n" +
                "Color: 0, Key:8 Parent: 6\n" +
                "Color: 1, Key:7 Parent: 8\n" +
                "Color: 1, Key:9 Parent: 8\n" +
                "Color: 0, Key:10 Parent: 9\n";

        assertEquals(str, makeStringDetails(rbtInt));
    }

    /**
     * Does a preorder visit of the Red Black Tree and prints out their key
     *
     * @param t the Red Black Tree
     * @return String of nodes in Red Black Tree visited by preorder
     */
    public static String makeString(RedBlackTree t) {
        /**
         * Class that implements the Visitor interface.
         */
        class MyVisitor implements Visitor {

            String result = "";

            /**
             * Visits a node and only saves the key into the string.
             * @param n the visited node
             */
            public void visit(Node n) {
                result = result + n.key;
            }
        }
        ;
        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);
        return v.result;
    }

    /**
     * Does a preorder visit of the Red Black Tree and prints out their key along with their color
     * and parent.
     *
     * @param t the Red Black Tree
     * @return String of nodes in Red Black Tree visited by preorder
     */
    public static String makeStringDetails(RedBlackTree t) {
        {
            /**
             * Class that implements the Visitor interface.
             */
            class MyVisitor implements Visitor {

                String result = "";

                /**
                 * Used to visit a node in more detail than makeString: the color, the key, and the
                 * parent of the visited node.
                 * @param n the visited node
                 */
                public void visit(Node n) {
                    if (n.key != null && n.parent.key == null) {
                        result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: "
                                + "\n";
                    } else if (n.key != null) {
                        result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: "
                                + n.parent.key + "\n";
                    }

                }
            }
            ;
            MyVisitor v = new MyVisitor();
            t.preOrderVisit(v);
            return v.result;
        }
    }
}
  
